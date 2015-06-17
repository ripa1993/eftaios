package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface used by the RMI connection, used by the server to call methods on
 * the client
 * 
 * @author Simone
 * @version 1.0
 */
public interface SubscriberInterface extends Remote {
    /**
     * Sends a ServerResponse message to the server
     * 
     * @param message
     *            ServerResponse message
     * @throws RemoteException
     */
    public void publishMessage(ServerResponse message) throws RemoteException;

    /**
     * Used to get the client id
     * 
     * @return the client id
     * @throws RemoteException
     */
    public int getClientId() throws RemoteException;

    /**
     * Used to get the player name
     * 
     * @return the player name
     * @throws RemoteException
     */
    public String getPlayerName() throws RemoteException;

}
