package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerGameRoomInterface extends Remote {

	
	public boolean makeAction(int clientId, ClientAction action) throws RemoteException;
	
	
}
