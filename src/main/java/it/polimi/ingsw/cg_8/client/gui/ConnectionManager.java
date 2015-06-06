package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.client.ClientData;
import it.polimi.ingsw.cg_8.client.ClientSocketViewCS;
import it.polimi.ingsw.cg_8.client.ClientSocketViewSUB;
import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.server.ServerGameRoomInterface;
import it.polimi.ingsw.cg_8.server.ServerRMIRegistrationViewRemote;
import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;
import it.polimi.ingsw.cg_8.view.server.ResponseChat;
import it.polimi.ingsw.cg_8.view.server.ResponseNoise;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionManager implements Observer, Serializable, SubscriberInterface {
	private String playerName;
	private ClientData clientData;
	private final String SERVER_ADDRESS = "127.0.0.1";
	private final int SOCKET_PORT_CLIENTSERVER = 29998;
	private final int SOCKET_PORT_PUBSUB = 29999;
	private final static int REGISTRATION_PORT = 7777;
	private final String registrationRoomName = "registrationRoom";
	private int clientID;
	private boolean nameSet;
	private ClientGUIThread guiThread;

	public ConnectionManager(String playerName, ClientGUIThread guiThread) {
		this.playerName = playerName;
		clientData = new ClientData();
		nameSet = false;
		clientID = 0;
		clientData.addObserver(this);
		this.guiThread=guiThread;
	}

	public ConnectionManager() {
		clientData = new ClientData();
		nameSet = false;
		clientID = 0;
		clientData.addObserver(this);
	}
	
	public void setPlayerName(String name){
		this.playerName = name;
	}
	
	public void setGuiThread(ClientGUIThread gui){
		this.guiThread = gui;
	}


	public void setupRMI() {

		/**
		 * Value that shows if the server accepted the player's name.
		 */
		boolean nameSet = false;

		System.out.println("Contacting the broker...");


		try {

			System.out.println("Connecting to the registry...");
			Registry registry = LocateRegistry.getRegistry(SERVER_ADDRESS,
					REGISTRATION_PORT);
			System.out.println("Connecting to the registration room...");
			ServerRMIRegistrationViewRemote registrationRoom = (ServerRMIRegistrationViewRemote) registry
					.lookup(registrationRoomName);

			System.out.println("Trying to get a clientID...");
			while (this.clientID == 0) {
				this.clientID = registrationRoom.getClientId(this.clientID);
			}
			System.out.println("Your clientID is " + this.clientID);

			System.out.println("Trying to send your name to the server...");

			while (nameSet == false) {
				nameSet = registrationRoom.sendPlayerName(this.playerName);
			}
			System.out.println("NAME ACCEPTED");

			/**
			 * The client gets a view to play the game;
			 */
			System.out.println("Trying to register...");

			ServerGameRoomInterface view = registrationRoom
					.register((SubscriberInterface) UnicastRemoteObject
							.exportObject(this, 0));

			System.out.println("Successfully registered");

			

		} catch (NotBoundException | RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
			System.err.println("Failed to connect to the RMI Server");
		}
	}

	public void setupSocket() {
		try {
			Socket socket = new Socket(SERVER_ADDRESS, SOCKET_PORT_CLIENTSERVER);
			System.out.println("Connected to server " + SERVER_ADDRESS
					+ " on port " + SOCKET_PORT_CLIENTSERVER);

			ObjectOutputStream output = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(
					socket.getInputStream());

			do {
				try {
					System.out.println("Your ID is not set.");
					output.writeObject(new Integer(this.getclientID()));
					output.flush();
					Integer clientIDRequested = (Integer) input.readObject();
					System.out.println("New ID received");
					this.setclientID((int) clientIDRequested);
					System.out.println("Your ID is: " + this.getclientID());
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			} while (this.getclientID() == 0);

			do {
				try {

					System.out
							.println("Sending your User-Name to the server...");
					output.writeObject(this.playerName);
					output.flush();

					String serverAnswer = (String) input.readObject();
					if (serverAnswer.equals("NAME ACCEPTED")) {
						nameSet = true;
						System.out.println("Name accepted");
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			} while (nameSet == false);

			/**
			 * Close the socket used to establish the first connection.
			 */
			this.close(socket, output);
			/**
			 * Creates an always-on thread that works as a subscriber. When the
			 * server publishes something, this thread is notified.
			 */
			// TODO: ClientSocketViewPUB implementation.
			ExecutorService executor = Executors.newCachedThreadPool();
			executor.submit(new ClientSocketViewSUB(SERVER_ADDRESS,
					SOCKET_PORT_PUBSUB, this));
			System.out.println("[DEBUG] subscriber back to main thread");

			
			
		} catch (IOException e) {
			System.err.println("Cannot connect to socket server ("
					+ SERVER_ADDRESS + ":" + SOCKET_PORT_CLIENTSERVER + ")");
		}

	}

	public ClientData getClientData() {
		return clientData;
	}

	public void setclientID(int clientID) {
		this.clientID = clientID;
	}

	public int getclientID() {
		return this.clientID;
	}

	private void close(Socket socket, ObjectOutputStream output) {
		try {
			socket.close();
		} catch (IOException e) {
		} finally {
			socket = null;
			output = null;
			System.gc();
		}
	}

	public void send(ClientAction inputLine) {

		ClientSocketViewCS socketCS = new ClientSocketViewCS(SERVER_ADDRESS,
				SOCKET_PORT_CLIENTSERVER, inputLine, clientID);
		socketCS.run();

	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg.equals("Chat")) {
			ResponseChat chat = clientData.getLastChat();
			guiThread.appendChat(chat.getPlayerName(), chat.getMessage());
		} else if (arg.equals("Noise")) {
			ResponseNoise noise = clientData.getLastNoise();
			guiThread.appendInfo("NOISE", noise.toString());
		} else {
			ResponsePrivate privateMessage = clientData.getLastPrivate();
			guiThread.appendInfo("INFO", privateMessage.getMessage());
		}

	}

	@Override
	public void publishMessage(ServerResponse message) throws RemoteException {
		
		
	}

	@Override
	public int getClientId() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPlayerName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
