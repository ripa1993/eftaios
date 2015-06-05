package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.DefaultRules;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	/**
	 * Progressive id assigned to client, it is increased after assigning to one
	 * client
	 */
	private static int clientId = 1;
	/**
	 * Reference to the next starting game
	 */
	private static Controller nextGame;
	/**
	 * Request-Response socket server port
	 */
	private final static int SERVER_SOCKET_RR_PORT = 29998;
	/**
	 * Publisher-Subscribe socket server port
	 */
	private final static int SERVER_SOCKET_PS_PORT = 29999;
	/**
	 * Associates players with the game they are playing.
	 */
	private static Map<Integer, Controller> id2Controller = new HashMap<Integer, Controller>();
	/**
	 * The name of the registry.
	 */
	private static final String NAME = "registrationRoom";
	/**
	 * Maximum number of players allowed in a game
	 */
	public static final int MAX_PLAYER = 8;
	/**
	 * Minimum number of players allowed in a game
	 */
	public static final int MIN_PLAYER = 2;
	/**
	 * RMI registry
	 */
	private final Registry registry;

	/**
	 * The constructor creates an RMI registry on port 7777.
	 * 
	 * @throws RemoteException
	 */
	public Server() throws RemoteException {
		nextGame = null;
		this.registry = LocateRegistry.createRegistry(7777);

	}

	public static Map<Integer, Controller> getId2Controller() {
		return id2Controller;
	}

	public static int getClientId() {
		return clientId;
	}

	public static void increaseClientId() {
		clientId++;
	}

	public static Controller getStartingGame() {
		return nextGame;
	}

	public static Controller createNewGame(GameMapName gameMapName) {
		nextGame = new Controller(gameMapName, new DefaultRules());
		return nextGame;
	}

	public static void nullStartingGame() {
		nextGame = null;
	}

	public static void main(String[] args) throws RemoteException,
			AlreadyBoundException {
		Server server = new Server();
		server.startSocket();
		server.startRMI();
	}

	/**
	 * Start a thread to handle the socket connections.
	 */
	private void startSocket() {
		ExecutorService executorSocket = Executors.newCachedThreadPool();
		try {
			executorSocket.submit(new ServerSocketRRThread(
					SERVER_SOCKET_RR_PORT, SERVER_SOCKET_PS_PORT));
		} catch (IOException e) {
			System.err.println("Cannot start server socket CS on port: "
					+ SERVER_SOCKET_RR_PORT);
		}
	}

	/**
	 * Start a thread to handle the RMI connections.
	 * 
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 */
	private void startRMI() throws RemoteException, AlreadyBoundException {

		ExecutorService executorRMI = Executors.newCachedThreadPool();
		executorRMI.submit(new ServerRMI(this));
	}

	public static String getName() {
		return NAME;
	}

	public Registry getRegistry() {
		return registry;
	}

	public static void checkGameStart() {
		// start the timer if reached min player
		if (nextGame.getNumOfPlayers() == Server.MIN_PLAYER) {
			nextGame.startTimeout();
		}
		// start the game if reached max players
		if (nextGame.getNumOfPlayers() == Server.MAX_PLAYER) {
			nextGame.initGame();

			Server.nullStartingGame();
			System.out.println("Game started because reached "
					+ Server.MAX_PLAYER + " players.");
		}
	}

}
