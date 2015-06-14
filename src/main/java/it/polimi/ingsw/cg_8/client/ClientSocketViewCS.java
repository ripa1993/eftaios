package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Temporary thread used to send requests to the server. The thread is closed
 * after receiving a confirmation form the server.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ClientSocketViewCS implements Runnable {

	/**
	 * Socket used to communicate with the server.
	 */
	private Socket requestSocket;

	/**
	 * The request made by the player.
	 */
	private ClientAction action;

	/**
	 * Output stream used to send requests to the server.
	 */
	private ObjectOutputStream output;
	/**
	 * Input stream used to receive messages from the server
	 */
	private ObjectInputStream input;
	/**
	 * This client id
	 */
	private int clientId;
	/**
	 * This player client data
	 */
	private ClientData clientData;
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager
			.getLogger(ClientSocketViewCS.class);

	/**
	 * 
	 * Constructor that creates a thread that will connect to the server with a
	 * socket connection and will send the input ClientAction, the server will
	 * answer with a boolean value to distinguish if the action has been
	 * validated or not
	 * 
	 * @param serverIP
	 *            the ip of the server socket
	 * @param serverResponsePort
	 *            the port of the server socket
	 * @param input
	 *            the ClientAction that will be sent to the server
	 * @param clientId
	 *            the id of the sender
	 * @param clientData
	 *            location where the ack will be saved
	 */
	public ClientSocketViewCS(String serverIP, int serverResponsePort,
			ClientAction input, int clientId, ClientData clientData) {

		try {
			this.clientId = clientId;
			this.action = input;
			this.requestSocket = new Socket(serverIP, serverResponsePort);
			this.output = new ObjectOutputStream(
					requestSocket.getOutputStream());
			this.input = new ObjectInputStream(requestSocket.getInputStream());
			this.clientData = clientData;
		} catch (IOException e) {
			LOGGER.error("Failed to establish a connection with the server", e);
		}

	}

	@Override
	public void run() {
		try {
			// write id
			output.writeObject(clientId);
			output.flush();

			/**
			 * Useful for testing purposes.
			 */
			LOGGER.debug("Sent client id: " + clientId);
			// write action
			output.writeObject(action);
			output.flush();

			// Useful for testing purposes.

			LOGGER.debug("Write server command: " + action);

			// Useful for testing purposes.

			LOGGER.debug("Waiting server response");

			boolean serverResponse = (boolean) input.readObject();
			clientData.storeAck(serverResponse);
			LOGGER.debug("Server response is: " + serverResponse);

		} catch (IOException e) {
			LOGGER.error("Failed to send your request to the server", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}

		close(requestSocket, output);
		// delete references
		requestSocket = null;
		output = null;
		LOGGER.debug("Socket connection closed.");
	}

	/**
	 * Closes the socket connection, resetting the object streams
	 * 
	 * @param socket
	 *            socket to be closed
	 * @param output
	 *            ouput object stream, used to send objects to the server
	 */
	private void close(Socket socket, ObjectOutputStream output) {
		try {
			socket.close();
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
