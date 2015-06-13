package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.noises.MovementNoise;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class FakeNoiseTest {
	Model model;

	@Before
	public void init() {

		try {
			model = new Model(GameMapName.FERMI);
			model.addPlayer("A");
			model.addPlayer("B");
			model.addPlayer("C");
			model.initGame();
		} catch (EmptyDeckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotAValidMapException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GameAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFakeNoise() {
		FakeNoise.fakeNoise(model, new Coordinate(1, 1));
		MovementNoise noise = (MovementNoise) model.getNoiseLogger().get(0);
		assertEquals(noise.getCoordinate(), new Coordinate(1, 1));
	}

}
