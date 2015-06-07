package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.client.gui.ConnectionManagerSocket;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread created by the client when initialized. Connects the client to the
 * server, through Sockets.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ClientSocket implements Runnable {

	/**
	 * Identifies the client and it's assigned by the server after the first
	 * successful connection; before the assignment it is defaulted at 0.
	 */
	private int clientID = 0;

	/**
	 * Identifies the client (and the player), it is chosen by the player at the
	 * start of the game and displayed to the other players.
	 */
	private String playerName;

	private Scanner stdin;

	private final String SERVER_ADDRESS = "127.0.0.1";

	private final int SOCKET_PORT_CLIENTSERVER = 29998;

	private final int SOCKET_PORT_PUBSUB = 29999;
	
	private final ClientData clientData;

	private ConnectionManagerSocket connectionManager;
	/**
	 * Changed to true when the server accepts the player's name.
	 */
	private boolean nameSet = false;

	public ClientSocket(String playerName, Scanner stdin) {
		this.playerName = playerName;
		this.clientData=new ClientData();
		this.stdin = stdin;
		this.connectionManager = new ConnectionManagerSocket(playerName);
	}

	public ClientData getClientData() {
		return clientData;
	}

	public void setClientID(int clientId) {
		this.clientID = clientId;
	}

	public int getClientID() {
		return this.clientID;
	}

	@Override
	public void run() {

		try {
			this.connectionManager.initializeSocket();
			ExecutorService executor = Executors.newCachedThreadPool();
			executor.submit(new ClientSocketViewSUB(SERVER_ADDRESS,
					SOCKET_PORT_PUBSUB, this));
			System.out.println("[DEBUG] subscriber back to main thread");

			// E' anche chiuso il thread creato, tramite end() ?
			while (true) {
				try {
					System.out.println("Write a command:");
					String inputLine = stdin.nextLine();
					//System.out.println("CLIENT: read "+ inputLine);

					executor.submit(new ClientSocketViewCS(SERVER_ADDRESS,
							SOCKET_PORT_CLIENTSERVER, inputLine, clientID));

				} catch (NotAValidInput e) {
					System.out.println(e.getMessage());
				}

			}

		} catch (IOException e) {
			System.err.println("Cannot connect to socket server ("
					+ SERVER_ADDRESS + ":" + SOCKET_PORT_CLIENTSERVER + ")");
		}
	}

}
