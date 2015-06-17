package it.polimi.ingsw.cg_8.model.decks;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.characterCards.AlienCard;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.DangerousSectorCard;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.SilenceCard;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.DangerousSectorDeckCreator;

import org.junit.Before;
import org.junit.Test;

public class DangerousDeckTest {

    DangerousSectorDeck cards;
    DangerousSectorDeckCreator creator;

    @Before
    public void init() {
        creator = new DangerousSectorDeckCreator();
        cards = creator.createDeck();
    }

    @Test
    public void testConstructor() {
        assertTrue(cards.drawCard() instanceof DangerousSectorCard);
    }

    @Test
    public void reshuffleTest() {
        Card c = new SilenceCard();
        for (int i = 0; i < 200; i++) {
            c = cards.drawCard();
            cards.addUsedCard(c);
        }
        assertTrue(c instanceof DangerousSectorCard);
    }
}
