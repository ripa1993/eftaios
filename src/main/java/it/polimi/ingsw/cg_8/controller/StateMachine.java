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
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

/**
 * Simulation of a state machine, used to handle {@link ClientAction} generated
 * by the client
 * 
 * @author Simone
 *
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

		// TODO: rendere tutte ( o alcune) delle classi non statiche per poter
		// estrarre delle informazioni, ad esempio dalla DrawDangerousSector
		// card si può chiamare il metodo getcard che restituisce la carta
		// appena pescata, cosi da poter comuinicare al client quale carta ha
		// pescato

		// local variables, calculated every time a new evaluation is launched

		Model model = controller.getModel();
		Player currentPlayer = model.getCurrentPlayerReference();
		TurnPhase turnPhase = model.getTurnPhase();
		Rules rules = controller.getRules();

		// handles chat and disconnect action, always true
		if (a instanceof ActionChat) {
			String message = ((ActionChat) a).getMessage();
			controller.writeToAll(new ResponseChat(player.getName(), message));
			return true;
		}

		if (a instanceof ActionDisconnect) {
			Disconnect.disconnect(player);
			model.nextPlayer();
			// model.getCurrentPlayerReference().cycleState();
			controller.writeToAll(new ResponsePrivate(player.getName()
					+ " has been disconnected."));
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
			}
			if (a instanceof ActionGetHand) {
				controller.writeToPlayer(player,
						new ResponsePrivate(GetCards.printHeldCards(player)));
			}
			if (a instanceof ActionGetAvailableAction) {
				controller.writeToPlayer(player, new ResponsePrivate(
						GetAllowedActions.printActions(player)));
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

			// attack

			if (a instanceof ActionAttack) {
				if (rules.attackValidator(model)) {
					new Attack(model).makeAttack();
					model.setTurnPhase(TurnPhase.ATTACK_DONE);
					controller.writeToAll(new ResponsePrivate(player.getName()
							+ " has attacked in " + player.getLastPosition()));
					return true;
				}
				return false;
			}

			// end turn

			if (a instanceof ActionEndTurn) {
				EndTurn.endTurn(model);
				controller.writeToAll(new ResponsePrivate(player.getName()
						+ " has finished his turn"));
				controller.writeToAll(new ResponsePrivate("Next player is: "
						+ model.getCurrentPlayerReference().getName()));

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

			// attack

			if (a instanceof ActionAttack) {
				if (rules.attackValidator(model)) {
					new Attack(model).makeAttack();
					model.setTurnPhase(TurnPhase.ATTACK_DONE);
					controller.writeToAll(new ResponsePrivate(player.getName()
							+ " has attacked in " + player.getLastPosition()));
					return true;
				}
				return false;
			}

			// draw card

			if (a instanceof ActionDrawCard) {
				if (DrawDangerousSectorCard.drawDangerousSectorCard(model)) {
					model.setTurnPhase(TurnPhase.WAITING_FAKE_NOISE);
				} else {
					model.setTurnPhase(TurnPhase.DRAWN_CARD);
					controller.writeToAll(new ResponsePrivate(player.getName()
							+ " has drawn a Dangerous Card"));
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
				EndTurn.endTurn(model);
				controller.writeToAll(new ResponsePrivate(player.getName()
						+ " has finished his turn"));
				controller.writeToAll(new ResponsePrivate("Next player is: "
						+ model.getCurrentPlayerReference().getName()));

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
				EndTurn.endTurn(model);
				controller.writeToAll(new ResponsePrivate(player.getName()
						+ " has finished his turn"));
				controller.writeToAll(new ResponsePrivate("Next player is: "
						+ model.getCurrentPlayerReference().getName()));
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
}
