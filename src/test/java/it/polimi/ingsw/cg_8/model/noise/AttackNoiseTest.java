package it.polimi.ingsw.cg_8.model.noise;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.characterCards.AlienCard;
import it.polimi.ingsw.cg_8.model.noises.AttackNoise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class AttackNoiseTest {
	private AttackNoise noise;
	private Player player;

	@Before
	public void init() {
		player = new Player("Test");
		player.init(new Alien(new AlienCard("a", "b", "c")), new Coordinate(0,
				0));
		noise = new AttackNoise(1, player, new Coordinate(1, 1));
	}

	@Test
	public void testIsAlien() {
		assertTrue(noise.isAlien());
	}

	@Test
	public void testGetTurnNumber() {
		assertEquals(1, noise.getTurnNumber());
	}

	@Test
	public void testGetCoordinate() {
		assertEquals(new Coordinate(1, 1), noise.getCoordinate());
	}

	@Test
	public void testGetPlayer() {
		assertEquals(player, noise.getPlayer());
	}

	@Test
	public void testToString() {
		assertEquals("Attack noise: " + " Turn: " + "1" + " Player: " + "Test"
				+ " Coordinate: " + "B02" + " Character: " + "Alien",
				noise.toString());
	}

}
