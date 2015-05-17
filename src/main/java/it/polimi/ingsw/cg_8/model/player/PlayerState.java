package it.polimi.ingsw.cg_8.model.player;
/**
 * Allowed player state for a player
 * @author Simone
 *
 */
public enum PlayerState {
	/**
	 * waiting for his turn
	 */
	ALIVE_WAITING,
	/**
	 * playing his turn
	 */
	ALIVE_PLAYING,
	/**
	 * player is dead
	 */
	DEAD,
	/**
	 * player is joining the game
	 */
	CONNECTING,
	/**
	 * player has left the game
	 */
	DISCONNECTED;
}
