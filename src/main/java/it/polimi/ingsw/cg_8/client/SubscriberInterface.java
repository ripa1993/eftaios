package it.polimi.ingsw.cg_8.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SubscriberInterface extends Remote  {

	public void publishMessage(String action) throws RemoteException;

}
