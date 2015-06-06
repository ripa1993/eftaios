package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerRMI implements Runnable {

	private Server server;

	public ServerRMI(Server server) {
		this.server = server;
	}

	public void run() {
		System.out.println("Starting an RMI thread");

		ServerRMIRegistrationViewRemote gameRegistration = new ServerRMIRegistrationView(
				this);
		try {
			ServerRMIRegistrationViewRemote gameRemoteRegistration = (ServerRMIRegistrationViewRemote) UnicastRemoteObject
					.exportObject(gameRegistration, 0);
			System.out.println("Binding server implementation to registry...");

			server.getRegistry().bind(Server.getName(), gameRemoteRegistration);

		} catch (RemoteException | AlreadyBoundException e) {
			System.err.println("Cannot start an RMI registry");
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

			System.out.println("Player successfully added to the game");
			Server.getId2Controller().put(client.getClientId(), nextGame);
			if (nextGame.getNumOfPlayers() == Server.MIN_PLAYERS) {
				Server.startTimeout();
			}
			if (nextGame.getNumOfPlayers() == Server.MAX_PLAYERS) {
				Server.abortTimeout();
				nextGame.initGame();
				Server.nullStartingGame();
				System.out.println("Game started");
			}
		}
	}
}
