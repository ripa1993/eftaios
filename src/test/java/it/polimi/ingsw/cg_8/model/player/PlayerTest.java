package it.polimi.ingsw.cg_8.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.characterCards.AlienCard;
import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;
import it.polimi.ingsw.cg_8.model.cards.characterCards.HumanCard;
import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	Player player;
	InGameCharacter alien;
	CharacterCard cc;
	Player player2;
	InGameCharacter human;
	CharacterCard cc2;
	Player player3;
	InGameCharacter human3;
	CharacterCard cc3;

	@Before
	public void init() {
		cc = new AlienCard("Ciao", "test", "boh");
		player = new Player("test");
		alien = new Alien(cc);
		player.init(alien, new Coordinate(0, 0));
		cc2 = new HumanCard("Ciao", "test", "boh");
		player2 = new Player("test2");
		human = new Human(cc2);
		player2.init(human, new Coordinate(0, 0));
		cc3 = new HumanCard("Ciao", "test", "boh");
		player3 = new Player("test3");
		human3 = new Human(cc2);
		player3.init(human3, new Coordinate(0, 0));
	}

	@Test
	public void testGetPosition() {
		assertEquals(new Coordinate(0, 0), player.getLastPosition());

	}

	@Test
	public void testSetPosition() {
		player.setPosition(new Coordinate(1, 1));
		assertEquals(new Coordinate(1, 1), player.getLastPosition());
	}

	@Test
	public void testGetState() {
		assertEquals(PlayerState.ALIVE_WAITING, player.getState());
	}

	@Test
	public void testGetName() {
		assertEquals("test", player.getName());
	}

	@Test
	public void testGetCharacter() {
		assertEquals(alien, player.getCharacter());
	}

	@Test
	public void testSetDisconnected() {
		player.setDisconnected();
		assertEquals(PlayerState.DISCONNECTED, player.getState());
	}

	@Test
	public void testSetDead() {
		player.setDead();
		assertEquals(PlayerState.DEAD, player.getState());
	}
	
	@Test 
	public void testSetEscaped() {
		player.setEscaped();
		assertEquals(PlayerState.ESCAPED, player.getState());
	}

	@Test
	public void testCycleState() {
		player.cycleState();
		assertEquals(PlayerState.ALIVE_PLAYING, player.getState());
	}

	@Test
	public void testCycleState2() {
		player.cycleState();
		player.cycleState();
		assertEquals(PlayerState.ALIVE_WAITING, player.getState());
	}

	@Test
	public void tesGetRoundNumber() {
		assertEquals(0, player.getRoundNumber());
	}

	@Test
	public void testEditLastPosition() {
		player.editLastPosition(new Coordinate(2, 2));
		assertEquals(player.getLastPosition(), new Coordinate(2, 2));
	}

	@Test
	public void testGetHand() {
		assertTrue(player.getHand() instanceof Hand);
	}

	@Test
	public void testEqualsNewObj() {
		assertFalse(player.equals(new Object()));
	}

	@Test
	public void testEqualsNull() {
		assertFalse(player.equals(null));
	}

	@Test
	public void testEqualsDiffObj() {
		assertFalse(player.equals(player2));
	}

	@Test
	public void testEqualsEqualObj() {
		assertTrue(player.equals(player));
	}

	@Test
	public void testEqualsIsConsistent() {
		assertTrue(player.equals(player2) == player.equals(player2));
	}

	@Test
	public void testEqualsIsTransitive() {
		cc = new AlienCard("Ciao", "test", "boh");
		player = new Player("test");
		alien = new Alien(cc);
		player.init(alien, new Coordinate(0, 0));
		cc2 = new AlienCard("Ciao", "test", "boh");
		player2 = new Player("test");
		human = new Alien(cc2);
		player2.init(human, new Coordinate(0, 0));
		cc3 = new AlienCard("Ciao", "test", "boh");
		player3 = new Player("test");
		human3 = new Alien(cc3);
		player3.init(human3, new Coordinate(0, 0));
		boolean result = false;
		boolean result2 = false;
		if (player.equals(player2) && player2.equals(player3)) {
			result = true;
		}
		if (player.equals(player3)) {
			result2 = true;
		}
		assertTrue(result == result2);
	}

	@Test
	public void testHashCode() {
		assertFalse(player.hashCode() == player2.hashCode());
	}
}
