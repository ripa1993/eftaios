package it.polimi.ingsw.cg_8.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AlienTest {
	static Alien normalAlien;
	static Alien fedAlien;
	static Player player1;
	static Player player2;
	
	@BeforeClass
	public static void init(){
		player1 = new Player("player1");
		player2 = new Player("player2");
		normalAlien = new Alien(player1);
		fedAlien = new Alien(player2);
		fedAlien.feedAlien();
	}
	
	// testing normal behaviour
	@Test
	public void getMaxAllowedMovement() {
		assertEquals(normalAlien.getMaxAllowedMovement(),2);
	}
	@Test
	public void isAttackAllowed(){
		assertTrue(normalAlien.isAttackAllowed());
	}
	@Test
	public void isDefendAllowed(){
		assertFalse(normalAlien.isDefendAllowed());
	}
	@Test
	public void hasToDrawSectorCard(){
		assertTrue(normalAlien.hasToDrawSectorCard());
	}
	// testing fed behaviour
	@Test
	public void getMaxAllowedMovement2() {
		assertEquals(fedAlien.getMaxAllowedMovement(),3);
	}
	@Test
	public void isAttackAllowed2(){
		assertTrue(fedAlien.isAttackAllowed());
	}
	@Test
	public void isDefendAllowed2(){
		assertFalse(fedAlien.isDefendAllowed());
	}
	@Test
	public void hasToDrawSectorCard2(){
		assertTrue(fedAlien.hasToDrawSectorCard());
	}
	// testing equals
	@Test
	public void equals(){
		assertFalse(normalAlien.equals(fedAlien));
	}

}
