package it.polimi.ingsw.cg_8.model.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.EscapeHatchCard;
import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.GreenEhCard;
import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.RedEhCard;

public class EscapeHatchCardTest {

    EscapeHatchCard c1;
    EscapeHatchCard c2;

    @Before
    public void init() {
        c1 = new GreenEhCard();
        c2 = new RedEhCard();
    }

    @Test
    public void EhCardTest1() {
        assertTrue(c1 instanceof GreenEhCard);

    }

    @Test
    public void EhCardTest2() {
        assertTrue(c2 instanceof RedEhCard);

    }
}
