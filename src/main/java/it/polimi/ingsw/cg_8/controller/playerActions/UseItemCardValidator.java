package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;

import java.util.List;

/**
 * When a player is trying to use a card, check if he actually has the card.
 * 
 * @author Alberto Parravicini
 *
 */
public class UseItemCardValidator {
	/**
	 * 
	 * @param model
	 *            The state of the game
	 * @param card
	 *            The card that the player is trying to use.
	 * @return Whether the card can be used or not.
	 */
	public static boolean validateItemCardUsage(Model model, ItemCard card) {

		if (model.getCurrentPlayerReference().getCharacter() instanceof Alien) {
			return false;
		}
		
		List<ItemCard> heldCards = model.getCurrentPlayerReference().getHand()
				.getHeldCards();
		for (ItemCard i : heldCards) {
			if ((card.getClass()).equals(i.getClass())) {
				return true;
			}
		}
		return false;

	}
}
