package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread created by the client when initialized. Connects the client to the
 * server.
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

	/**
	 * Changed to true when the server accepts the player's name.
	 */
	private boolean nameSet = false;

	public ClientSocket(String playerName, Scanner stdin) {
		this.playerName = playerName;
		this.clientData=new ClientData();
		this.stdin = stdin;
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
			Socket socket = new Socket(SERVER_ADDRESS, SOCKET_PORT_CLIENTSERVER);
			System.out.println("Connected to server " + SERVER_ADDRESS
					+ " on port " + SOCKET_PORT_CLIENTSERVER);

			ObjectOutputStream output = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(
					socket.getInputStream());

			do {
				try {
					System.out.println("Your ID is not set.");
					output.writeObject(new Integer(this.getClientID()));
					output.flush();
					Integer clientIdRequested = (Integer) input.readObject();
					System.out.println("New ID received");
					this.setClientID((int) clientIdRequested);
					System.out.println("Your ID is: " + this.getClientID());
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			} while (this.getClientID() == 0);

			do {
				try {

					System.out
							.println("Sending your User-Name to the server...");
					output.writeObject(this.playerName);
					output.flush();

					String serverAnswer = (String) input.readObject();
					if (serverAnswer.equals("NAME ACCEPTED")) {
						nameSet = true;
						System.out.println("Name accepted");
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			} while (nameSet = false);

			/**
			 * Close the socket used to establish the first connection.
			 */
			this.close(socket, output);
			/**
			 * Creates an always-on thread that works as a subscriber. When the
			 * server publishes something, this thread is notified.
			 */
			// TODO: ClientSocketViewPUB implementation.
			ExecutorService executor = Executors.newCachedThreadPool();
			executor.submit(new ClientSocketViewSUB(SERVER_ADDRESS, SOCKET_PORT_PUBSUB, this));
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

	private void close(Socket socket, ObjectOutputStream output) {
		try {
			socket.close();
		} catch (IOException e) {
		} finally {
			socket = null;
			output = null;
			System.gc();
		}
	}
}
