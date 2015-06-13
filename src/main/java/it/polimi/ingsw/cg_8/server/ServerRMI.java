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

public class ServerRMI implements Runnable {
	/**
	 * Main server reference
	 */
	private Server server;

	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(ServerRMI.class);
	
	public ServerRMI(Server server) {
		this.server = server;
	}

	public void run() {

		ServerRMIRegistrationViewRemote gameRegistration = new ServerRMIRegistrationView(
				this);
		try {
			ServerRMIRegistrationViewRemote gameRemoteRegistration = (ServerRMIRegistrationViewRemote) UnicastRemoteObject
					.exportObject(gameRegistration, 0);
			logger.info("Binding server implementation to registry...");

			server.getRegistry().bind(Server.getName(), gameRemoteRegistration);
			logger.info("RMI successfully started");
		} catch (RemoteException | AlreadyBoundException e) {
			logger.error("Cannot start an RMI registry");
		}
	}

	public Server getServer() {
		return server;
	}

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

			Server.addClient(client.getClientId());
		}
	}
}
