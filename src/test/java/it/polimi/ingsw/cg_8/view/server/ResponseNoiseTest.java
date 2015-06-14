package it.polimi.ingsw.cg_8.view.server;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.noises.MovementNoise;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.player.*;

import org.junit.Before;
import org.junit.Test;

public class ResponseNoiseTest {
	ResponseNoise response;
	MovementNoise noise;

	@Before
	public void init() {
		noise = new MovementNoise(1, new Player("test"), new Coordinate(1, 1));
		response = new ResponseNoise(noise);
	}

	@Test
	public void test() {
		assertEquals(noise, response.getNoise());
	}

}
