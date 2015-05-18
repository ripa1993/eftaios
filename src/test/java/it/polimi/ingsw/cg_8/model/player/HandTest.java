package it.polimi.ingsw.cg_8.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.exceptions.TooManyCardsException;

import org.junit.Before;
import org.junit.Test;

public class HandTest {
	Hand hand;

	@Before
	public void init() {
		hand = new Hand();
		try {
			hand.addItemCard(new AdrenalineCard());
		} catch (TooManyCardsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetHeldCards() {
		assertTrue((hand.getHeldCards()).get(0) instanceof AdrenalineCard);
	}

	@Test
	public void testGetCard() {
		assertTrue((hand.getCard(0) instanceof AdrenalineCard));
	}

	@Test(expected = TooManyCardsException.class)
	public void testAddItemCard() throws TooManyCardsException {
			hand.addItemCard(new SpotlightCard());
			hand.addItemCard(new SpotlightCard());
			hand.addItemCard(new AdrenalineCard());
		
	}

}
