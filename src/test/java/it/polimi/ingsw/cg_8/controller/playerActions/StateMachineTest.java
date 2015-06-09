package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.DefaultRules;
import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.controller.StateMachine;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.actions.ActionAttack;
import it.polimi.ingsw.cg_8.view.client.actions.ActionEndTurn;
import it.polimi.ingsw.cg_8.view.client.actions.ActionMove;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.util.Observable;

import org.junit.Before;
import org.junit.Test;

public class StateMachineTest {

	private class DummyController extends Controller {

		public DummyController(GameMapName mapName, Rules rules) {
			super(mapName, rules);
		}

		@Override
		public void writeToAll(ServerResponse message) {
		}

		@Override
		public void writeToId(Integer id, ServerResponse message) {
		}

		@Override
		public void writeToPlayer(Player player, ServerResponse message) {
		}

		@Override
		public void update(Observable o, Object arg) {

		}

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
			StateMachine.evaluateAction(controller, new ActionEndTurn(), currPlayer);
			currPlayer = controller.getModel().getCurrentPlayerReference();
		}
		StateMachine.evaluateAction(controller, new ActionMove(new Coordinate(
				12, 7)), currPlayer);
		result = StateMachine.evaluateAction(controller, new ActionAttack(), currPlayer);
		assertTrue(result);
	}

}
