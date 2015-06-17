package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.noises.TeleportNoise;
import it.polimi.ingsw.cg_8.model.player.Player;

/**
 * Action that uses a teleport card. It resets the human's position back to
 * human spawn
 * 
 * @author Simone
 * @version 1.0
 */
public class UseTeleportCard extends UseItemCard {
	/**
	 * Applies card effect to the model
	 * 
	 * @param model
	 *            reference to the game in which the card is used
	 */
	public static void useCard(Model model) {
		/**
		 * If player has already done Movement, it calls editLastPosition(),
		 * otherwise it calls setPosition.
		 */
		Player currentPlayer = model.getCurrentPlayerReference();
		int lastModelTurn = model.getRoundNumber();
		int lastPlayerTurn = currentPlayer.getRoundNumber();
		// case: player has not done a movement in this turn
		if (lastPlayerTurn == lastModelTurn - 1) {
			currentPlayer.setPosition(model.getMap().getHumanSpawn());
		}
		// case: player has already done its movement (e.g. normal movement,
		// teleport card)
		else if (lastPlayerTurn == lastModelTurn) {
			currentPlayer.editLastPosition(model.getMap().getHumanSpawn());
		}
		// make noise
		Noise teleportNoise = new TeleportNoise(model.getRoundNumber(),
		        currentPlayer, model.getMap().getHumanSpawn());
		model.addNoise(teleportNoise);

	}
}
