package it.polimi.ingsw.cg_8.model.decks;

public class Test {

	
	private static CharacterDeck charDeck;
	private static CharacterDeck charDeck2;
	private static CharacterDeck charDeck3;
	private static CharacterDeckCreator charCreator;
	
	
		public static void main(String[] args) {
			charCreator = new CharacterDeckCreator();
			charDeck = charCreator.createDeck();
			charDeck2 = charCreator.createDeck();
			charDeck3 = charCreator.createDeck();
			
			System.out.println(charDeck.toString());
			System.out.println(charDeck2.toString());
			System.out.println(charDeck2.equals(charDeck));
		}
		
		
}
