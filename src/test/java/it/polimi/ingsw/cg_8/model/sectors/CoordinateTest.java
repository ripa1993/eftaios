package it.polimi.ingsw.cg_8.model.sectors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {

	Coordinate c;

	@Before
	public void init() {
		c = new Coordinate(10, 20);
		System.out.println(c);
	}

	@Test
	public void converterTest() {
		assertEquals("K21", c.toString());
	}
}
