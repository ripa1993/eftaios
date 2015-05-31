package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.ClientRMI;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMIRegistrationViewRemote extends Remote {

	public ServerGameRoomInterface register(ClientRMI client) throws RemoteException,
	AlreadyBoundException;

	public int getClientId(int clientId) throws RemoteException, AlreadyBoundException;
	
	public boolean sendPlayerName(String string) throws RemoteException, AlreadyBoundException;
}
