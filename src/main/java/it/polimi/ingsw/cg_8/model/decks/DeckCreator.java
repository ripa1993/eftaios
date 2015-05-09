package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.cards.Card;

import java.util.List;

public abstract class DeckCreator {

	public abstract Deck createDeck();
	
	protected abstract List<Card> createCardList();
	
}
