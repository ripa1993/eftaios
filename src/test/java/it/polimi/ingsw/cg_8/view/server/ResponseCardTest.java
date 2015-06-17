package it.polimi.ingsw.cg_8.view.server;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ResponseCardTest {
	ResponseCard response;

	@Before
	public void init() {
		List<ItemCard> list = new ArrayList<ItemCard>();
		list.add(new TeleportCard());
		list.add(new SpotlightCard());
		list.add(new AttackCard());
		response = new ResponseCard(list);
	}

	@Test
	public void testGetHand() {
		assertEquals("TeleportCard SpotlightCard AttackCard",
		        response.getHand());
	}

	@Test
	public void testGetCard1() {
		assertEquals("TeleportCard", response.getCard1());
	}

	@Test
	public void testGetCard2() {
		assertEquals("SpotlightCard", response.getCard2());
	}

	@Test
	public void testGetCard3() {
		assertEquals("AttackCard", response.getCard3());
	}

	@Test
	public void testNullCard() {
		ResponseCard response2 = new ResponseCard(new ArrayList<ItemCard>());
		assertEquals("No Card", response2.getCard1());
	}
}
