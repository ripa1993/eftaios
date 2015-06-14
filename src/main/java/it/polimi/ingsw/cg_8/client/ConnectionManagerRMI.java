package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.server.ServerGameRoom;
import it.polimi.ingsw.cg_8.server.ServerGameRoomInterface;
import it.polimi.ingsw.cg_8.server.ServerRMIRegistrationView;
import it.polimi.ingsw.cg_8.server.ServerRMIRegistrationViewRemote;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
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

/**
 * Class used to handle the RMI connection between server and client gui or cli
 * 
 * @author Simone
 * @version 1.0
 */
public class ConnectionManagerRMI extends ConnectionManager implements
		Serializable, SubscriberInterface {
	/**
	 * 
	 */
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
	private static final Logger logger = LogManager
			.getLogger(ConnectionManagerRMI.class);

	/**
	 * The constructor is the same as the parent class.
	 * 
	 * @param playerName
	 *            player name
	 * @param mapName
	 *            map name
	 */
	public ConnectionManagerRMI(String playerName, GameMapName mapName) {
		super(playerName, mapName);
	}

	/**
	 * Method used to setup the RMI connection with the server.
	 */
	@Override
	public void setup() {

		logger.debug("Contacting the broker...");

		try {
			this.view = this.initializeRMI();
			logger.debug("Successfully registered");
		} catch (NotBoundException | RemoteException | AlreadyBoundException e) {
			logger.error("Failed to connect to the RMI Server: "
					+ e.getMessage());
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

			boolean serverResponse = view.makeAction(this.clientID, inputLine);
			clientData.storeAck(serverResponse);
		} catch (RemoteException e) {
			logger.debug("Can't perform the action");
		}

	}

	/**
	 * Method used by the server to print messages on the client, and store them
	 * in {@link ClientData}.
	 */
	@Override
	public void publishMessage(ServerResponse message) throws RemoteException {
		logger.debug(message);
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
		logger.debug("Connecting to the registry...");
		Registry registry = LocateRegistry.getRegistry(SERVER_ADDRESS,
				REGISTRATION_PORT);
		logger.debug("Connecting to the registration room...");
		ServerRMIRegistrationViewRemote registrationRoom = (ServerRMIRegistrationViewRemote) registry
				.lookup(registrationRoomName);

		logger.debug("Trying to get a clientID...");
		while (this.clientID == 0) {
			this.clientID = registrationRoom.getClientId(this.clientID);
		}
		logger.info("Your clientID is " + this.clientID);

		logger.debug("Trying to send your name to the server...");

		while (nameSet == false) {
			nameSet = registrationRoom.sendPlayerName(this.playerName);
		}
		logger.debug("NAME ACCEPTED");

		/**
		 * Communicating the chosen map to the server.
		 */
		logger.debug("Sending your chosen map to the server...");
		registrationRoom.sendMapVote(mapName);

		/**
		 * The client gets a view to play the game;
		 */
		logger.debug("Trying to register...");

		return registrationRoom
				.register((SubscriberInterface) UnicastRemoteObject
						.exportObject(this, 0));

	}
}
