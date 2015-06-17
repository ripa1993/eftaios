package it.polimi.ingsw.cg_8.view.server;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.io.Serializable;

/**
 * Used to notify the player about his state, through string representing his
 * name, class, state and position on the map.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ResponseState implements ServerResponse, Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 6811457673145213295L;
    /**
     * Player name
     */
    private final String playerName;
    /**
     * Player character
     */
    private final String character;
    /**
     * Player state
     */
    private final String state;
    /**
     * Player position
     */
    private final Coordinate position;
    /**
     * Round number
     */
    private final int roundNumber;

    /**
     * Constructor
     * 
     * @param playerName
     *            player name
     * @param character
     *            player character
     * @param state
     *            player state
     * @param position
     *            player position
     * @param roundNumber
     *            player round number
     */
    public ResponseState(String playerName, String character, String state,
            Coordinate position, int roundNumber) {
        this.playerName = playerName;
        this.character = character;
        this.state = state;
        this.position = position;
        this.roundNumber = roundNumber;
    }

    /**
     * 
     * @return player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * 
     * @return player character
     */
    public String getCharacter() {
        return character;
    }

    /**
     * 
     * @return player state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @return player position
     */
    public Coordinate getPosition() {
        return position;
    }

    /**
     * 
     * @return round number
     */
    public String getRoundNumber() {
        return String.valueOf(roundNumber);
    }

    @Override
    public String toString() {
        return "Your State - Player: " + playerName + ", Character: "
                + character + ", State: " + state + ", Position: " + position
                + ", Round Number: " + roundNumber;
    }

}
