package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.server.ServerSocketPublisherThread;
import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

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
	 * Log4j logger
	 */
	private static final Logger logger = LogManager
			.getLogger(ServerSocketPublisherThread.class);

	/**
	 * 
	 * @param serverIP
	 *            The IP of the server.
	 * @param serverResponsePort
	 *            The port used by the server to listen to incoming connections.
	 * @throws NotAValidInput
	 */
	public ClientSocketViewCS(String serverIP, int serverResponsePort,
			String inputLine, int clientId) throws NotAValidInput {
		try {
			// TODO: server must close connection if no input from client in 10
			// sec
			this.clientId = clientId;
			this.action = ActionParser.createEvent(inputLine);
			this.requestSocket = new Socket(serverIP, serverResponsePort);
			this.output = new ObjectOutputStream(
					requestSocket.getOutputStream());
			this.input = new ObjectInputStream(requestSocket.getInputStream());

		} catch (IOException e) {
			logger.error("Failed to establish a connection with the server");
		}
	}

	public ClientSocketViewCS(String serverIP, int serverResponsePort,
			ClientAction input, int clientId) {

		try {
			this.clientId = clientId;
			this.action = input;
			this.requestSocket = new Socket(serverIP, serverResponsePort);
			this.output = new ObjectOutputStream(
					requestSocket.getOutputStream());
			this.input = new ObjectInputStream(requestSocket.getInputStream());
		} catch (IOException e) {
			logger.error("Failed to establish a connection with the server");
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
			// logger.debug("Sent client id: "+clientId);
			// write action
			output.writeObject(action);
			output.flush();

			// Useful for testing purposes.

			//logger.debug("Write server command: " + action);

			// Useful for testing purposes.

			// logger.debug("Waiting server response");
			try {
				logger.debug((boolean) input.readObject());
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
			}
			// Aspetto un messaggio ResponsePrivate di conferma dal server.

		} catch (IOException e) {
			logger.error("Failed to send your request to the server");
		}

		close(requestSocket, output);
		/**
		 * Useful for testing purposes.
		 */
		// logger.debug("Socket connection closed.");
	}

	private void close(Socket socket, ObjectOutputStream output) {
		try {
			socket.close();
		} catch (IOException e) {
		} finally {
			socket = null;
			output = null;
		}
	}

}
