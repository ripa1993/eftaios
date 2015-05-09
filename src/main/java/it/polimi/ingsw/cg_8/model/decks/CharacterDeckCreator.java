package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.characterCards.AlienCard;
import it.polimi.ingsw.cg_8.model.cards.characterCards.HumanCard;

import java.util.ArrayList;
import java.util.List;

public class CharacterDeckCreator extends DeckCreator {

	@Override
	public CharacterDeck createDeck() {
		CharacterDeck charDeck = new CharacterDeck();
		
		charDeck.addCard(new HumanCard("Ennio Maria Dominoni",null,"The Captain"));
		charDeck.addCard(new HumanCard("Julia Niguloti","Cabal","The Pilot"));
		charDeck.addCard(new HumanCard("Silvano Porpora",null,"The Psychologist"));
		charDeck.addCard(new HumanCard("Tuccio Brendon","Piri","The Soldier"));
		charDeck.addCard(new AlienCard("Piero Ceccarella",null,"The First Alien"));
		charDeck.addCard(new AlienCard("Vittorio Martana",null,"The Second Alien"));
		charDeck.addCard(new AlienCard("Maria Galbani",null,"The Third Alien"));
		charDeck.addCard(new AlienCard("Paolo Landon",null,"The Fourth Alien"));
		
		return charDeck;
	}


	@Override
	protected List<Card> createCardList() {
		return new ArrayList<Card>();
	}
}
