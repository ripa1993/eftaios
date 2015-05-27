package it.polimi.ingsw.cg_8.client;

import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketViewSUB implements Runnable {

	private Socket subSocket;
	private ObjectInputStream input;
	private ClientSocket clientSocket;

	public ClientSocketViewSUB(String serverIP, int serverPubPort, ClientSocket clientSocket) {
		try {
			this.subSocket = new Socket(serverIP, serverPubPort);
			this.input = new ObjectInputStream(subSocket.getInputStream());
			this.clientSocket=clientSocket;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Successfully subscribed.");
		while(true){
			try {
				ServerResponse response = (ServerResponse) input.readObject();
				System.out.println("[DEBUG] "+response);
				clientSocket.getClientData().storeResponse(response);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
