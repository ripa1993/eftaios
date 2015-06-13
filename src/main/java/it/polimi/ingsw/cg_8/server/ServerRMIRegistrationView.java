package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	 * Log4j logger
	 */
	private static final Logger logger = LogManager
			.getLogger(ServerRMIRegistrationView.class);

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
		logger.info("ClientId is: " + clientId);
		if (clientId == 0) {
			Integer newClientId = Server.getClientId();
			logger.info("Assigning new ClientId: " + newClientId);
			Server.increaseClientId();
			return newClientId;
		}
		logger.debug("ClientId already assigned");
		return clientId;
	}

	/**
	 * Method used by the client to communicate its name to the server, before
	 * the start of the game.
	 */
	@Override
	public boolean sendPlayerName(String name) throws RemoteException,
			AlreadyBoundException {

		logger.debug("Name accepted: " + name);
		return true;
	}

	/**
	 * Method used by the client to communicate its chosen map to the server,
	 * which will process the vote.
	 */
	@Override
	public void sendMapVote(GameMapName chosenMap) throws RemoteException,
	AlreadyBoundException {
		logger.debug("Vote given to " + chosenMap);
		Server.addVote(chosenMap);	
	}
	
	/**
	 * Creates a {@link ServerGameRoom GameRoom} for the client so that it can
	 * play the game. Add the client to a client list, so that the server can
	 * identify it.
	 */
	@Override
	public ServerGameRoomInterface register(SubscriberInterface client)
			throws RemoteException, AlreadyBoundException {
		ServerGameRoom view = new ServerGameRoom(client);

		try {
			serverRMI.addRMIClient(client, view);
		} catch (GameAlreadyRunningException e) {
			logger.error("Game already running, can't add the player to this game");
		}
		return view;

	}

}
