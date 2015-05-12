package it.polimi.ingsw.cg_8.model.sectors;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchBehaviour;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;

import org.junit.Before;
import org.junit.Test;

public class EscapeHatchSectorTest {

	EscapeHatchSector ehs;
	
	@Before
	public void init(){
		ehs = new EscapeHatchSector(10, 20, 4);
	}
	
	
	@Test
	public void testGetX() {
		assertEquals(10, ehs.getX());
	}
	@Test
	public void testGetY(){
		assertEquals(20, ehs.getY());
	}
	@Test
	public void testAllowEscape(){
		assertTrue(ehs.allowEscape());
	}
	@Test
	public void testAllowEscape2(){
		assertFalse(ehs.allowEscape() == ehs.allowEscape());
	}
	
	@Test
	public void testGetNumber(){
		assertEquals(4, ehs.getNumber());
	}

	@Test
	public void testGetStatus(){
		assertTrue(ehs.getStatus() instanceof EscapeHatchBehaviour);
	}
}
