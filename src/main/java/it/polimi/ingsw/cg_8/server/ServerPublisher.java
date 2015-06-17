package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.view.server.ServerResponse;

/**
 * Contains the methods used by the server to communicate with the client.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public abstract class ServerPublisher {

	/**
	 * Used by the server to send messages to the client.
	 * 
	 * @param message
	 *            A {@link ServerResponse} given by the server.
	 */
	public abstract void dispatchMessage(ServerResponse message);
}
