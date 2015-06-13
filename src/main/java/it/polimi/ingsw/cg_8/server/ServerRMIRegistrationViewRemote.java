package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMIRegistrationViewRemote extends Remote {
	/**
	 * The client gets a new clientId, if it doesn't already have one. The
	 * method is called at the beginning of the registration process.
	 */
	public int getClientId(int clientId) throws RemoteException, AlreadyBoundException;
	/**
	 * Method used by the client to communicate its name to the server, before
	 * the start of the game.
	 */
	public boolean sendPlayerName(String string) throws RemoteException, AlreadyBoundException;
	/**
	 * Creates a {@link ServerGameRoom GameRoom} for the client so that it can
	 * play the game. Add the client to a client list, so that the server can
	 * identify it.
	 */
	public ServerGameRoomInterface register(SubscriberInterface client) throws RemoteException,
	AlreadyBoundException;
	/**
	 * Method used by the client to communicate its chosen map to the server,
	 * which will process the vote.
	 */
	public void sendMapVote(GameMapName chosenMap) throws RemoteException, AlreadyBoundException;
}
