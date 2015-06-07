package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	private ObjectInputStream input;
	private int clientId;

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
			System.out
					.println("Failed to establish a connection with the server");
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
			System.out
					.println("Failed to establish a connection with the server");
		}

	}

	@Override
	public void run() {
		try {
			// write id
			output.writeObject(clientId);
			output.flush();
			System.out.println("[DEBUG] sent client id");
			// write action
			output.writeObject(action);
			output.flush();
			System.out.println("[DEBUG] write server command: " + action);
			System.out.println("[DEBUG] waiting server response");
			try {
				System.out.println((boolean) input.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Aspetto un messaggio ResponsePrivate di conferma dal server.

		} catch (IOException e) {
			System.out.println("Failed to send your request to the server");
		}

		close(requestSocket, output);
		System.out.println("Socket connection closed.");
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
