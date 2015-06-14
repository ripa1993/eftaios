package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.model.map.GameMapName;
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
	/**
	 * 
	 */
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
	/**
	 * The chosen map.
	 */
	protected GameMapName mapName;

	/**
	 * The constructor sets the player name and the chosen map, ready to be sent
	 * to the server.
	 * 
	 * @param playerName
	 * @param mapName
	 */
	public ConnectionManager(String playerName, GameMapName mapName) {
		this.playerName = playerName;
		this.nameSet = false;
		this.clientID = 0;
		this.clientData = new ClientData();
		this.mapName = mapName;
	}

	/**
	 * Changes the player name
	 * 
	 * @param name
	 *            player name
	 */
	public void setPlayerName(String name) {
		this.playerName = name;
	}

	/**
	 * Method used to setup a connection, implemented according to a specific
	 * protocol.
	 */
	public abstract void setup();

	/**
	 * Changes the client id
	 * 
	 * @param clientID
	 *            client id
	 */
	public void setclientID(int clientID) {
		this.clientID = clientID;
	}

	/**
	 * Used to get the client id
	 * 
	 * @return the cliend id
	 */
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

	/**
	 * Used to get the client data
	 * 
	 * @return the client data
	 */
	public ClientData getClientData() {
		return clientData;
	}

	/**
	 * Changes the client data
	 * 
	 * @param clientData
	 *            new client data
	 */
	public void setClientData(ClientData clientData) {
		this.clientData = clientData;
	}

}
