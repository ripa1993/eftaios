package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubscriberInterface extends Remote  {

	public void publishMessage(ServerResponse message) throws RemoteException;

}
