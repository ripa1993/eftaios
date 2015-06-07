package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.client.gui.ConnectionManager;
import it.polimi.ingsw.cg_8.client.gui.ConnectionManagerSocket;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

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
	 * Reference to the {@link ClientSocket}, used to get the client
	 * information.
	 */
	private ClientSocket clientSocket;
	/**
	 * Used to store messages in {@link ClientData}.
	 */
	private ConnectionManager connectionManager;

	/**
	 * Used in the CLI
	 * @param serverIP
	 * @param serverPubPort
	 * @param clientSocket
	 */
	public ClientSocketViewSUB(String serverIP, int serverPubPort,
			ClientSocket clientSocket) {
		try {
			this.subSocket = new Socket(serverIP, serverPubPort);
			this.input = new ObjectInputStream(subSocket.getInputStream());
			this.clientSocket = clientSocket;
			this.connectionManager = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Used in the GUI
	 * @param serverIP
	 * @param serverPubPort
	 * @param connectionManager
	 */
	public ClientSocketViewSUB(String serverIP, int serverPubPort,
			ConnectionManagerSocket connectionManager) {
		try {
			this.subSocket = new Socket(serverIP, serverPubPort);
			this.input = new ObjectInputStream(subSocket.getInputStream());
			this.clientSocket = null;
			this.connectionManager = connectionManager;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * After the thread is started, it does nothing but wait to receive
	 * messages.
	 */
	@Override
	public void run() {
		System.out.println("Successfully subscribed.");
		// TODO: add timer to waste fewer resources?
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
			System.out.println(response);
			if (clientSocket != null) {
				clientSocket.getClientData().storeResponse(response);
			} else {
				connectionManager.getClientData().storeResponse(response);
			}
			return;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
