package it.polimi.ingsw.cg_8.model.cards.itemCards;

import it.polimi.ingsw.cg_8.model.cards.Card;

/**
 * Abstract item card, extended by {@link AdrenalineCard}, {@link AttackCard},
 * {@link DefenseCard}, {@link SedativesCard}, {@link SpotlightCard} and
 * {@link TeleportCard}
 * 
 * @author Simone
 *
 */
public abstract class ItemCard extends Card {
	/**
	 * Constructor
	 */
	public ItemCard() {
		super();
	}
}
