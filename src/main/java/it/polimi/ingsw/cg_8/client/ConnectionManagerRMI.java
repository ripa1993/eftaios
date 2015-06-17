package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.server.ServerGameRoom;
import it.polimi.ingsw.cg_8.server.ServerGameRoomInterface;
import it.polimi.ingsw.cg_8.server.ServerRMIRegistrationView;
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
    private static final String SERVER_ADDRESS = "localhost";
    /**
     * The server port used by the client to register.
     */
    private static final int REGISTRATION_PORT = 7777;
    /**
     * The name used to identify the {@link ServerRMIRegistrationView}
     */
    private static final String REGISTRATION_ROOM_NAME = "registrationRoom";
    /**
     * GameRoom used by RMI.
     */
    private transient ServerGameRoomInterface view;
    /**
     * Log4j logger
     */
    private static final Logger LOGGER = LogManager
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

        LOGGER.debug("Contacting the broker...");

        try {
            this.view = this.initializeRMI();
            LOGGER.debug("Successfully registered");
        } catch (NotBoundException | RemoteException | AlreadyBoundException e) {
            LOGGER.error(
                    "Failed to connect to the RMI Server: " + e.getMessage(), e);
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
            LOGGER.error("Can't perform the action", e);
        }

    }

    /**
     * Method used by the server to print messages on the client, and store them
     * in {@link ClientData}.
     */
    @Override
    public void publishMessage(ServerResponse message) throws RemoteException {
        LOGGER.debug(message);
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
        LOGGER.debug("Connecting to the registry...");
        Registry registry = LocateRegistry.getRegistry(SERVER_ADDRESS,
                REGISTRATION_PORT);
        LOGGER.debug("Connecting to the registration room...");
        ServerRMIRegistrationViewRemote registrationRoom = (ServerRMIRegistrationViewRemote) registry
                .lookup(REGISTRATION_ROOM_NAME);

        LOGGER.debug("Trying to get a clientID...");
        while (this.clientID == 0) {
            this.clientID = registrationRoom.getClientId(this.clientID);
        }
        LOGGER.info("Your clientID is " + this.clientID);

        LOGGER.debug("Trying to send your name to the server...");

        while (!nameSet) {
            nameSet = registrationRoom.sendPlayerName(this.playerName);
        }
        LOGGER.debug("NAME ACCEPTED");

        /**
         * Communicating the chosen map to the server.
         */
        LOGGER.debug("Sending your chosen map to the server...");
        registrationRoom.sendMapVote(mapName);

        /**
         * The client gets a view to play the game;
         */
        LOGGER.debug("Trying to register...");

        return registrationRoom
                .register((SubscriberInterface) UnicastRemoteObject
                        .exportObject(this, 0));

    }
}
