package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.io.IOException;
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
	Socket requestSocket;

	/**
	 * The request made by the player.
	 */
	ClientAction action;
	
	/**
	 * Output stream used to send requests to the server.
	 */
	ObjectOutputStream output;
	
	/**
	 * 
	 * @param serverIP
	 *            The IP of the server.
	 * @param serverResponsePort
	 *            The port used by the server to listen to incoming connections.
	 */
	public ClientSocketViewCS(String serverIP, int serverResponsePort, ClientAction action) {
		try {
			this.requestSocket = new Socket(serverIP, serverResponsePort);
			this.action = action;
			this.output = new ObjectOutputStream(requestSocket.getOutputStream());;
		} catch (IOException e) {
			System.out.println("Failed to establish a connection with the server");
		}
	}

	@Override
	public void run() {
		try {
			output.writeObject(action);
			output.flush();
			
			// Aspetto un messaggio ResponsePrivate di conferma dal server.
			
		} catch (IOException e) {
			System.out.println("Failed to send your request to the server");
		}
		

		System.out.println("CLIENT: sent event "
				+ action.toString());
	}

}
