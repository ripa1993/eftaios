package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.fail;

import java.net.Socket;
import java.util.Observable;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.DefaultRules;
import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.server.ServerSocketPublisherThread;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

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
		controller = new DummyController(GameMapName.GALILEI,
				new DefaultRules());
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
	public void test() {
		fail("Not yet implemented");
	}

}
