package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.StateMachine;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket server implementation, it accepts connection from clients, if they
 * haven't got a valid id (0) it assignes them a valid one and creates an
 * instance of the broker thread for publisher-subscriber communication with the
 * clients
 * 
 * @author Simone
 * @version 1.0
 */
public class ServerSocketRRThread implements Runnable {
	/**
	 * Request-Response socket server
	 */
	private ServerSocket serverRR;
	/**
	 * Publisher-Subscriber socket server
	 */
	private ServerSocket serverPS;

	/**
	 * It starts both RR and PS server on the given ports
	 * 
	 * @param serverSocketRRPort
	 *            request-response server port
	 * @param serverSocketPSPort
	 *            publisher-subscriber server port
	 * @throws IOException
	 *             if one of the server cannot be started
	 */
	public ServerSocketRRThread(int serverSocketRRPort, int serverSocketPSPort)
			throws IOException {
		serverRR = new ServerSocket(serverSocketRRPort);
		System.out.println("Server socket (request-response) running on port: "
				+ serverSocketRRPort);
		serverPS = new ServerSocket(serverSocketPSPort);
		System.out
				.println("Server socket (publisher-subscribe) running on port: "
						+ serverSocketPSPort);

	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Waiting a connection...");
				Socket client = serverRR.accept();
				System.out.println("Connection accepted");
				ObjectInputStream input = new ObjectInputStream(
						client.getInputStream());
				ObjectOutputStream output = new ObjectOutputStream(
						client.getOutputStream());
				// read client id
				Integer clientId = (Integer) input.readObject();
				System.out.println("ClientId is: " + clientId);

				if (clientId == 0) {
					// client has never connected to the server
					Integer newClientId = Server.getClientId();
					System.out
							.println("Assigning new ClientId: " + newClientId);
					Server.increaseClientId();
					output.writeObject(newClientId);
					output.flush();

					// read player name and confirm
					String playerName = (String) input.readObject();
					System.out.println("Id: " + newClientId + " Name: "
							+ playerName);
					output.writeObject(new String("NAME ACCEPTED"));
					output.flush();

					// get reference to the starting game
					
					Controller nextGame = Server.getStartingGame();
					if (Server.getStartingGame() == null) {
						nextGame = Server.createNewGame(GameMapName.FERMI);
					}
					synchronized (Server.getStartingGame()) {
						
						// add player to the game
						Socket subscriber = serverPS.accept();
						ServerSocketPublisherThread publisher = new ServerSocketPublisherThread(
								subscriber);
						nextGame.addClientSocket(newClientId, playerName,
								publisher);
						System.out
								.println("Player successfully added to the game");
						Server.getId2Controller().put(newClientId, nextGame);

						if (nextGame.getNumOfPlayers() == Server.MIN_PLAYERS) {
							Server.startTimeout();
						}

						if (nextGame.getNumOfPlayers() == Server.MAX_PLAYERS) {
							Server.abortTimeout();
							nextGame.initGame();
							Server.nullStartingGame();
							System.out.println("Game started");
						}
					}
				} else {
					// client has already connected to the server, reads player
					// action
					ClientAction action = (ClientAction) input.readObject();
					System.out.println("[DEBUG] " + action);
					Controller controller = Server.getId2Controller().get(
							clientId);
					System.out.println(controller);
					System.out.println(controller.getPlayerById(clientId));

					boolean result = StateMachine.evaluateAction(controller,
							action, controller.getPlayerById(clientId));
					System.out.println("[DEBUG]" + result);
					output.writeObject(result);
					output.flush();
				}
				try {
					// close connection with the socket
					System.out.println("Closing connection");
					client.close();
				} finally {
					input = null;
					output = null;
					client = null;
				}

			} catch (IOException e) {
				System.err.println("Cannot connect to the client");
			} catch (ClassNotFoundException e) {
				System.err.println("Cannot read from the input stream");
			} catch (GameAlreadyRunningException e) {
				System.err
						.println("Game already running, can't add you to this game");
			}
		}
	}

}
