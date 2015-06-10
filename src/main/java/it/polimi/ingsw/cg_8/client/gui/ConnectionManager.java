package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.client.ClientData;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.io.Serializable;

/**
 * Class used to handle the connection between the client and the server. Its
 * methods can be inherited by other classes that implements various connection
 * protocol, such as RMI or Sockets.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public abstract class ConnectionManager implements Serializable {

	private static final long serialVersionUID = -1347635297531821083L;
	/**
	 * The name used to identify the player.
	 */
	protected String playerName;
	/**
	 * Value assigned by the server to univocally identify the player.
	 */
	protected int clientID;
	/**
	 * Value used to see if the server accepted the player name.
	 */
	protected boolean nameSet;
	/**
	 * Used to store the messages sent by the server.
	 */
	protected ClientData clientData;

	public ConnectionManager(String playerName) {
		this.playerName = playerName;
		nameSet = false;
		clientID = 0;
		clientData = new ClientData();
	}

	public ConnectionManager() {
		nameSet = false;
		clientID = 0;
		clientData = new ClientData();
	}

	public void setPlayerName(String name) {
		this.playerName = name;
	}

	/**
	 * Method used to setup a connection, implemented according to a specific
	 * protocol.
	 */
	public abstract void setup();

	public void setclientID(int clientID) {
		this.clientID = clientID;
	}

	public int getclientID() {
		return this.clientID;
	}

	/**
	 * Method used to send messages to the server, implemented according to a
	 * specific protocol.
	 * 
	 * @param inputLine
	 *            The action performed by the player.
	 */
	public abstract void send(ClientAction inputLine);

	public ClientData getClientData() {
		return clientData;
	}

	public void setClientData(ClientData clientData) {
		this.clientData = clientData;
	}

}
