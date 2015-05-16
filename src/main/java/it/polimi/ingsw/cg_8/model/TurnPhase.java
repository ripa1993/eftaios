package it.polimi.ingsw.cg_8.model;
/**
 * Phases in a game's turn
 * @author Simone
 *
 */
public enum TurnPhase {
	/**
	 * Phase in which players are joining the newly created game
	 */
	GAME_SETUP,
	/**
	 * Beginning of a player's turn
	 */
	TURN_BEGIN,
	/**
	 * Player is choosing where to move
	 */
	MOVEMENT_PHASE,
	/**
	 * Player is choosing where to attack
	 */
	ATTACK_PHASE,
	/**
	 * Player has finished his turn
	 */
	TURN_END,
	/**
	 * Phase in which the game is ended
	 */
	GAME_END;
}
