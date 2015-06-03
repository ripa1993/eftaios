package it.polimi.ingsw.cg_8.view.server;

import java.io.Serializable;

public class ResponseChat implements ServerResponse, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4477669407364086500L;
	private final String playerName;
	private final String message;

	public ResponseChat(String playerName, String message) {
		this.playerName = playerName;
		this.message = message;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return playerName + ": " + message;
	}
}
