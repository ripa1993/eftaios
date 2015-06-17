package it.polimi.ingsw.cg_8.model.decks;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.model.cards.escapehatch.EscapeHatchCard;
import it.polimi.ingsw.cg_8.model.decks.creators.EscapeHatchDeckCreator;
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
