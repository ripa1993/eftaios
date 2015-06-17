package it.polimi.ingsw.cg_8.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.characterCards.AlienCard;
import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class InGameCharacterTest {
	CharacterCard cc;
	Player player;
	Alien alien;

	@Before
	public void init() {
		cc = new AlienCard("Ciao", "test", "boh");
		player = new Player("test");
		Alien alien = new Alien(cc);
		player.init(alien, new Coordinate(0, 0));
	}

	@Test
	public void charCardTest() {
		assertTrue(player.getCharacter().getCharacterCard() instanceof CharacterCard);
	}

	@Test
	public void toStringTest() {
		assertTrue(player.getCharacter().toString() instanceof String);
	}
}
