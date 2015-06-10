package it.polimi.ingsw.cg_8.view.server;

import java.io.Serializable;

/**
 * Used to notify the player about his state.
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ResponseState implements ServerResponse, Serializable {

	private static final long serialVersionUID = 6811457673145213295L;
	
	/**
	 * The state of the player, expressed as a string.
	 */
	private final String playerState;

	public ResponseState(String playerState) {
		this.playerState = playerState;
	}
	
	/**
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return playerState;
	}

	@Override
	public String toString() {
		return "Server: " + playerState;
	}
}
