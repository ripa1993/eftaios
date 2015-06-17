package it.polimi.ingsw.cg_8.view.server;

import java.io.Serializable;

/**
 * Server response used by the server to communicate chat messages to all the
 * players
 * 
 * @author Simone
 * @version 1.0
 */
public class ResponseChat implements ServerResponse, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4477669407364086500L;
	/**
	 * Sender's name
	 */
	private final String playerName;
	/**
	 * Sender's message
	 */
	private final String message;

	/**
	 * Builds a server response chat with the given player name and message
	 * 
	 * @param playerName
	 *            sender's name
	 * @param message
	 *            sender's message
	 */
	public ResponseChat(String playerName, String message) {
		this.playerName = playerName;
		this.message = message;
	}

	/**
	 * 
	 * @return sender's name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * 
	 * @return sender's message
	 */
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return playerName + ": " + message;
	}
}
