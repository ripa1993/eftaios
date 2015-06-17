package it.polimi.ingsw.cg_8.view.client.actions;

import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.io.Serializable;

/**
 * Action: use an item card
 * 
 * @author Simone
 *
 */
public class ActionUseCard implements ClientAction, Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 225938059463444849L;

    /**
     * Designated card
     */
    private final ItemCard itemCard;
    /**
     * Optional field, used only in spotlight card
     */
    private final Coordinate coordinate;

    /**
     * Constructor, used by every item card but spotlight
     * 
     * @param itemCard
     *            card to be used
     */
    public ActionUseCard(ItemCard itemCard) {
        this.itemCard = itemCard;
        this.coordinate = null;
    }

    /**
     * Constructor, used by spotlight card
     * 
     * @param itemCard
     *            card to be used
     * @param coordinate
     *            target coordinate
     */
    public ActionUseCard(ItemCard itemCard, Coordinate coordinate) {
        this.itemCard = itemCard;
        this.coordinate = coordinate;
    }

    /**
     * Getter
     * 
     * @return item card
     */

    public ItemCard getItemCard() {
        return this.itemCard;
    }

    /**
     * Getter
     * 
     * @return spotlight target coordinate
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}
