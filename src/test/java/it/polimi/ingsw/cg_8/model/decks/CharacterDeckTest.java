package it.polimi.ingsw.cg_8.model.decks;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.CharacterDeckCreator;

import org.junit.Before;
import org.junit.Test;


/* Contains most of the test regarding the Deck class, along with tests specific for the CharacterDeck class.
 * This is required as the Deck class is abstract and cannot be initialized.
 */

public class CharacterDeckTest {
	
	private CharacterDeck charDeck;
	private CharacterDeck charDeck2;
	private CharacterDeck charDeck3;
	private CharacterDeckCreator charCreator;
	
	
	@Before
	public void init() {
		charCreator = new CharacterDeckCreator();
		charDeck = charCreator.createDeck();
		charDeck2 = charCreator.createDeck();
		charDeck3 = charCreator.createDeck();
	}
	
	
	@Test 
	public void charDeckTest1() {
		boolean check;
		if (charDeck.drawCard() instanceof CharacterCard) {
			check = true;
		}
		else check = false;
		assertTrue(check);
	}
	
	@Test 
	public void equalsTest() {
		assertTrue(charDeck3.equals(charDeck2));
	}
	@Test
	public void equalsTest2() {
		charDeck.drawCard();
		assertFalse(charDeck.equals(charDeck2));		
	}
	
	@Test
	public void hashFunctionTest() {
		assertEquals(charDeck.hashCode(), charDeck2.hashCode());
	}
	
	@Test 
	public void shuffleTest() {
		boolean check = false;
		charDeck.shuffle();
		while (charDeck.getCards().isEmpty() == false) {
			if (charDeck.drawCard() instanceof CharacterCard) {
				check = true;
			}
			else check = false;
		}
		assertTrue(check);
	}
	
	@Test
	public void isDeckEmptyTest() {
		boolean check = false;
		while (charDeck.getCards().isEmpty() == false) {
			charDeck.drawCard();
			}
		if (charDeck.isDeckEmpty() == true) {
			check = true; 
			}
		assertTrue(check);
		
	}
	@Test 
	public void addUsedCardTest() {
		Card c1 = charDeck.drawCard();
		charDeck.addUsedCard(c1);
		assertFalse(charDeck.getUsedCards().isEmpty());
	}
	
}
