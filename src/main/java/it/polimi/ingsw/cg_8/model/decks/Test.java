package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.decks.deckCreators.DangerousSectorDeckCreator;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.EscapeHatchDeckCreator;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.ItemDeckCreator;

public class Test {

	
	private static ItemDeck itemDeck;
	private static EscapeHatchDeck ehDeck;
	private static DangerousSectorDeck dangDeck;
	private static DangerousSectorDeckCreator dangCreator;
	private static EscapeHatchDeckCreator ehCreator;
	
	
		public static void main(String[] args) {
			dangCreator = new  DangerousSectorDeckCreator();
			dangDeck = dangCreator.createDeck();
	
			
			System.out.println(dangDeck.toString());
			
			ehCreator = new EscapeHatchDeckCreator();
			ehDeck = ehCreator.createDeck();
			
			System.out.println(ehDeck.toString());
			ehDeck.shuffle();
			System.out.println(ehDeck.toString());
			
		}
		
		
}
