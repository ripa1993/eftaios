package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.controller.playerActions.Attack;
import it.polimi.ingsw.cg_8.controller.playerActions.DrawDangerousSectorCard;
import it.polimi.ingsw.cg_8.controller.playerActions.EndTurn;
import it.polimi.ingsw.cg_8.controller.playerActions.FakeNoise;
import it.polimi.ingsw.cg_8.controller.playerActions.Movement;
import it.polimi.ingsw.cg_8.controller.playerActions.otherActions.Disconnect;
import it.polimi.ingsw.cg_8.controller.playerActions.otherActions.GetAllowedActions;
import it.polimi.ingsw.cg_8.controller.playerActions.otherActions.GetCards;
import it.polimi.ingsw.cg_8.controller.playerActions.otherActions.GetReachableSectors;
import it.polimi.ingsw.cg_8.controller.playerActions.otherActions.SetPlayerName;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseAdrenalineCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseAttackCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseSedativesCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseSpotlightCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseTeleportCard;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.TurnPhase;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.view.client.actions.ActionAttack;
import it.polimi.ingsw.cg_8.view.client.actions.ActionChat;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDisconnect;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDrawCard;
import it.polimi.ingsw.cg_8.view.client.actions.ActionEndTurn;
import it.polimi.ingsw.cg_8.view.client.actions.ActionFakeNoise;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetAvailableAction;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetHand;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetReachableCoordinates;
import it.polimi.ingsw.cg_8.view.client.actions.ActionMove;
import it.polimi.ingsw.cg_8.view.client.actions.ActionSetName;
import it.polimi.ingsw.cg_8.view.client.actions.ActionUseCard;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ResponseChat;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Simulation of a state machine, used to handle {@link ClientAction} generated
 * by the client
 * 
 * @author Simone
 * @version 1.0
 */
