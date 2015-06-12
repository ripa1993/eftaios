package it.polimi.ingsw.cg_8.client.gui;


import it.polimi.ingsw.cg_8.client.ClientData;
import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.server.Server;
import it.polimi.ingsw.cg_8.server.ServerGameRoomInterface;
import it.polimi.ingsw.cg_8.server.ServerRMIRegistrationViewRemote;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionManagerRMI extends ConnectionManager implements
		Serializable, SubscriberInterface {

	private static final long serialVersionUID = 9162555998546617215L;

	/**
	 * The server IP address.
	 */
	private final String SERVER_ADDRESS = "127.0.0.1";
	/**
	 * The server port used by the client to register.
	 */
	private final static int REGISTRATION_PORT = 7777;
	/**
	 * The name used to identify the {@link ServerRMIRegistrationView}
	 */
	private final String registrationRoomName = "registrationRoom";
	/**
	 * GameRoom used by RMI.
	 */
	private ServerGameRoomInterface view;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(ConnectionManagerRMI.class);
	/**
	 * The constructor is the same as the parent class.
	 * @param playerName
	 */
	public ConnectionManagerRMI(String playerName) {
		super(playerName);
	}

	/**
	 * Method used to setup the RMI connection with the server.
	 */
	@Override
	public void setup() {

		System.out.println("Contacting the broker...");

		try {
			this.view = this.initializeRMI();
			System.out.println("Successfully registered");
		} catch (NotBoundException | RemoteException | AlreadyBoundException e) {
			logger.error("Failed to connect to the RMI Server");
		}
	}

	@Override
	public int getClientId() throws RemoteException {
		return this.clientID;
	}

	@Override
	public String getPlayerName() throws RemoteException {
		return this.playerName;
	}

	/**
	 * Method used to send {@link ClientAction} to the server.
	 */
	@Override
	public void send(ClientAction inputLine) {

		try {

			view.makeAction(this.clientID, inputLine);
		} catch (RemoteException e) {
			System.err.println("[DEBUG] Can't perform the action");
		}

	}

	/**
	 * Method used by the server to print messages on the client, and store them
	 * in {@link ClientData}.
	 */
	@Override
	public void publishMessage(ServerResponse message) throws RemoteException {
		System.out.println(message);
		this.clientData.storeResponse(message);
		return;
	}

	/**
	 * Method used to initialize the RMI connection and get a
	 * {@link ServerGameRoom} to perform actions.
	 * 
	 */
	public ServerGameRoomInterface initializeRMI() throws RemoteException,
			NotBoundException, AlreadyBoundException {
		System.out.println("Connecting to the registry...");
		Registry registry = LocateRegistry.getRegistry(SERVER_ADDRESS,
				REGISTRATION_PORT);
		System.out.println("Connecting to the registration room...");
		ServerRMIRegistrationViewRemote registrationRoom = (ServerRMIRegistrationViewRemote) registry
				.lookup(registrationRoomName);

		System.out.println("Trying to get a clientID...");
		while (this.clientID == 0) {
			this.clientID = registrationRoom.getClientId(this.clientID);
		}
		System.out.println("Your clientID is " + this.clientID);

		System.out.println("Trying to send your name to the server...");

		while (nameSet == false) {
			nameSet = registrationRoom.sendPlayerName(this.playerName);
		}
		System.out.println("NAME ACCEPTED");

		/**
		 * The client gets a view to play the game;
		 */
		System.out.println("Trying to register...");
		
		return registrationRoom
				.register((SubscriberInterface) UnicastRemoteObject
						.exportObject(this, 0));

	}
}
