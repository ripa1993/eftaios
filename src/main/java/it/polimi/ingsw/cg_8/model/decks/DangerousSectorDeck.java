package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Dangerous sector deck
 * 
 * @author Simone
 * @version 1.0
 */
public class DangerousSectorDeck extends Deck {
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager
	        .getLogger(DangerousSectorDeck.class);

	@Override
	public Card drawCard() {
		if (isDeckEmpty()) {
			try {
				this.reshuffle();
			} catch (EmptyDeckException e) {
				// never happens
				LOGGER.error(e.getMessage(), e);
			}
		}
		return this.getCards().remove(0);
	}
}
