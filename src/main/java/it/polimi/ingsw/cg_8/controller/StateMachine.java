package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.controller.playerActions.Attack;
import it.polimi.ingsw.cg_8.controller.playerActions.DrawDangerousSectorCard;
import it.polimi.ingsw.cg_8.controller.playerActions.EndTurn;
import it.polimi.ingsw.cg_8.controller.playerActions.FakeNoise;
import it.polimi.ingsw.cg_8.controller.playerActions.Movement;
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
import it.polimi.ingsw.cg_8.view.client.actions.ActionMove;
import it.polimi.ingsw.cg_8.view.client.actions.ActionUseCard;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

public class StateMachine {
	Model model;
	Controller controller;
	Player currentPlayer;
	TurnPhase turnPhase;
	Rules rules;

	public StateMachine(Controller controller) {
		this.model = controller.getModel();
		this.controller = controller;
		this.currentPlayer = model.getCurrentPlayerReference();
		this.turnPhase = model.getTurnPhase();
		this.rules = controller.getRules();
	}

	public boolean evaluateAction(ClientAction a, Player player) {
		// handles chat and disconnect action, always true
		if (a instanceof ActionChat){
			// TODO: send the message to all the player
			return true;
		}
		
		if (a instanceof ActionDisconnect){
			//TODO: handle this
			player.setDisconnected();
			return true;
		}
		
		// if not currentPlayer, then refuse all actions
		if (!currentPlayer.equals(player)) {
			return false;
		}
		// handle TURN_BEGIN
		if (turnPhase == TurnPhase.TURN_BEGIN) {
			if (a instanceof ActionMove) {
				// validate movement
				Coordinate destination = ((ActionMove) a).getCoordinate();
				if (rules.MovementValidator(model, destination)) {
					// execute movement
					new Movement(model, destination).makeMove();
					;
					Sector destinationSector = model.getMap().getSectors()
							.get(destination);
					if (destinationSector instanceof DangerousSector) {
						model.setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);
					} else {
						model.setTurnPhase(TurnPhase.MOVEMENT_DONE_NOT_DS);
					}
					return true;
				}
				return false;

			}
			if (a instanceof ActionUseCard) {
				ItemCard card = ((ActionUseCard) a).getItemCard();
				Coordinate coordinate = ((ActionUseCard) a).getCoordinate();
				if (card instanceof AdrenalineCard) {
					if (rules.UseItemCardValidator(model, card)) {
						UseAdrenalineCard.useCard(model);
						return true;
					}
					return false;
				}

				if (card instanceof AttackCard) {
					if (rules.UseItemCardValidator(model, card)) {
						UseAttackCard.useCard(model);
						return true;
					}
					return false;
				}
				if (card instanceof TeleportCard) {
					if (rules.UseItemCardValidator(model, card)) {
						UseTeleportCard.useCard(model);
						return true;
					}
					return false;
				}
				if (card instanceof SedativesCard) {
					if (rules.UseItemCardValidator(model, card)) {
						UseSedativesCard.useCard(model);
						return true;
					}
					return false;
				}
				if (card instanceof SpotlightCard) {
					if (rules.UseItemCardValidator(model, card)) {
						UseSpotlightCard.useCard(model, coordinate);
						return true;
					}
					return false;
				}

			}
			return false;
		}
		// handle MOVEMENT_DONE_NOT_DS
		if (turnPhase == TurnPhase.MOVEMENT_DONE_NOT_DS) {
			if (a instanceof ActionAttack) {
				if (rules.AttackValidator(model)) {
					new Attack(model).makeAttack();
					model.setTurnPhase(TurnPhase.ATTACK_DONE);
					return true;
				}
				return false;
			}
			if (a instanceof ActionEndTurn) {
				EndTurn.endTurn(model);
				model.setTurnPhase(TurnPhase.TURN_END);
			}
			if (a instanceof ActionUseCard) {
				if (a instanceof ActionUseCard) {
					ItemCard card = ((ActionUseCard) a).getItemCard();
					Coordinate coordinate = ((ActionUseCard) a).getCoordinate();

					if (card instanceof AttackCard) {
						if (rules.UseItemCardValidator(model, card)) {
							UseAttackCard.useCard(model);
							return true;
						}
						return false;
					}
					if (card instanceof TeleportCard) {
						if (rules.UseItemCardValidator(model, card)) {
							UseTeleportCard.useCard(model);
							return true;
						}
						return false;
					}
					if (card instanceof SedativesCard) {
						if (rules.UseItemCardValidator(model, card)) {
							UseSedativesCard.useCard(model);
							return true;
						}
						return false;
					}
					if (card instanceof SpotlightCard) {
						if (rules.UseItemCardValidator(model, card)) {
							UseSpotlightCard.useCard(model, coordinate);
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
			if (a instanceof ActionAttack) {
				if (rules.AttackValidator(model)) {
					new Attack(model).makeAttack();
					model.setTurnPhase(TurnPhase.ATTACK_DONE);
					return true;
				}
				return false;
			}
			if (a instanceof ActionDrawCard) {
				if (DrawDangerousSectorCard.drawDangerousSectorCard(model)) {
					model.setTurnPhase(TurnPhase.WAITING_FAKE_NOISE);
				} else {
					model.setTurnPhase(TurnPhase.DRAWN_CARD);
				}
				return true;
			}
			if (a instanceof ActionUseCard) {
				if (a instanceof ActionUseCard) {
					ItemCard card = ((ActionUseCard) a).getItemCard();
					Coordinate coordinate = ((ActionUseCard) a).getCoordinate();

					if (card instanceof AttackCard) {
						if (rules.UseItemCardValidator(model, card)) {
							UseAttackCard.useCard(model);
							return true;
						}
						return false;
					}
					if (card instanceof TeleportCard) {
						if (rules.UseItemCardValidator(model, card)) {
							UseTeleportCard.useCard(model);
							return true;
						}
						return false;
					}
					if (card instanceof SedativesCard) {
						if (rules.UseItemCardValidator(model, card)) {
							UseSedativesCard.useCard(model);
							return true;
						}
						return false;
					}
					if (card instanceof SpotlightCard) {
						if (rules.UseItemCardValidator(model, card)) {
							UseSpotlightCard.useCard(model, coordinate);
							return true;
						}
						return false;
					}
				}
			}

		}

		// handle ATTACK_PHASE
		if (turnPhase == TurnPhase.ATTACK_DONE) {
			if (a instanceof ActionEndTurn) {
				EndTurn.endTurn(model);
				model.setTurnPhase(TurnPhase.TURN_END);
			}
			if (a instanceof ActionUseCard) {
				ItemCard card = ((ActionUseCard) a).getItemCard();
				Coordinate coordinate = ((ActionUseCard) a).getCoordinate();
				if (card instanceof TeleportCard) {
					if (rules.UseItemCardValidator(model, card)) {
						UseTeleportCard.useCard(model);
						return true;
					}
					return false;
				}
				if (card instanceof SpotlightCard) {
					if (rules.UseItemCardValidator(model, card)) {
						UseSpotlightCard.useCard(model, coordinate);
						return true;
					}
					return false;
				}
			}
		}
		// handle WAITING_FAKE_NOISE
		if (turnPhase == TurnPhase.WAITING_FAKE_NOISE) {
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
			if (a instanceof ActionEndTurn) {
				EndTurn.endTurn(model);
				model.setTurnPhase(TurnPhase.TURN_END);
			}
			if (a instanceof ActionUseCard) {
				ItemCard card = ((ActionUseCard) a).getItemCard();
				Coordinate coordinate = ((ActionUseCard) a).getCoordinate();
				if (card instanceof TeleportCard) {
					if (rules.UseItemCardValidator(model, card)) {
						UseTeleportCard.useCard(model);
						return true;
					}
					return false;
				}
				if (card instanceof SpotlightCard) {
					if (rules.UseItemCardValidator(model, card)) {
						UseSpotlightCard.useCard(model, coordinate);
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
