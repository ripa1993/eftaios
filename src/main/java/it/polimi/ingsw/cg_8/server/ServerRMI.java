package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class that handles th registration of the RMI clients.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ServerRMI implements Runnable {
	/**
	 * Main server reference
	 */
	private Server server;

	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(ServerRMI.class);

	/**
	 * Passing the server is needed to bind the registration room.
	 * 
	 * @param server
	 */
	public ServerRMI(Server server) {
		this.server = server;
	}

	@Override
	public void run() {

		ServerRMIRegistrationViewRemote gameRegistration = new ServerRMIRegistrationView(
				this);
		try {
			ServerRMIRegistrationViewRemote gameRemoteRegistration = (ServerRMIRegistrationViewRemote) UnicastRemoteObject
					.exportObject(gameRegistration, 0);
			LOGGER.info("Binding server implementation to registry...");

			server.getRegistry().bind(Server.getName(), gameRemoteRegistration);
			LOGGER.info("RMI successfully started");
		} catch (RemoteException | AlreadyBoundException e) {
			LOGGER.error("Cannot start an RMI registry", e);
		}
	}

	/**
	 * Add a client to the game, check if the game has to start.
	 * 
	 * @param client
	 * @param view
	 * @throws GameAlreadyRunningException
	 * @throws RemoteException
	 */
	public synchronized void addRMIClient(SubscriberInterface client,
			ServerGameRoom view) throws GameAlreadyRunningException,
			RemoteException {

		Controller nextGame = Server.getStartingGame();
		if (nextGame == null) {
			nextGame = Server.createNewGame(GameMapName.FERMI);
		}
		synchronized (Server.getStartingGame()) {

			nextGame.addClientRMI(client.getClientId(), client.getPlayerName(),
					view);

			/**
			 * Check if the game has to start.
			 */
			Server.addClient(client.getClientId());
		}
	}
}
