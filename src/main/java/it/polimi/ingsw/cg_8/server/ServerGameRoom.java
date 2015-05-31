package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.ClientRMI;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerGameRoom extends ServerPublisher implements
		ServerGameRoomInterface {

	private ClientRMI clientRMI;

	protected ServerGameRoom(ClientRMI client) throws RemoteException {
		UnicastRemoteObject.exportObject(this, 7777);
		this.clientRMI = client;
	}

	@Override
	public void makeAction(Player player, ClientAction action) throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispatchMessage(ServerResponse message) {

	}
}
