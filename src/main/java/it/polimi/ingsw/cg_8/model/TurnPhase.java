package it.polimi.ingsw.cg_8.model;
/**
 * GAME_SETUP: Phase in which players are joining the newly created game<br>
 * GAME_END: Phase in which the game is ended<br>
 * TURN_BEGIN: Beginning of a player's turn<br>
 * MOVEMENT_PHASE: Player is choosing where to move<br>
 * ATTACK_PHASE: Player is choosing where to attack<br>
 * TURN_END: Player has finished his turn<br>
 * 
 * @author Simone
 *
 */
public enum TurnPhase {
	GAME_SETUP, TURN_BEGIN, MOVEMENT_PHASE, ATTACK_PHASE, TURN_END, GAME_END;
}
