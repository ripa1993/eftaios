package it.polimi.ingsw.cg_8.model.decks.deckCreators;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.decks.Deck;

import java.util.List;

public abstract class DeckCreator {

	public abstract Deck createDeck();
	
	protected abstract List<Card> createCardList();
	
}
