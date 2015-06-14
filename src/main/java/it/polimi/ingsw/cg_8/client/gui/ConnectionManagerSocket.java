package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.client.ClientSocketViewCS;
import it.polimi.ingsw.cg_8.client.ClientSocketViewSUB;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class used to connect the client to the server via socket. It also containt
 * the send() function, used to communicate with the server.
 * 
 * @author Alberto Parravicini
 * @version 1.1
 */
public class ConnectionManagerSocket extends ConnectionManager {

	private static final long serialVersionUID = -3402004204836762667L;
	/**
	 * The server IP address.
	 */
	private final String SERVER_ADDRESS = "127.0.0.1";
	/**
	 * The server port used for the Receive/Response communication.
	 */
	private final int SOCKET_PORT_CLIENTSERVER = 29998;
	/**
	 * The server port used for the Publisher/Subscriber communication.
	 */
	private final int SOCKET_PORT_PUBSUB = 29999;
	/**
	 * Thread executor service
	 */
	private ExecutorService executor;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager
			.getLogger(ConnectionManagerSocket.class);

	public ConnectionManagerSocket(String playerName, GameMapName mapName) {
		super(playerName, mapName);
		executor = Executors.newCachedThreadPool();
	}

	/**
	 * Function called to setup the connection with the server.
	 */
	@Override
	public void setup() {
		try {
			this.initializeSocket();

			/**
			 * Creates an always-on thread that works as a subscriber. When the
			 * server publishes something, this thread is notified.
			 */
			executor.submit(new ClientSocketViewSUB(SERVER_ADDRESS,
					SOCKET_PORT_PUBSUB, this));
			logger.debug("Subscriber back to main thread");
		} catch (IOException e) {
			logger.error("Cannot connect to socket server (" + SERVER_ADDRESS
					+ ":" + SOCKET_PORT_CLIENTSERVER + ")");
		}

	}

	/**
	 * Function used to send messages (i.e {@link ClientAction} to the server.
	 */
	@Override
	public void send(ClientAction inputLine) {
		logger.debug("Sending action...");
		ClientSocketViewCS socketCS = new ClientSocketViewCS(SERVER_ADDRESS,
				SOCKET_PORT_CLIENTSERVER, inputLine, clientID, clientData);
		executor.submit(socketCS);

	}

	/**
	 * Used to establish a connection with the server.
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void initializeSocket() throws UnknownHostException, IOException {
		Socket socket = new Socket(SERVER_ADDRESS, SOCKET_PORT_CLIENTSERVER);
		logger.debug("Connected to server " + SERVER_ADDRESS
				+ " on port " + SOCKET_PORT_CLIENTSERVER);

		ObjectOutputStream output = new ObjectOutputStream(
				socket.getOutputStream());
		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

		do {
			try {
				logger.info("Your ID is not set.");
				output.writeObject(new Integer(this.getclientID()));
				output.flush();
				Integer clientIDRequested = (Integer) input.readObject();
				logger.info("New ID received");
				this.setclientID((int) clientIDRequested);
				logger.info("Your ID is: " + this.getclientID());
			} catch (IOException | ClassNotFoundException e) {
				logger.error(e.getMessage());
			}
		} while (this.getclientID() == 0);

		do {
			try {

				logger.debug("Sending your User-Name to the server...");
				output.writeObject(this.playerName);
				output.flush();

				String serverAnswer = (String) input.readObject();
				if (serverAnswer.equals("NAME ACCEPTED")) {
					nameSet = true;
					logger.debug("Name accepted");
				}
			} catch (IOException | ClassNotFoundException e) {
				logger.error(e.getMessage());
			}
		} while (nameSet == false);

		this.close(socket, output);

	}

	/**
	 * Close the socket used to establish the first connection.
	 */
	private void close(Socket socket, ObjectOutputStream output) {
		try {
			socket.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			socket = null;
			output = null;
		}
	}
}