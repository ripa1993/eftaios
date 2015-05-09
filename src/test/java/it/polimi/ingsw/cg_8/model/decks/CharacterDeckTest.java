package it.polimi.ingsw.cg_8.model.decks;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;

import org.junit.Before;
import org.junit.Test;


/* Contains most of the test regarding the Deck class, along with tests specific for the CharacterDeck class.
 * This is required as the Deck class is abstract and cannot be initialized.
 */

public class CharacterDeckTest {
	
	private CharacterDeck charDeck;
	private CharacterDeckCreator charCreator;
	
	
	@Before
	public void init() {
		charCreator = new CharacterDeckCreator();
		charDeck = charCreator.createDeck();
	}
	
	
	@Test 
	public void charDeckTest1() {
		boolean check;
		System.out.println(charDeck.toString());
		if (charDeck.drawCard() instanceof CharacterCard) {
			check = true;
		}
		else check = false;
		assertEquals(check, true);
	}
	
	@Test 
	public void equalsTest() {
		assertEquals(charDeck, charDeck);
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
		assertEquals(check, true);
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
		assertEquals(check, true);
		
	}
	
}
