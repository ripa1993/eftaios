package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.DefaultRules;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	private static int clientId = 1;
	
	private static Controller nextGame;
	
	private final static int SERVER_SOCKET_RR_PORT = 29998;
	
	private final static int SERVER_SOCKET_PS_PORT = 29999;
	/**
	 * Associates players with the game they are playing.
	 */
	private static Map<Integer, Controller> id2Controller = new HashMap<Integer, Controller>();
	/**
	 * The name of the registry.
	 */
	private static final String NAME = "gameRoom";
	
	private final Registry registry;
	
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
		ExecutorService executor = Executors.newCachedThreadPool();
		try {
			executor.submit(new ServerSocketRRThread(SERVER_SOCKET_RR_PORT,
					SERVER_SOCKET_PS_PORT));
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

		System.out.println("Starting an RMI thread");
		
		ServerRMIRegistrationViewRemote gameRegistration = new ServerRMIRegistrationView(this);
		ServerRMIRegistrationViewRemote gameRemoteRegistration = (ServerRMIRegistrationViewRemote) UnicastRemoteObject
				.exportObject(gameRegistration, 0);
		System.out.println("Binding server implementation to registry...");
		registry.bind(NAME, gameRemoteRegistration);
	}

	public void addRMIClient(ServerGameRoom view) {
		// TODO Auto-generated method stub
		
	}
}
