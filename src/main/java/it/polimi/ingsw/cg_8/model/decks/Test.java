package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.decks.deckCreators.EscapeHatchDeckCreator;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.ItemDeckCreator;

public class Test {

	
	private static ItemDeck itemDeck;
	private static EscapeHatchDeck ehDeck;
	private static CharacterDeck charDeck3;
	private static ItemDeckCreator itemCreator;
	private static EscapeHatchDeckCreator ehCreator;
	
	
		public static void main(String[] args) {
			itemCreator = new ItemDeckCreator();
			itemDeck = itemCreator.createDeck();
	
			
			System.out.println(itemDeck.toString());
			itemDeck.shuffle();
			System.out.println(itemDeck.toString());
			
			
			ehCreator = new EscapeHatchDeckCreator();
			ehDeck = ehCreator.createDeck();
			
			System.out.println(ehDeck.toString());
			ehDeck.shuffle();
			System.out.println(ehDeck.toString());
			
		}
		
		
}
