package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
/**
 * Dangerous sector deck
 * @author Simone
 *
 */
public class DangerousSectorDeck extends Deck {

	@Override
	public Card drawCard() {
		if (isDeckEmpty() == true) {
			try {
				this.reshuffle();
			} catch (EmptyDeckException e) {
				// never happens
				System.err.println(e.getMessage());
			}		
		} 
		return this.getCards().remove(0);
	}
}
