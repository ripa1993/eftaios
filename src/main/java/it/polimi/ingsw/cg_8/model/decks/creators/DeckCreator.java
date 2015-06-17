package it.polimi.ingsw.cg_8.model.decks.creators;

import it.polimi.ingsw.cg_8.model.decks.Deck;

/**
 * Abstract deck creator
 * 
 * @author Simone
 * @version 1.0
 */
public abstract class DeckCreator {
    /**
     * Populates the deck
     * 
     * @return a complete deck
     */
    public abstract Deck createDeck();

}
