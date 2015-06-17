package it.polimi.ingsw.cg_8.controller.playerActions.otherActions;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_8.controller.playerActions.other.GetCards;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.item.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.item.ItemCard;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GetCardsTest {
    Model model;

    @Before
    public void init() throws GameAlreadyRunningException,
            NotAValidMapException, EmptyDeckException {
        model = new Model(GameMapName.FERMI);
        model.addPlayer("a");
        model.addPlayer("b");
        model.initGame();
    }

    @Test
    public void testGetHeldCards() {
        ItemCard card = new AttackCard();
        List<ItemCard> list = new ArrayList<ItemCard>();
        list.add(card);
        model.getCurrentPlayerReference().getHand().addItemCard(card);
        assertEquals(list,
                GetCards.getHeldCards(model.getCurrentPlayerReference()));
    }

    @Test
    public void testPrintHeldCards() {
        assertEquals("[]",
                GetCards.printHeldCards(model.getCurrentPlayerReference()));
    }
}
