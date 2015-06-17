package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class used as subscriber to receive messages from the server and store the in
 * {@link ClientData}.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ClientSocketViewSUB implements Runnable {

	/**
	 * The socket used to receive messages from the server.
	 */
	private Socket subSocket;
	/**
	 * The input stream used to receive messages.
	 */
	private ObjectInputStream input;
	/**
	 * Used to store messages in {@link ClientData}.
	 */
	private ConnectionManager connectionManager;
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager
	        .getLogger(ClientSocketViewSUB.class);

	/**
	 * Used in the GUI and CLI
	 * 
	 * @param serverIP
	 * @param serverPubPort
	 * @param connectionManager
	 */
	public ClientSocketViewSUB(String serverIP, int serverPubPort,
	        ConnectionManagerSocket connectionManager) {
		try {
			this.subSocket = new Socket(serverIP, serverPubPort);
			this.input = new ObjectInputStream(subSocket.getInputStream());
			this.connectionManager = connectionManager;
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * After the thread is started, it does nothing but wait to receive
	 * messages.
	 */
	@Override
	public void run() {
		LOGGER.info("Successfully subscribed.");
		while (true) {

			this.receive();
		}
	}

	/**
	 * This method receive messages from the server, and stores them in
	 * {@link ClientData}.
	 */
	private void receive() {

		try {
			ServerResponse response = (ServerResponse) input.readObject();

			LOGGER.debug(response);

			connectionManager.getClientData().storeResponse(response);

			return;
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
