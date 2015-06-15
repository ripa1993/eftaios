package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.DefaultRules;
import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.controller.StateMachine;
import it.polimi.ingsw.cg_8.controller.playerActions.otherActions.GetAllowedActions;
import it.polimi.ingsw.cg_8.model.TurnPhase;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.noises.MovementNoise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.actions.ActionAttack;
import it.polimi.ingsw.cg_8.view.client.actions.ActionChat;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDisconnect;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDrawCard;
import it.polimi.ingsw.cg_8.view.client.actions.ActionEndTurn;
import it.polimi.ingsw.cg_8.view.client.actions.ActionFakeNoise;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetAvailableAction;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetReachableCoordinates;
import it.polimi.ingsw.cg_8.view.client.actions.ActionMove;
import it.polimi.ingsw.cg_8.view.client.actions.ActionUseCard;
import it.polimi.ingsw.cg_8.view.server.ResponseNoise;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.util.Observable;

import org.junit.Before;
import org.junit.Test;

public class StateMachineTest {

	private class DummyController extends Controller {

		public ServerResponse response;

		public DummyController(GameMapName mapName, Rules rules) {
			super(mapName, rules);
		}

		@Override
		public void writeToAll(ServerResponse message) {
			response = message;
		}

		@Override
		public void writeToId(Integer id, ServerResponse message) {
			response = message;

		}

		@Override
		public void writeToPlayer(Player player, ServerResponse message) {
			response = message;
		}

		// @Override
		// public void update(Observable o, Object arg) {
		//
		// }

	}

	Controller controller;

