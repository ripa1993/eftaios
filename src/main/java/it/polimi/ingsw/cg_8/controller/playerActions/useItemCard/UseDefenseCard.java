package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;

/**
 * Action that uses a defense card. It allows a human to defend himself during
 * an attack
 * 
 * @author Simone
 *
 */
public class UseDefenseCard extends UseItemCard {

	/**
	 * Defends a player
	 * 
	 * @param player
	 *            player to be defended
	 */

	public static void useCard(Player player) {
		// Assume that the player is a human by hypothesis
		Human currentPlayerCharacter = (Human) player.getCharacter();
		currentPlayerCharacter.enableDefend();
	}

}
