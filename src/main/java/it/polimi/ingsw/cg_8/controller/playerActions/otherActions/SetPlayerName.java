package it.polimi.ingsw.cg_8.controller.playerActions.otherActions;

import it.polimi.ingsw.cg_8.controller.playerActions.PlayerAction;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;

/**
 * Set the player's name when he's added to a new game.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class SetPlayerName implements PlayerAction {

	/**
	 * Set the player name
	 * 
	 * @param playerName
	 *            The name that the player chooses
	 * @param model
	 *            The current state of the game
	 * @throws GameAlreadyRunningException
	 */
	public static void setPlayerName(String playerName, Model model)
			throws GameAlreadyRunningException {
		model.addPlayer(playerName);
	}
}
