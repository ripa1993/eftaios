package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.cards.Card;
/**
 * Dangerous sector deck
 * @author Simone
 *
 */
public class DangerousSectorDeck extends Deck {

	@Override
	public Card drawCard() {
		if (isDeckEmpty() == true) {
			this.reshuffle();		
		} 
		return this.getCards().remove(0);
	}
}
