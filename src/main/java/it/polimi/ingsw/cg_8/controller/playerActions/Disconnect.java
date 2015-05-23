package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;

/**
 * Disconnect the player, unless he is connecting or he is already disconnected.
 * 
 * @author Alberto Parravicini
 *
 */
public class Disconnect extends PlayerAction {
	/**
	 * 
	 * @param player The player who wants to leave the game.
	 */
	public static void disconnect(Player player) {
		if (player.getState() != PlayerState.DISCONNECTED
				|| player.getState() != PlayerState.CONNECTING) {
			player.setDisconnected();
		}
	}
}
