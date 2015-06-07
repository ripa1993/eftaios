package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.client.gui.ConnectionManager;
import it.polimi.ingsw.cg_8.client.gui.ConnectionManagerSocket;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketViewSUB implements Runnable {

	private Socket subSocket;
	private ObjectInputStream input;
	private ClientSocket clientSocket;
	private ConnectionManager connectionManager;

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

	@Override
	public void run() {
		System.out.println("Successfully subscribed.");
		while (true) {
//			try {
				this.receive();

//				Thread.sleep(5);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
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
