package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;

/**
 * Action that uses an adrenaline card. It allows a human to walk longer
 * distance
 * 
 * @author Simone
 * 
 */
public class UseAdrenalineCard extends UseItemCard {

	/**
	 * Enables adrenaline to current player
	 * 
	 * @param model
	 *            game
	 */
	public static void useCard(Model model) {
		// TODO: change method to getCharacter
		Player currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
		// Assume that the player is a human by hypothesis
		Human currentPlayerCharacter = (Human) currentPlayer.getCharacter();
		
		currentPlayerCharacter.enableAdrenaline();

	}
}
