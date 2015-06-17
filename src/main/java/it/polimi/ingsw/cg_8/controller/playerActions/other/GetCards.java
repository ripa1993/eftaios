package it.polimi.ingsw.cg_8.controller.playerActions.other;

import it.polimi.ingsw.cg_8.controller.playerActions.PlayerAction;
import it.polimi.ingsw.cg_8.model.cards.item.ItemCard;
import it.polimi.ingsw.cg_8.model.player.Player;

import java.util.List;

/**
 * Return the cards held by the player.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class GetCards implements PlayerAction {
    /**
     * Constructor
     */
    private GetCards() {

    }

    /**
     * This method gives the hand of the player as a list of ItemCard
     * 
     * @param player
     *            The player performing the action
     * @return The cards held by the player
     */
    public static List<ItemCard> getHeldCards(Player player) {
        return player.getHand().getHeldCards();
    }

    /**
     * This method gives the hand of the player as a string
     * 
     * @param player
     *            the player performing the action
     * @return the cards held by the player
     */
    public static String printHeldCards(Player player) {
        return player.getHand().getHeldCards().toString();
    }
}
