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
	private static final Logger logger = LogManager.getLogger(ServerSocketRRThread.class);
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
		logger.info("Server socket (request-response) running on port: "
				+ serverSocketRRPort);
		serverPS = new ServerSocket(serverSocketPSPort);
		logger.info("Server socket (publisher-subscribe) running on port: "
						+ serverSocketPSPort);

	}

	@Override
	public void run() {
		while (true) {
			try {
				logger.info("Waiting a connection...");
				Socket client = serverRR.accept();
				logger.info("Connection accepted");
				ObjectInputStream input = new ObjectInputStream(
						client.getInputStream());
				ObjectOutputStream output = new ObjectOutputStream(
						client.getOutputStream());
				// read client id
				Integer clientId = (Integer) input.readObject();
				logger.info("ClientId is: " + clientId);

				if (clientId == 0) {
					// client has never connected to the server
					Integer newClientId = Server.getClientId();
					logger.info("Assigning new ClientId: " + newClientId);
					Server.increaseClientId();
					output.writeObject(newClientId);
					output.flush();

					// read player name and confirm
					String playerName = (String) input.readObject();
					logger.debug("Name accepted: "
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
						Server.addClient(newClientId);
					}
				} else {
					// client has already connected to the server, reads player
					// action
					logger.debug("ClientId already assigned");
					ClientAction action = (ClientAction) input.readObject();
					logger.debug("Received client action: "+action);
					Controller controller = Server.getId2Controller().get(
							clientId);
					logger.debug("Client is assigned to controller: "+controller);
					logger.debug("Client is player: "+controller.getPlayerById(clientId));

					boolean result = StateMachine.evaluateAction(controller,
							action, controller.getPlayerById(clientId));
					logger.debug("Validation output is: " + result);
					output.writeObject(result);
					output.flush();
					logger.debug("Result sent to client");
				}
				try {
					// close connection with the socket
					logger.info("Closing connection");
					client.close();
				} finally {
					input = null;
					output = null;
					client = null;
				}

			} catch (IOException e) {
				logger.error("Cannot connect to the client");
			} catch (ClassNotFoundException e) {
				logger.error("Cannot read from the input stream");
			} catch (GameAlreadyRunningException e) {
				logger.error("Game already running, can't add the player to this game");
			}
		}
	}

}
