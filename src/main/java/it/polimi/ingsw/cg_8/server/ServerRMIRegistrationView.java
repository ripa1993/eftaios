package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.ClientRMI;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

/**
 * Class that handles the registration of a client to a game; it creates its own
 * view and associate the client with the game it is playing.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ServerRMIRegistrationView implements
		ServerRMIRegistrationViewRemote {

	/**
	 * Required as i want to associate the clients with their respective
	 * controller (unique for a certain game).
	 */
	private ServerRMI serverRMI;

	public ServerRMIRegistrationView(ServerRMI server) {
		this.serverRMI = server;
	}


	/**
	 * The client gets a new clientId, if it doesn't already have one. The
	 * method is called at the beginning of the registration process.
	 */
	@Override
	public int getClientId(int clientId) throws RemoteException,
			AlreadyBoundException {

		if (clientId == 0) {
			Integer newClientId = Server.getClientId();
			System.out.println("Assigning new ClientId: " + newClientId);
			Server.increaseClientId();
			return newClientId;
		}
		System.out.println("ClientId already assigned");
		return clientId;
	}

	/**
	 * Method used by the client to communicate its name to the server, before
	 * the start of the game.
	 */
	@Override
	public boolean sendPlayerName(String name) throws RemoteException,
			AlreadyBoundException {

		String playerName = name;
		System.out.println("NAME ACCEPTED");
		return true;
	}
	

	/**
	 * Creates a {@link ServerGameRoom GameRoom} for the client so that it can
	 * play the game.
	 * Add the client to a client list, so that the server can identify it.
	 */
	@Override
	public ServerGameRoomInterface register(ClientRMI client)
			throws RemoteException, AlreadyBoundException {
		ServerGameRoom view = new ServerGameRoom(client);
		
		try {
			serverRMI.addRMIClient(client, view);
		} catch (GameAlreadyRunningException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return view;

	}

}
