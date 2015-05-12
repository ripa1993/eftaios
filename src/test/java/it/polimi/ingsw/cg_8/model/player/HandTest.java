package it.polimi.ingsw.cg_8.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;

import org.junit.Before;
import org.junit.Test;

public class HandTest {
	Hand hand;

	@Before
	public void init() {
		hand = new Hand();
		hand.addItemCard(new AdrenalineCard());
	}

	@Test
	public void testGetHeldCards() {
		assertTrue((hand.getHeldCards()).get(0) instanceof AdrenalineCard);
	}

	@Test
	public void testGetCard() {
		assertTrue((hand.getCard(0) instanceof AdrenalineCard));
	}

	@Test
	public void testAddItemCard() {
		hand.addItemCard(new SpotlightCard());
		hand.addItemCard(new SpotlightCard());
		hand.addItemCard(new AdrenalineCard());
		assertEquals(hand.getHeldCards().size(), 3);
	}

}
