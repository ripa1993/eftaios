package it.polimi.ingsw.cg_8.model.noise;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_8.model.noises.DefenseNoise;
import it.polimi.ingsw.cg_8.model.noises.EscapeSectorNoise;
import it.polimi.ingsw.cg_8.model.noises.MovementNoise;
import it.polimi.ingsw.cg_8.model.noises.SpotlightNoise;
import it.polimi.ingsw.cg_8.model.noises.TeleportNoise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class OtherNoisesTest {

	private Player player;
	private Coordinate coordinate;

	@Before
	public void init() {
		player = new Player("a");
		coordinate = new Coordinate(0, 0);
	}

	@Test
	public void testEscapeSectorNoise() {
		EscapeSectorNoise noise = new EscapeSectorNoise(1, player, coordinate);
		assertEquals(1, noise.getTurnNumber());
	}

	@Test
	public void testMovementNoise() {
		MovementNoise noise = new MovementNoise(1, player, coordinate);
		assertEquals(1, noise.getTurnNumber());
	}

	@Test
	public void testTeleportNoise() {
		TeleportNoise noise = new TeleportNoise(1, player, coordinate);
		assertEquals(1, noise.getTurnNumber());
	}

	@Test
	public void testDefenseNoise() {
		DefenseNoise noise = new DefenseNoise(1, player, coordinate);
		assertEquals(1, noise.getTurnNumber());
	}

	@Test
	public void testSpotlightNoise() {
		SpotlightNoise noise = new SpotlightNoise(1, player, coordinate);
		assertEquals(1, noise.getTurnNumber());
	}
}
