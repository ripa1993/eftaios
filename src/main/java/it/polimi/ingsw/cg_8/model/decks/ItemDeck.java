package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.cards.Card;
/**
 * Item deck
 * @author Simone
 *
 */
public class ItemDeck extends Deck {

	@Override
	public Card drawCard() {
		if (isDeckEmpty() == true) {
			this.reshuffle();		
		} 
		return this.getCards().remove(0);
	}
}
