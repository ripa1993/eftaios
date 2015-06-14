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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager
			.getLogger(ServerSocketRRThread.class);

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
		LOGGER.info("Server socket (request-response) running on port: "
				+ serverSocketRRPort);
		serverPS = new ServerSocket(serverSocketPSPort);
		LOGGER.info("Server socket (publisher-subscribe) running on port: "
				+ serverSocketPSPort);

	}

	/**
	 * Process the registration and the input handling of a client, from the
	 * registration to the main game.
	 */
	@Override
	public void run() {
		while (true) {
			try {
				LOGGER.info("Waiting a connection...");
				Socket client = serverRR.accept();
				LOGGER.info("Connection accepted");
				ObjectInputStream input = new ObjectInputStream(
						client.getInputStream());
				ObjectOutputStream output = new ObjectOutputStream(
						client.getOutputStream());
				// read client id
				Integer clientId = (Integer) input.readObject();
				LOGGER.info("ClientId is: " + clientId);

				if (clientId == 0) {
					// client has never connected to the server
					Integer newClientId = Server.getClientId();
					LOGGER.info("Assigning new ClientId: " + newClientId);
					Server.increaseClientId();
					output.writeObject(newClientId);
					output.flush();

					// read player name and confirm
					String playerName = (String) input.readObject();
					LOGGER.debug("Name accepted: " + playerName);
					output.writeObject(new String("NAME ACCEPTED"));
					output.flush();

					GameMapName chosenMap = (GameMapName) input.readObject();
					LOGGER.debug("Map received: " + chosenMap.toString());

					LOGGER.debug("Vote given to " + chosenMap);
					Server.addVote(chosenMap);
					output.writeObject(new String("MAP CHOSEN: "
							+ chosenMap.toString()));
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
						Server.addClient(newClientId);
					}
				} else {
					// client has already connected to the server, reads player
					// action
					LOGGER.debug("ClientId already assigned");
					ClientAction action = (ClientAction) input.readObject();
					LOGGER.debug("Received client action: " + action);
					Controller controller = Server.getId2Controller().get(
							clientId);
					LOGGER.debug("Client is assigned to controller: "
							+ controller);
					LOGGER.debug("Client is player: "
							+ controller.getPlayerById(clientId));

					boolean result = StateMachine.evaluateAction(controller,
							action, controller.getPlayerById(clientId));
					LOGGER.debug("Validation output is: " + result);
					output.writeObject(result);
					output.flush();
					LOGGER.debug("Result sent to client");
				}
				try {
					// close connection with the socket
					LOGGER.info("Closing connection");
					client.close();
				} finally {
					input = null;
					output = null;
					client = null;
				}

			} catch (IOException e) {
				LOGGER.error("Cannot connect to the client",e);
			} catch (ClassNotFoundException e) {
				LOGGER.error("Cannot read from the input stream",e);
			} catch (GameAlreadyRunningException e) {
				LOGGER.error("Game already running, can't add the player to this game",e);
			}
		}
	}

}
