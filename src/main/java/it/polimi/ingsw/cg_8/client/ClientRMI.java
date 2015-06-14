//package it.polimi.ingsw.cg_8.client;
//
//import it.polimi.ingsw.cg_8.model.map.GameMapName;
//import it.polimi.ingsw.cg_8.server.ServerGameRoom;
//import it.polimi.ingsw.cg_8.server.ServerGameRoomInterface;
//import it.polimi.ingsw.cg_8.server.ServerRMIRegistrationViewRemote;
//import it.polimi.ingsw.cg_8.view.client.ActionParser;
//import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
//import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;
//import it.polimi.ingsw.cg_8.view.server.ServerResponse;
//
//import java.io.Serializable;
//import java.rmi.AlreadyBoundException;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.rmi.server.UnicastRemoteObject;
//import java.util.Scanner;
//
///**
// * Thread created by the client when initialized. Connects the client to the
// * server, through RMI.
// * 
// * @author Alberto Parravicini
// * @version 1.0
// */
//public class ClientRMI implements Runnable, Serializable, SubscriberInterface {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -4794641611927532900L;
//
//	/**
//	 * Identifies the client and it's assigned by the server after the first
//	 * successful connection; before the assignment it is defaulted at 0.
//	 */
//	private int clientId = 0;
//
//	/**
//	 * Identifies the client (and the player), it is chosen by the player at the
//	 * start of the game and displayed to the other players.
//	 */
//	private String playerName;
//
//	private transient Scanner stdin;
//
//	private final static String HOST = "127.0.0.1";
//
//	private final static int REGISTRATION_PORT = 7777;
//
//	private final String registrationRoomName = "registrationRoom";
//
//	private ClientData clientData;
//	
//	private GameMapName mapName;
//
//	public ClientRMI(String playerName, Scanner stdin, GameMapName mapName) {
//
//		this.playerName = playerName;
//		this.stdin = stdin;
//		this.clientData = new ClientData();
//		this.mapName = mapName;	
//	}
//
//	
//	/**
//	 * The client makes a registry lookup, and access to the registration view.
//	 * Then he registers to the game, by acquiring a player code and sending its
//	 * name. Subsequently he gets access to the main {@link ServerGameRoom},
//	 * which allows him to send {@link ClientAction} to the server. When
//	 * connecting the client exposes its {@link SubscriberInterface} to the
//	 * server, so that the server can send messages to the client and access to
//	 * its data.
//	 */
//	@Override
//	public void run() {
//
//		/**
//		 * Value that shows if the server accepted the player's name.
//		 */
//		boolean nameSet = false;
//
//		System.out.println("Contacting the broker...");
//
//		try {
//
//			System.out.println("Connecting to the registry...");
//			Registry registry = LocateRegistry.getRegistry(HOST,
//					REGISTRATION_PORT);
//			System.out.println("Connecting to the registration room...");
//			ServerRMIRegistrationViewRemote registrationRoom = (ServerRMIRegistrationViewRemote) registry
//					.lookup(registrationRoomName);
//
//			System.out.println("Trying to get a clientId...");
//			while (this.clientId == 0) {
//				this.clientId = registrationRoom.getClientId(this.clientId);
//			}
//			System.out.println("Your clientId is " + this.clientId);
//
//			System.out.println("Trying to send your name to the server...");
//
//			while (nameSet == false) {
//				nameSet = registrationRoom.sendPlayerName(this.playerName);
//			}
//			System.out.println("NAME ACCEPTED");
//			
//			/**
//			 * Communicating the chosen map to the server.
//			 */
//			registrationRoom.sendMapVote(mapName);
//
//			/**
//			 * The client gets a view to play the game;
//			 */
//			System.out.println("Trying to register...");
//
//			ServerGameRoomInterface view = registrationRoom
//					.register((SubscriberInterface) UnicastRemoteObject
//							.exportObject(this, 0));
//
//			System.out.println("Successfully registered");
//
//			while (true) {
//				try {
//					System.out.println("Write a command:");
//					String inputLine = stdin.nextLine();
//					System.out.println("CLIENT: read " + inputLine);
//
//					ClientAction action = ActionParser.createEvent(inputLine);
//					/**
//					 * Useful for testing purposes.
//					 */
//					//System.out.println("Created action " + action.toString());
//					view.makeAction(this.clientId, action);
//					System.out.println("Sent action to server");
//
//				} catch (NotAValidInput e) {
//					System.out.println(e.getMessage());
//				}
//
//			}
//
//		} catch (NotBoundException | RemoteException | AlreadyBoundException e) {
//			e.printStackTrace();
//			System.err.println("Failed to connect to the RMI Server");
//		}
//
//	}
//
//	public int getClientId() {
//		return clientId;
//	}
//
//	public String getPlayerName() {
//		return playerName;
//	}
//
//	/**
//	 * @param message
//	 *            Message sent by the server, stored in {@link ClientData}.
//	 */
//	@Override
//	public void publishMessage(ServerResponse message) {
//		System.out.println(message);
//		this.clientData.storeResponse(message);
//		return;
//
//	}
//
//}