public class StateMachine {
	/**
	 * This method evaluates an action created by a player though a client.
	 * 
	 * @param controller
	 *            reference to the game
	 * @param a
	 *            action that needs to be evaluated
	 * @param player
	 *            player that submitted the action
	 * @return true, if the action has been validated<br>
	 *         false, if the action has been refused
	 */
	public static boolean evaluateAction(Controller controller, ClientAction a,
			Player player) {

		// local variables, calculated every time a new evaluation is launched
		Model model = controller.getModel();
		Player currentPlayer = model.getCurrentPlayerReference();
		TurnPhase turnPhase = model.getTurnPhase();
		Rules rules = controller.getRules();

		/**
		 * A disconnected player isn't allowed to use any command. The same goes
		 * if the game is over.
		 */
		if (player.getState().equals(PlayerState.DISCONNECTED)
				|| model.getTurnPhase().equals(TurnPhase.GAME_END)) {
			return false;
		}

		// handles chat and disconnect action, always true
		if (a instanceof ActionChat) {
			String message = ((ActionChat) a).getMessage();
			controller.writeToAll(new ResponseChat(player.getName(), message));
			return true;
		}

		if (a instanceof ActionDisconnect) {
			Disconnect.disconnect(player);
			if (player.equals(model.getCurrentPlayerReference())) {
				model.nextPlayer();
				model.setTurnPhase(TurnPhase.TURN_BEGIN);
			}

			controller.writeToAll(new ResponsePrivate(player.getName()
					+ " has been disconnected."));
//			StateMachine.endTurnMessage(controller, model, currentPlayer);
			return true;
		}

		if (turnPhase == TurnPhase.GAME_SETUP) {
			if (a instanceof ActionSetName) {
				String name = ((ActionSetName) a).getName();
				try {
					SetPlayerName.setPlayerName(name, model);
				} catch (GameAlreadyRunningException e) {
					// TODO: never happens if we handle concurrency in the right
					// way
				}
				return true;
			}
			return false;
		}

		// TODO: rimuovere i system out

		if (!(model.getTurnPhase() == TurnPhase.GAME_SETUP)
				|| !(model.getTurnPhase() == TurnPhase.GAME_END)) {
			if (a instanceof ActionGetReachableCoordinates) {
				controller.writeToPlayer(
						player,
						new ResponsePrivate(GetReachableSectors
								.printReachableSectors(model, player)));
				return true;
			}
			if (a instanceof ActionGetHand) {
				controller.writeToPlayer(player,
						new ResponsePrivate(GetCards.printHeldCards(player)));
				return true;
			}
			if (a instanceof ActionGetAvailableAction) {
				controller.writeToPlayer(player, new ResponsePrivate(
						GetAllowedActions.printActions(player)));
				return true;
			}
		}

		// if not currentPlayer, then refuse all actions
		if (!currentPlayer.equals(player)) {
			return false;
		}
		// handle TURN_BEGIN
		if (turnPhase == TurnPhase.TURN_BEGIN) {

			// movement

			if (a instanceof ActionMove) {
				// validate movement
				Coordinate destination = ((ActionMove) a).getCoordinate();
				if (rules.movementValidator(model, destination)) {
					// execute movement
					Movement move = new Movement(model, destination);
					move.makeMove();
					controller.writeToAll(new ResponsePrivate(player.getName()
							+ " has moved."));
					Sector destinationSector = model.getMap().getSectors()
							.get(destination);
					if (currentPlayer.getCharacter().hasToDrawSectorCard() == false) {
						model.setTurnPhase(TurnPhase.MOVEMENT_DONE_NOT_DS);
					} else {
						if (destinationSector instanceof DangerousSector) {
							model.setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);
						} else {
							model.setTurnPhase(TurnPhase.MOVEMENT_DONE_NOT_DS);
						}
					}
					return true;
				}
				controller.writeToPlayer(player, new ResponsePrivate(
						"Moving to " + destination + " is not allowed"));
				return false;

			}

			// use item card

			if (a instanceof ActionUseCard) {
				ItemCard card = ((ActionUseCard) a).getItemCard();
				Coordinate coordinate = ((ActionUseCard) a).getCoordinate();
				if (card instanceof AdrenalineCard) {
					if (rules.useItemCardValidator(model, card)) {
						UseAdrenalineCard.useCard(model);
						controller.writeToAll(new ResponsePrivate(player
								.getName() + " has used an Adrenaline Card"));
						return true;
					}
					return false;
				}

				if (card instanceof AttackCard) {
					if (rules.useItemCardValidator(model, card)) {
						UseAttackCard.useCard(model);
						controller.writeToAll(new ResponsePrivate(player
								.getName() + " has used an Attack Card"));

						return true;
					}
					return false;
				}
				if (card instanceof TeleportCard) {
					if (rules.useItemCardValidator(model, card)) {
						UseTeleportCard.useCard(model);
						controller.writeToAll(new ResponsePrivate(player
								.getName() + " has used a Teleport Card"));
						return true;
					}
					return false;
				}
				if (card instanceof SedativesCard) {
					if (rules.useItemCardValidator(model, card)) {
						UseSedativesCard.useCard(model);
						controller.writeToAll(new ResponsePrivate(player
								.getName() + " has used a Sedatives Card"));
						model.setTurnPhase(TurnPhase.MOVEMENT_DONE_NOT_DS);
						return true;
					}
					return false;
				}
				if (card instanceof SpotlightCard) {
					if (rules.useItemCardValidator(model, card)) {
						UseSpotlightCard.useCard(model, coordinate);
						controller.writeToAll(new ResponsePrivate(player
								.getName() + " has used a Spotlight Card"));
						return true;
					}
					return false;
				}

			}
			return false;
		}
		// handle MOVEMENT_DONE_NOT_DS
		if (turnPhase == TurnPhase.MOVEMENT_DONE_NOT_DS) {

			/**
			 * Attack
			 */
			if (a instanceof ActionAttack) {
				return StateMachine.attackMove(rules, model, controller);
			}

			// end turn

			if (a instanceof ActionEndTurn) {
				endTurn(controller, model, player);
				return true;
			}

			// use card

			if (a instanceof ActionUseCard) {
				if (a instanceof ActionUseCard) {
					ItemCard card = ((ActionUseCard) a).getItemCard();
					Coordinate coordinate = ((ActionUseCard) a).getCoordinate();

					if (card instanceof AttackCard) {
						if (rules.useItemCardValidator(model, card)) {
							UseAttackCard.useCard(model);
							controller.writeToAll(new ResponsePrivate(player
									.getName() + " has used an Attack Card"));
							return true;
						}
						return false;
					}
					if (card instanceof TeleportCard) {
						if (rules.useItemCardValidator(model, card)) {
							UseTeleportCard.useCard(model);
							controller.writeToAll(new ResponsePrivate(player
									.getName() + " has used a Teleport Card"));

							return true;
						}
						return false;
					}
					if (card instanceof SedativesCard) {
						if (rules.useItemCardValidator(model, card)) {
							UseSedativesCard.useCard(model);
							controller.writeToAll(new ResponsePrivate(player
									.getName() + " has used a Sedatives Card"));
							return true;
						}
						return false;
					}
					if (card instanceof SpotlightCard) {
						if (rules.useItemCardValidator(model, card)) {
							UseSpotlightCard.useCard(model, coordinate);
							controller.writeToAll(new ResponsePrivate(player
									.getName() + " has used a Spotlight Card"));
							return true;
						}
						return false;
					}
				}
			}
			return false;
		}

