package it.polimi.ingsw.cg_8.model.cards.itemCards;

import it.polimi.ingsw.cg_8.model.cards.Card;
import java.io.Serializable;

/**
 * Abstract item card, extended by {@link AdrenalineCard}, {@link AttackCard},
 * {@link DefenseCard}, {@link SedativesCard}, {@link SpotlightCard} and
 * {@link TeleportCard}
 * 
 * @author Simone
 *
 */
public abstract class ItemCard extends Card implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = -2439914640637572125L;

    /**
     * Constructor
     */
    public ItemCard() {
        super();
    }
}
