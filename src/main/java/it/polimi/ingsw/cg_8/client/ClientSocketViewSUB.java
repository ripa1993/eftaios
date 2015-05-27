package it.polimi.ingsw.cg_8.client;

import java.io.IOException;
import java.net.Socket;

public class ClientSocketViewSUB implements Runnable {

	Socket subSocket;

	public ClientSocketViewSUB(String serverIP, int serverPubPort) {
		try {
			this.subSocket = new Socket(serverIP, serverPubPort);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Subscribed.");
	}

}
