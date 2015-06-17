package it.polimi.ingsw.cg_8.model;

/**
 * Phases in a game's turn
 * 
 * @author Simone
 * @version 1.0
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
     * Player hasn't moved into a dangerous sector
     */
    MOVEMENT_DONE_NOT_DS,

    /**
     * Player has moved into a dangerous sector
     */
    MOVEMENT_DONE_DS,

    /**
     * Player has attacked
     */
    ATTACK_DONE,

    /**
     * Waiting for the player to communicate location of fake noise, after
     * having drawn a Fake Noise card
     */
    WAITING_FAKE_NOISE,

    /**
     * Player has drawn a card after having moved.
     */
    DRAWN_CARD,

    /**
     * Player has finished his turn
     */
    TURN_END,

    /**
     * Phase in which the game is ended
     */
    GAME_END;
}
