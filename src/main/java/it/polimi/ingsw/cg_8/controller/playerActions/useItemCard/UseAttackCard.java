package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;

/**
 * Action that uses an attack card. It allows a player to attack even if he is a
 * human
 * 
 * @author Simone
 * @version 1.0
 */
public class UseAttackCard extends UseItemCard {

	/**
	 * Enable attack to current player
	 * 
	 * @param model
	 *            game
	 */
	public static void useCard(Model model) {
		Player currentPlayer = model.getCurrentPlayerReference();
		// Assume that the player is a human by hypothesis
		Human currentPlayerCharacter = (Human) currentPlayer.getCharacter();

		currentPlayerCharacter.enableAttack();

	}
}