	@Before
	public void init() {
		controller = new DummyController(GameMapName.FERMI, new DefaultRules());
		try {
			controller.getModel().addPlayer("sim");
			controller.getModel().addPlayer("sim2");
			controller.getModel().addPlayer("sim3");
			controller.initGame();
		} catch (GameAlreadyRunningException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void validMoveTest() {
		Player p = controller.getModel().getCurrentPlayerReference();
		boolean result = false;
		if (p.getCharacter() instanceof Human) {
			result = StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 10)), p);
		} else {
			result = StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), p);
		}
		assertTrue(result);
	}

	@Test
	public void notValidMoveTest() {
		Player p = controller.getModel().getCurrentPlayerReference();
		boolean result = false;
		if (p.getCharacter() instanceof Human) {
			result = StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 11)), p);
		} else {
			result = StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 8)), p);
		}
		assertFalse(result);
	}

	@Test
	public void notValidAttackTest() {
		Player p = controller.getModel().getCurrentPlayerReference();
		boolean result = true;

		result = StateMachine.evaluateAction(controller, new ActionAttack(), p);
		assertFalse(result);
	}

	@Test
	public void alienValidAttackTest() {

		boolean result = false;
		/**
		 * Cycles players until it finds an Alien.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Human) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 10)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		StateMachine.evaluateAction(controller, new ActionMove(new Coordinate(
				12, 7)), currPlayer);
		result = StateMachine.evaluateAction(controller, new ActionAttack(),
				currPlayer);
		assertTrue(result);
	}

	@Test
	public void chatTest() {
		Player p = controller.getModel().getCurrentPlayerReference();
		boolean result = false;

		result = StateMachine.evaluateAction(controller, new ActionChat(
				"asdasdasd"), p);
		assertTrue(result);
	}

	@Test
	public void actionTest() {
		Player p = controller.getModel().getCurrentPlayerReference();
		boolean result = false;

		result = StateMachine.evaluateAction(controller,
				new ActionGetAvailableAction(), p);
		assertTrue(result);
	}

	@Test
	public void coordinateTest() {
		Player p = controller.getModel().getCurrentPlayerReference();
		boolean result = false;

		result = StateMachine.evaluateAction(controller,
				new ActionGetReachableCoordinates(), p);
		assertTrue(result);
	}

	@Test
	public void alienCardUsageTest() {

		boolean result = false;
		/**
		 * Cycles players until it finds an Alien.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Human) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 10)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		currPlayer.getHand().addItemCard(new SedativesCard());
		result = StateMachine.evaluateAction(controller, new ActionUseCard(
				new SedativesCard()), currPlayer);
		assertFalse(result);
	}

	@Test
	public void adrenalineUsageTest1() {

		boolean result = false;
		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		currPlayer.getHand().addItemCard(new AdrenalineCard());
		result = StateMachine.evaluateAction(controller, new ActionUseCard(
				new AdrenalineCard()), currPlayer);
		assertTrue(result);
	}

	@Test
	public void adrenalineUsageTest2() {

		boolean result = false;
		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		currPlayer.getHand().addItemCard(new AdrenalineCard());
		StateMachine.evaluateAction(controller, new ActionMove(new Coordinate(
				12, 10)), currPlayer);
		result = StateMachine.evaluateAction(controller, new ActionUseCard(
				new AdrenalineCard()), currPlayer);
		assertFalse(result);
	}

	@Test
	public void spotlightUsageTest() {

		boolean result = false;
		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		currPlayer.getHand().addItemCard(new SpotlightCard());
		result = StateMachine.evaluateAction(controller, new ActionUseCard(
				new SpotlightCard(), new Coordinate(12, 10)), currPlayer);
		assertTrue(result);
	}

	@Test
	public void spotlightUsageTest2() {

		boolean result = false;
		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		currPlayer.getHand().addItemCard(new SpotlightCard());
		StateMachine.evaluateAction(controller, new ActionMove(new Coordinate(
				12, 10)), currPlayer);
		result = StateMachine.evaluateAction(controller, new ActionUseCard(
				new SpotlightCard(), new Coordinate(12, 10)), currPlayer);
		assertTrue(result);
	}

	@Test
	public void sedativesUsageTest() {

		boolean result = false;
		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		currPlayer.getHand().addItemCard(new SedativesCard());
		result = StateMachine.evaluateAction(controller, new ActionUseCard(
				new SedativesCard()), currPlayer);
		assertTrue(result);
	}

	@Test
	public void sedativesUsageTest2() {

		boolean result = false;
		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		currPlayer.getHand().addItemCard(new SedativesCard());
		StateMachine.evaluateAction(controller, new ActionMove(new Coordinate(
				12, 10)), currPlayer);
		result = StateMachine.evaluateAction(controller, new ActionUseCard(
				new SedativesCard()), currPlayer);
		assertTrue(result);
	}

	@Test
	public void attackUsageTest() {

		boolean result = false;
		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		currPlayer.getHand().addItemCard(new AttackCard());
		result = StateMachine.evaluateAction(controller, new ActionUseCard(
				new AttackCard()), currPlayer);
		assertTrue(result);
	}

	@Test
	public void teleportUsageTest() {

		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		currPlayer.getHand().addItemCard(new TeleportCard());
		StateMachine.evaluateAction(controller, new ActionMove(new Coordinate(
				12, 10)), currPlayer);
		StateMachine.evaluateAction(controller, new ActionUseCard(
				new TeleportCard()), currPlayer);

		assertTrue(currPlayer.getLastPosition().equals(new Coordinate(11, 9)));
	}

	@Test
	public void endTurnTest() {

		boolean result = false;
		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		StateMachine.evaluateAction(controller, new ActionMove(new Coordinate(
				12, 10)), currPlayer);
		result = StateMachine.evaluateAction(controller, new ActionEndTurn(),
				currPlayer);
		assertTrue(result);
	}

	/**
	 * Check that it isn't possible to end the turn before having moved.
	 */
	@Test
	public void endTurnTest2() {

		boolean result = true;
		/**
		 * Cycles players until it finds a Human.
		 */
		Player currPlayer = controller.getModel().getCurrentPlayerReference();
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {

			StateMachine.evaluateAction(controller, new ActionMove(
					new Coordinate(12, 7)), currPlayer);
			StateMachine.evaluateAction(controller, new ActionEndTurn(),
					currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		result = StateMachine.evaluateAction(controller, new ActionEndTurn(),
				currPlayer);
		assertFalse(result);
	}

	@Test
	public void getHandTest() {
		boolean result = StateMachine.evaluateAction(controller,
				new ActionGetAvailableAction(), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void notCurrentPlayerTest() {
		Player oldPlayer = controller.getModel().getCurrentPlayerReference();
		EndTurn.endTurn(controller.getModel());
		boolean result = StateMachine.evaluateAction(controller,
				new ActionMove(new Coordinate(1, 1)), oldPlayer);
		assertFalse(result);
	}

	@Test
	public void teleportTest() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().setTurnPhase(TurnPhase.TURN_BEGIN);
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new TeleportCard());
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new TeleportCard()), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void attackTest() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new AttackCard());
		controller.getModel().setTurnPhase(TurnPhase.MOVEMENT_DONE_NOT_DS);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new AttackCard()), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void attackTestDS() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Human) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionAttack(), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void attackDrawCardDS() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Human) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionDrawCard(), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void attackUseCardDSAlien() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Human) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new TeleportCard()), controller.getModel()
						.getCurrentPlayerReference());
		assertFalse(result);
	}

	@Test
	public void attackUseCardTeleportDS() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new TeleportCard());
		controller.getModel().setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new TeleportCard()), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void attackUseCardSpotlightDS() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new SpotlightCard());
		controller.getModel().setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new SpotlightCard(), new Coordinate(6, 6)),
				controller.getModel().getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void attackUseCardSedativesDS() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new SedativesCard());
		controller.getModel().setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new SedativesCard()), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void attackUseCardAttackDS() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new AttackCard());
		controller.getModel().setTurnPhase(TurnPhase.MOVEMENT_DONE_DS);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new AttackCard()), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void endTurnAttackPhase() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new AttackCard());
		controller.getModel().setTurnPhase(TurnPhase.ATTACK_DONE);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionEndTurn(), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void attackUseCardTeleportAtk() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new TeleportCard());
		controller.getModel().setTurnPhase(TurnPhase.ATTACK_DONE);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new TeleportCard()), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void attackUseCardSpotlightAtk() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new SpotlightCard());
		controller.getModel().setTurnPhase(TurnPhase.ATTACK_DONE);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new SpotlightCard(), new Coordinate(6, 6)),
				controller.getModel().getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void waitingFakeNoiseTest() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new SpotlightCard());
		controller.getModel().setTurnPhase(TurnPhase.WAITING_FAKE_NOISE);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionFakeNoise(new Coordinate(9, 9)), controller
						.getModel().getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void endTurnDrawnCard() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new SpotlightCard());
		controller.getModel().setTurnPhase(TurnPhase.DRAWN_CARD);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionEndTurn(), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void useCardTeleportDrawnCard() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new TeleportCard());
		controller.getModel().setTurnPhase(TurnPhase.DRAWN_CARD);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new TeleportCard()), controller.getModel()
						.getCurrentPlayerReference());
		assertTrue(result);
	}

	@Test
	public void useCardSpotlightDrawnCard() {
		while (controller.getModel().getCurrentPlayerReference().getCharacter() instanceof Alien) {
			EndTurn.endTurn(controller.getModel());
		}
		controller.getModel().getCurrentPlayerReference()
				.setPosition(new Coordinate(1, 1));
		controller.getModel().getCurrentPlayerReference().getHand()
				.addItemCard(new SpotlightCard());
		controller.getModel().setTurnPhase(TurnPhase.DRAWN_CARD);
		boolean result = StateMachine.evaluateAction(controller,
				new ActionUseCard(new SpotlightCard(), new Coordinate(6, 6)),
				controller.getModel().getCurrentPlayerReference());
		assertTrue(result);
	}

	
}
