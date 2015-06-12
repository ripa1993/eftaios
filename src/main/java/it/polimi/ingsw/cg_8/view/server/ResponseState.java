package it.polimi.ingsw.cg_8.view.server;

import java.io.Serializable;

/**
 * Used to notify the player about his state, through string representing his
 * name, class, state and position on the map.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ResponseState implements ServerResponse, Serializable {

	private static final long serialVersionUID = 6811457673145213295L;

	private final String playerName;
	private final String character;
	private final String state;
	private final String position;
	private final int roundNumber;

	public ResponseState(String playerName, String character, String state,
			String position, int roundNumber) {
		this.playerName = playerName;
		this.character = character;
		this.state = state;
		this.position = position;
		this.roundNumber = roundNumber;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getCharacter() {
		return character;
	}

	public String getState() {
		return state;
	}

	public String getPosition() {
		return position;
	}

	public String getRoundNumber() {
		return String.valueOf(roundNumber);
	}

	@Override
	public String toString() {
		return "ResponseState [playerName=" + playerName + ", character="
				+ character + ", state=" + state + ", position=" + position
				+ ", roundNumber=" + roundNumber + "]";
	}

}
