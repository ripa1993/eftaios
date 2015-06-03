package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketViewSUB implements Runnable {

	private Socket subSocket;
	private ObjectInputStream input;
	private ClientSocket clientSocket;

	public ClientSocketViewSUB(String serverIP, int serverPubPort,
			ClientSocket clientSocket) {
		try {
			this.subSocket = new Socket(serverIP, serverPubPort);
			this.input = new ObjectInputStream(subSocket.getInputStream());
			this.clientSocket = clientSocket;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Successfully subscribed.");
		while (true) {
			try {
				this.receive();
				
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * This method receive messages from the server, and stores them in
	 * {@link ClientData}.
	 * 
	 * @return
	 */
	private void receive() {

		try {
			ServerResponse response = (ServerResponse) input.readObject();
			System.out.println(response);
			clientSocket.getClientData().storeResponse(response);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
