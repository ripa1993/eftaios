package it.polimi.ingsw.cg_8.client;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

	private String playerName;

	public Client() {
		playerName = "Default";
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	private void startSocketThread(Scanner stdin) {
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(new ClientSocket(this.playerName, stdin));
	}

	// TODO: select rmi or socket. crea thread di conseguenza
	// se rmi faccio lookup registry. sul registri avrò subscribe (prendo un
	// player id) send name (invio il nome al server) e makeMove (invio
	// clientAction)
	// c'è pure write to all e write to player, usate dal server per mandare
	// messaggi
	// fanno uso di dispatch message, da reimplementare sul client come remote
	// method.
	// non ho problemi di sicurezza perchè write to... non sono remote method,
	// non fanno override della broker interface
	//
	public static void main(String[] args) {

		Client client = new Client();
		Scanner stdin = new Scanner(System.in);

		System.out.println("Choose your User-Name");
		String name = stdin.nextLine();
		client.setPlayerName(name);
		client.startSocketThread(stdin);
	}
}
