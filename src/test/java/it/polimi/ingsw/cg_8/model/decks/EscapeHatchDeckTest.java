package it.polimi.ingsw.cg_8.model.decks;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.EscapeHatchCard;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.EscapeHatchDeckCreator;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;

import org.junit.Before;
import org.junit.Test;

public class EscapeHatchDeckTest {

	EscapeHatchDeck cards;
	EscapeHatchDeckCreator creator;

	@Before
	public void init() {
		creator = new EscapeHatchDeckCreator();
		cards = creator.createDeck();
	}

	@Test
	public void testConstructor() throws EmptyDeckException {
		assertTrue(cards.drawCard() instanceof EscapeHatchCard);
	}

}
