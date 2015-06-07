package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.client.ClientData;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.io.Serializable;

public abstract class ConnectionManager implements Serializable {

	private static final long serialVersionUID = -1347635297531821083L;
	protected String playerName;
	protected int clientID;
	protected boolean nameSet;
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

	public abstract void setup();

	public void setclientID(int clientID) {
		this.clientID = clientID;
	}

	public int getclientID() {
		return this.clientID;
	}

	public abstract void send(ClientAction inputLine);

	public ClientData getClientData() {
		return clientData;
	}

	public void setClientData(ClientData clientData) {
		this.clientData = clientData;
	}

}
