package it.polimi.ingsw.cg_8.model.decks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.server.ServerSocketPublisherThread;

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
		if (isDeckEmpty() == true) {
			try {
				this.reshuffle();
			} catch (EmptyDeckException e) {
				// never happens
				LOGGER.error(e.getMessage());
			}
		}
		return this.getCards().remove(0);
	}
}
