package it.polimi.ingsw.cg_8.model.player;
/**
 * Allowed player state for a player
 * @author Simone
 *
 */
public enum PlayerState {
	/**
	 * the player is alive, either playing or waiting for his turn
	 */
	ALIVE,
	/**
	 * player is dead
	 */
	DEAD,
	/**
	 * The player is escaped through an Escape hatch
	 */
	ESCAPED,
	/**
	 * player is joining the game
	 */
	CONNECTING,
	/**
	 * player has left the game
	 */
	DISCONNECTED;
}
