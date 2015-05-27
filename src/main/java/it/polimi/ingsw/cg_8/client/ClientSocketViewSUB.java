package it.polimi.ingsw.cg_8.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientSocketViewSUB implements Runnable {

	private Socket subSocket;
	private ObjectInputStream input;

	public ClientSocketViewSUB(String serverIP, int serverPubPort) {
		try {
			this.subSocket = new Socket(serverIP, serverPubPort);
			this.input = new ObjectInputStream(subSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Subscribed.");
		while(true){
			
		}
		
	}

}
