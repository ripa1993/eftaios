package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.DefaultRules;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Game server for Escape From The Aliens In Outer Space, provides a Socket and
 * RMI connection to clients, implements a Request-Response pattern and a
 * Publisher-Subscriber (sort of) pattern
 * 
 * @author Simone
 * @version 1.0
 */
public class Server {
	/**
	 * Progressive unique identifier given to a connecting client
	 */
	private static int clientId = 1;
	/**
	 * Reference to the next starting game
	 */
	private static Controller nextGame;
	/**
	 * Socket port for Request-Response server
	 */
	private final static int SERVER_SOCKET_RR_PORT = 29998;
	/**
	 * Socket port for Publisher-Subscriber server
	 */
	private final static int SERVER_SOCKET_PS_PORT = 29999;
	/**
	 * Minimum number of players required to start a game
	 */
	public final static int MIN_PLAYERS = 2;
	/**
	 * Maximum number of players per game
	 */
	public final static int MAX_PLAYERS = 8;
	/**
	 * Association between players and the game they are playing.
	 */
	private static Map<Integer, Controller> id2Controller = new HashMap<Integer, Controller>();
	/**
	 * The name of the registry.
	 */
	private static final String NAME = "registrationRoom";
	/**
	 * Registry used by RMI server
	 */
	private final Registry registry;
	/**
	 * Timer used in timeout
	 */
	private static Timer timer;
	/**
	 * TimerTask used in timeout
	 */
	private static TimerTask timerTask;
	/**
	 * Milliseconds after which the game is automatically started when 2 player
	 * join the game
	 */
	private final static int TIMEOUT = 5000;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(Server.class);

	/**
	 * The constructor creates an RMI registry on port 7777.
	 * 
	 * @throws RemoteException
	 */
	public Server() throws RemoteException {
		nextGame = null;
		timer = new Timer();
		this.registry = LocateRegistry.createRegistry(7777);

	}

	/**
	 * 
	 * @return reference to the association between players and games
	 */
	public static Map<Integer, Controller> getId2Controller() {
		return id2Controller;
	}

	/**
	 * 
	 * @return clientId that will be given to next connecting client
	 */
	public static int getClientId() {
		return clientId;
	}

	/**
	 * Increase the value of clientId
	 */
	public static void increaseClientId() {
		clientId++;
	}

	/**
	 * 
	 * @return reference to the next starting game
	 */
	public static Controller getStartingGame() {
		return nextGame;
	}

	/**
	 * Creates a new game, replacing the previous reference in nextGame with the
	 * new one
	 * 
	 * @param gameMapName
	 *            name of the new map
	 * @return reference to the newly created game
	 */
	public static Controller createNewGame(GameMapName gameMapName) {
		nextGame = new Controller(gameMapName, new DefaultRules());
		logger.debug("New game created: " + gameMapName);
		return nextGame;
	}

	/**
	 * Set the nextGame to null
	 */
	public static void nullStartingGame() {
		nextGame = null;
		logger.debug("Now the next game is null");
	}

	/**
	 * Start a thread to handle the socket connections.
	 */
	private void startSocket() {
		ExecutorService executorSocket = Executors.newCachedThreadPool();
		try {
			executorSocket.submit(new ServerSocketRRThread(
					SERVER_SOCKET_RR_PORT, SERVER_SOCKET_PS_PORT));
		} catch (IOException e) {
			logger.error("Cannot start server socket CS on port: "
					+ SERVER_SOCKET_RR_PORT);
		}
	}

	/**
	 * Start a thread to handle the RMI connections.
	 * 
	 * @throws RemoteException
	 * @throws AlreadyBoundException
	 */
	private void startRMI() throws RemoteException, AlreadyBoundException {

		ExecutorService executorRMI = Executors.newCachedThreadPool();
		executorRMI.submit(new ServerRMI(this));
	}

	/**
	 * 
	 * @return name of the registry
	 */
	public static String getName() {
		return NAME;
	}

	/**
	 * 
	 * @return RMI registry
	 */
	public Registry getRegistry() {
		return registry;
	}

	public static void startTimeout() {
		timer = new Timer();
		timerTask = new TimerTask() {

			@Override
			public void run() {

				synchronized (nextGame) {
					nextGame.initGame();
					logger.info("Game started because timeout is over");
					nullStartingGame();
				}

			}
		};
		timer.schedule(timerTask, TIMEOUT);
		logger.info("Timeout started");
	}

	public static void abortTimeout() {
		timer.cancel();
		logger.info("Timeout aborted");
	}

	public static void main(String[] args) throws RemoteException,
			AlreadyBoundException {
		logger.info("Starting server main");
		Server server = new Server();
		logger.info("Starting Socket");
		server.startSocket();
		logger.info("Starting RMI");
		server.startRMI();
	}

}