		// handle MOVEMENT_DONE_DS
		if (turnPhase == TurnPhase.MOVEMENT_DONE_DS) {

			/**
			 * Attack
			 */
			if (a instanceof ActionAttack) {
				return StateMachine.attackMove(rules, model, controller);
			}

			// draw card

			if (a instanceof ActionDrawCard) {
				DrawDangerousSectorCard draw = new DrawDangerousSectorCard(
						model);
				boolean hasToMakeFakeNoise = false;

				try {
					hasToMakeFakeNoise = draw.drawDangerousSectorCard();
					controller.writeToPlayer(
							player,
							new ResponsePrivate("You have drawn a "
									+ draw.getDangerousSectorCard()));
					System.out.println(draw.getItemCard());

					if (draw.getItemCard() != null
							&& draw.isDiscardedItemCard() == false) {
						controller.writeToPlayer(player, new ResponsePrivate(
								"You have drawn a " + draw.getItemCard()));
					} else if (draw.getItemCard() != null
							&& draw.isDiscardedItemCard() == true) {
						controller.writeToPlayer(player, new ResponsePrivate(
								"You have drawn " + draw.getItemCard()
										+ " but your hand is full,"
										+ " so the card has been discarded."));
					} else if (draw.getItemCard() == null
							&& draw.isEmptyItemDeck()) {
						controller.writeToPlayer(player, new ResponsePrivate(
								"The item card deck is empty"));
					} else {
						controller.writeToPlayer(player, new ResponsePrivate(
								"No item card was drawn"));
					}

				} finally {
					controller.writeToPlayer(player, new ResponsePrivate(player
							.getHand().getHeldCards().toString()));
					if (hasToMakeFakeNoise == true) {
						model.setTurnPhase(TurnPhase.WAITING_FAKE_NOISE);
						controller.writeToPlayer(player, new ResponsePrivate(
								"Make a noise on a coordinate of your choice"));
					} else {
						model.setTurnPhase(TurnPhase.DRAWN_CARD);
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							System.err.println("[DEBUG] Failed to sleep");
						}
						controller.writeToAll(new ResponsePrivate(player
								.getName()
								+ " has drawn a Dangerous Sector Card"));
					}
				}
				return true;
			}

			// use item card

