package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.player.Player;

import java.util.List;

/**
 * Return the cards held by the player.
 * @author Alberto Parravicini
 *
 */
public class GetCards extends PlayerAction {

	/**
	 * 
	 * @param player The player performing the action
	 * @return The cards held by the player
	 */
	public static List<ItemCard> getHeldCards(Player player) {
		return player.getHand().getHeldCards();
	}
	
}
