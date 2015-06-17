package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;

/**
 * Item deck
 * 
 * @author Simone
 * @version 1.0
 */
public class ItemDeck extends Deck {

    @Override
    public Card drawCard() throws EmptyDeckException {
        if (isDeckEmpty()) {
            this.reshuffle();
        }
        return this.getCards().remove(0);
    }
}
