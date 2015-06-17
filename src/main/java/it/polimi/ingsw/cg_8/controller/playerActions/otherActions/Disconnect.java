package it.polimi.ingsw.cg_8.controller.playerActions.otherActions;

import it.polimi.ingsw.cg_8.controller.playerActions.PlayerAction;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;

/**
 * Disconnect the player, unless he is connecting or he is already disconnected.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class Disconnect implements PlayerAction {
    /**
     * Constructor
     */
    private Disconnect() {

    }

    /**
     * Disconnect the player.
     * 
     * @param player
     *            The player who wants to leave the game.
     */
    public static void disconnect(Player player) {
        if (player.getState() != PlayerState.DISCONNECTED
                || player.getState() != PlayerState.CONNECTING) {
            player.setDisconnected();
        }
    }
}
