package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.cards.Card;

/**
 * Abstract card that is drawn when a player ends a turn in a dangerous sector,
 * it is extended by {@link SilenceCard}, {@link NormalNoise} and
 * {@link NoiseDecorator}
 * 
 * @author Simone
 *
 */
public abstract class DangerousSectorCard extends Card {
	/**
	 * Constructor
	 */
	public DangerousSectorCard() {
		super();
	}
}
