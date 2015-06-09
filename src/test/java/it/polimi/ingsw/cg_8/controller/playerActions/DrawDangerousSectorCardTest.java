package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NoiseCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.decks.ItemDeck;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;

import org.junit.Before;
import org.junit.Test;

public class DrawDangerousSectorCardTest {
	private Model model;

	@Before
	public void init() {
		try {
			model = new Model(GameMapName.FERMI);
			model.addPlayer("A");
			model.addPlayer("B");
			model.addPlayer("C");
			model.initGame();
		} catch (GameAlreadyRunningException | NotAValidMapException
				| EmptyDeckException e) {
			System.err.println(e.getMessage());
		}

	}

	@Test
	public void testGetDangerousSectorCard() {
		DrawDangerousSectorCard draw = new DrawDangerousSectorCard(model);
		boolean result = draw.drawDangerousSectorCard();
		boolean result2 = false;
		Card card = draw.getDangerousSectorCard();
		if (card instanceof NoiseCard) {
			result2 = ((NoiseCard) card).hasToMakeFakeNoise();
		}
		assertEquals(result, result2);
	}

	@Test
	public void testIsDiscardedItemCard() {
		Player currPlayer = model.getCurrentPlayerReference();
		currPlayer.getHand().addItemCard(new TeleportCard());
		currPlayer.getHand().addItemCard(new TeleportCard());
		currPlayer.getHand().addItemCard(new TeleportCard());
		boolean drawnItem = false;
		boolean result = false;
		do {
			DrawDangerousSectorCard draw = new DrawDangerousSectorCard(model);
			draw.drawDangerousSectorCard();
			Card card = draw.getDangerousSectorCard();
			if (card instanceof NoiseCard) {
				if (((NoiseCard) card).hasToDrawItem() == true) {
					drawnItem = true;
					result = draw.isDiscardedItemCard();
				}
			}
		}while (!drawnItem);
		assertTrue(result);	
	}



	@Test
	public void testIsEmptyItemDeck() {
		Player currPlayer = model.getCurrentPlayerReference();
		currPlayer.getHand().addItemCard(new TeleportCard());
		currPlayer.getHand().addItemCard(new TeleportCard());
		currPlayer.getHand().addItemCard(new TeleportCard());
		boolean drawnItem = false;
		boolean result = false;
		do {
			DrawDangerousSectorCard draw = new DrawDangerousSectorCard(model);
			draw.drawDangerousSectorCard();
			Card card = draw.getDangerousSectorCard();
			if (card instanceof NoiseCard) {
				if (((NoiseCard) card).hasToDrawItem() == true) {
					drawnItem = true;
					result = draw.isEmptyItemDeck();
				}
			}
		}while (!drawnItem);
		assertFalse(result);
	}

}
