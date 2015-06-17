package it.polimi.ingsw.cg_8.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class RoundTest {

	Round testRound;
	Round testRound2;

	@Before
	public void init() {
		testRound = new Round(10, new Coordinate(11, 2));
		testRound2 = new Round(10, new Coordinate(14, 2));
	}

	@Test
	public void testGetNumber() {
		assertEquals(testRound.getNumber(), 10);
	}

	@Test
	public void testGetCoordinate() {
		assertEquals(testRound.getCoordinate(), new Coordinate(11, 2));
	}

	@Test
	public void testEquals() {
		assertFalse(testRound.equals(testRound2));
	}

}
