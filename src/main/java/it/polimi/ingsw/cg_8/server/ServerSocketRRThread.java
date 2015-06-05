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
 * Client server architecture implementation
 * 
 * @author Simone
 *
 */
public class ServerSocketRRThread implements Runnable {

	ServerSocket serverRR;
	ServerSocket serverPS;

	public ServerSocketRRThread(int serverSocketCsPort, int serverSocketPsPort)
			throws IOException {
		serverRR = new ServerSocket(serverSocketCsPort);
		System.out.println("Server socket (request-response) running on port: "
				+ serverSocketCsPort);
		serverPS = new ServerSocket(serverSocketPsPort);
		System.out
				.println("Server socket (publisher-subscribe) running on port: "
						+ serverSocketPsPort);

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
					if (nextGame == null || nextGame.isGameStarted()==true) {
						nextGame = Server.createNewGame(GameMapName.FERMI);
					}
					// add player to the game
					Socket subscriber = serverPS.accept();
					ServerSocketPublisherThread publisher = new ServerSocketPublisherThread(
							subscriber);
					nextGame.addClientSocket(newClientId, playerName, publisher);
					System.out.println("Player successfully added to the game");
					Server.getId2Controller().put(newClientId, nextGame);
					
					
					Server.checkGameStart();

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
