package it.polimi.ingsw.cg_8.client;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
	
	private String playerName;
	
	public Client(){
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
		
	public static void main(String[] args) {
		
		Client client = new Client();
		Scanner stdin = new Scanner(System.in);
		
		
		System.out.println("Choose your User-Name");
		String name = stdin.nextLine();
		client.setPlayerName(name);
		client.startSocketThread(stdin);
	}
}
