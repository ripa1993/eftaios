//package it.polimi.ingsw.cg_8.client;
//
//import it.polimi.ingsw.cg_8.model.map.GameMapName;
//
//import java.util.Scanner;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * Main client class for the CLI; it allows the user to set his user-name and select the
// * connection type he prefers. It also starts the thread used to handle the
// * game.
// * 
// * @author Alberto Parravicini
// * @version 1.0
// */
//public class Client {
//
//	private String playerName;
//	
//	private GameMapName mapName;
//
//	public Client() {
//		playerName = "Default";
//		mapName = GameMapName.FERMI;
//	}
//
//	public void setPlayerName(String playerName) {
//		this.playerName = playerName;
//	}
//
//	public String getPlayerName() {
//		return this.playerName;
//	}
//
//	// TODO: select rmi or socket. crea thread di conseguenza
//	
//	public static void main(String[] args) {
//
//		/**
//		 * Used to see if the player has successfully chosen a connection type;
//		 */
//		boolean connectionChosen = false;
//
//		Client client = new Client();
//		Scanner stdin = new Scanner(System.in);
//
//		System.out.println("Choose your User-Name");
//		String name = stdin.nextLine();
//		client.setPlayerName(name);
//		while (connectionChosen == false) {
//			System.out.println("Press 1 to use RMI or press 2 to use Socket");
//			String connectionType = stdin.nextLine();
//			System.out.println(connectionType);
//
//			if (connectionType.equals("1")) {
//				client.startRMIThread(stdin);
//
//				System.out.println("You have chosen to use RMI");
//				connectionChosen = true;
//			} else if (connectionType.equals("2")) {
//				System.out.println("You have chosen to use Sockets");
//				client.startSocketThread(stdin);
//				connectionChosen = true;
//			} else {
//				System.out.println("Wrong input");
//			}
//		}
//		//TODO: chose map!!!!!!!!!
//	}
//
//	/**
//	 * Creates a thread used to handle the connection via RMI.
//	 * 
//	 * @param stdin
//	 *            The input scanner.
//	 */
//	private void startRMIThread(Scanner stdin) {
//		ExecutorService executorRMI = Executors.newCachedThreadPool();
//		executorRMI.submit(new ClientRMI(this.playerName, stdin, mapName));
//	}
//
//	/**
//	 * Creates a thread used to handle the connection via socket.
//	 * 
//	 * @param stdin
//	 *            The input scanner.
//	 */
//	private void startSocketThread(Scanner stdin) {
//		ExecutorService executorSocket = Executors.newCachedThreadPool();
//		executorSocket.submit(new ClientSocket(this.playerName, stdin));
//	}
//}