			if (a instanceof ActionUseCard) {
				if (a instanceof ActionUseCard) {
					ItemCard card = ((ActionUseCard) a).getItemCard();
					Coordinate coordinate = ((ActionUseCard) a).getCoordinate();

					if (card instanceof AttackCard) {
						if (rules.useItemCardValidator(model, card)) {
							UseAttackCard.useCard(model);
							controller.writeToAll(new ResponsePrivate(player
									.getName() + " has used a Attack Card"));

							return true;
						}
						return false;
					}
					if (card instanceof TeleportCard) {
						if (rules.useItemCardValidator(model, card)) {
							UseTeleportCard.useCard(model);
							controller.writeToAll(new ResponsePrivate(player
									.getName() + " has used a Teleport Card"));

							return true;
						}
						return false;
					}
					if (card instanceof SedativesCard) {
						if (rules.useItemCardValidator(model, card)) {
							UseSedativesCard.useCard(model);
							model.setTurnPhase(TurnPhase.MOVEMENT_DONE_NOT_DS);
							controller.writeToAll(new ResponsePrivate(player
									.getName() + " has used a Sedatives Card"));

							return true;
						}
						return false;
					}
					if (card instanceof SpotlightCard) {
						if (rules.useItemCardValidator(model, card)) {
							UseSpotlightCard.useCard(model, coordinate);
							controller.writeToAll(new ResponsePrivate(player
									.getName() + " has used a Spotlight Card"));

							return true;
						}
						return false;
					}
				}
			}

		}

		// handle ATTACK_PHASE
		if (turnPhase == TurnPhase.ATTACK_DONE) {

			// end turn

			if (a instanceof ActionEndTurn) {
				endTurn(controller, model, player);
				return true;
			}

			// use item card

			if (a instanceof ActionUseCard) {
				ItemCard card = ((ActionUseCard) a).getItemCard();
				Coordinate coordinate = ((ActionUseCard) a).getCoordinate();
				if (card instanceof TeleportCard) {
					if (rules.useItemCardValidator(model, card)) {
						UseTeleportCard.useCard(model);
						controller.writeToAll(new ResponsePrivate(player
								.getName() + " has used a Teleport Card"));
						return true;
					}
					return false;
				}
				if (card instanceof SpotlightCard) {
					if (rules.useItemCardValidator(model, card)) {
						UseSpotlightCard.useCard(model, coordinate);
						controller.writeToAll(new ResponsePrivate(player
								.getName() + " has used a Teleport Card"));

						return true;
					}
					return false;
				}
			}
		}

		// handle WAITING_FAKE_NOISE
		if (turnPhase == TurnPhase.WAITING_FAKE_NOISE) {

			// do fake noise

			if (a instanceof ActionFakeNoise) {
				Coordinate target = ((ActionFakeNoise) a).getCoordinate();
				if (model.getMap().getSectors().keySet().contains(target)) {
					FakeNoise.fakeNoise(model, target);
					model.setTurnPhase(TurnPhase.DRAWN_CARD);
					return true;
				}
				return false;
			}
		}

		// handle DRAWN_CARD
		if (turnPhase == TurnPhase.DRAWN_CARD) {

			// end turn

			if (a instanceof ActionEndTurn) {
				endTurn(controller, model, player);
				return true;
			}

			// use item card

			if (a instanceof ActionUseCard) {
				ItemCard card = ((ActionUseCard) a).getItemCard();
				Coordinate coordinate = ((ActionUseCard) a).getCoordinate();
				if (card instanceof TeleportCard) {
					if (rules.useItemCardValidator(model, card)) {
						UseTeleportCard.useCard(model);
						controller.writeToAll(new ResponsePrivate(player
								.getName() + " has used a Teleport card"));

						return true;
					}
					return false;
				}
				if (card instanceof SpotlightCard) {
					if (rules.useItemCardValidator(model, card)) {
						UseSpotlightCard.useCard(model, coordinate);
						controller.writeToAll(new ResponsePrivate(player
								.getName() + " has used a Spotlight Card"));

						return true;
					}
					return false;
				}
			}
		}
		// other cases
		return false;
	}

	private static void endTurn(Controller controller, Model model,
			Player player) {
		EndTurn.endTurn(model);
		controller.writeToAll(new ResponsePrivate(player.getName()
				+ " has finished his turn"));
//		StateMachine.endTurnMessage(controller, model, player);

	}

	/**
	 * Method used to handle an attack move
	 * @param rules
	 * @param model
	 * @param controller
	 * @return Whether the attack was allowed or not.
	 */
	private static boolean attackMove(Rules rules, Model model, Controller controller) {
		if (rules.attackValidator(model) == true) {
			Attack attack = new Attack(model);
			attack.makeAttack();
			model.setTurnPhase(TurnPhase.ATTACK_DONE);
			// controller.writeToAll(new
			// ResponsePrivate(player.getName()
			// + " has attacked in " + player.getLastPosition()));
			List<Player> victims = attack.getVictims();
			for (Player p : victims) {
				controller.writeToAll(new ResponsePrivate(p.getName()
						+ " has been killed!"));
			}
			return true;
		}
		return false;
	}
	
	private static void endTurnMessage(Controller controller, Model model,
			Player player) {
		controller.writeToAll(new ResponsePrivate("Next player is: "
				+ model.getCurrentPlayerReference().getName()));
		controller.writeToPlayer(model.getCurrentPlayerReference(),
				new ResponsePrivate("IT'S YOUR TURN"));
		controller.writeToPlayer(model.getCurrentPlayerReference(),
				new ResponsePrivate(model.getCurrentPlayerReference()
						.toString()));
	}
}
