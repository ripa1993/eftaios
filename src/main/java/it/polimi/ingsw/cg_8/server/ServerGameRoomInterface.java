package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * Contains the methods usable by the client after registering (and thus after
 * the start of the game) The client after registering gets a reference to the
 * class that implements this interface.
 * 
 * @author Alberto Parravicini
 * @verision 1.0
 */
public interface ServerGameRoomInterface extends Remote {

	/**
	 * 
	 * @param clientId The client Id
	 * @param action The action sent by the client
	 * @return Whether the action was accepted or not.
	 * @throws RemoteException
	 */
	public boolean makeAction(int clientId, ClientAction action) throws RemoteException;
	
	
}
