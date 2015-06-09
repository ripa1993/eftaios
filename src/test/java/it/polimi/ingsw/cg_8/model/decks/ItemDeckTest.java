package it.polimi.ingsw.cg_8.model.decks;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.ItemDeckCreator;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;

import org.junit.Before;
import org.junit.Test;

public class ItemDeckTest {

	ItemDeck items;
	ItemDeck items2;
	ItemDeckCreator idc;

	@Before
	public void init() {
		idc = new ItemDeckCreator();
		items = idc.createDeck();
		items2 = idc.createDeck();
	}

	@Test
	public void testDrawCard() {
		try {
			assertTrue(items.drawCard() instanceof ItemCard);
		} catch (EmptyDeckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testEquals() {
		Card card;
		try {
			card = items.drawCard();
			items.addCard(card);
			items.equals(items2);
		} catch (EmptyDeckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
