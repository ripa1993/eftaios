package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.client.ClientData;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;
import it.polimi.ingsw.cg_8.view.server.ResponseCard;
import it.polimi.ingsw.cg_8.view.server.ResponseChat;
import it.polimi.ingsw.cg_8.view.server.ResponseMap;
import it.polimi.ingsw.cg_8.view.server.ResponseNoise;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ResponseState;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterCLI implements Runnable, Observer {
	/**
	 * Client data, used to store server messages
	 */
	private ClientData clientData;
	/**
	 * Flag that signals if the game is started
	 */
	private boolean matchStarted;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(BetterCLI.class);
	/**
	 * System in scanner
	 */
	private Scanner input;
	/**
	 * Connection manager that handles the connection
	 */
	private ConnectionManager connectionManager;
	/**
	 * Flag that signals if a player has decided to quit the game
	 */
	private boolean disconnect;

	/**
	 * Constructor, creates the CLI instance
	 */
	public BetterCLI() {
		clientData = new ClientData();
		input = new Scanner(System.in);
	}

	private void setup() {
		/**
		 * Player name
		 */
		String playerName = "";
		/**
		 * Player has chosen connection method?
		 */
		boolean methodSet = false;
		/**
		 * Player connection method
		 */
		String chosenMethod = "";
		/**
		 * Player has chosen the fav map?
		 */
		boolean mapSet = false;
		/**
		 * Player chosen map
		 */
		GameMapName chosenMap = GameMapName.FERMI;
		// get player name
		do {
			System.out.println(">Insert your player name:");
			playerName = input.nextLine();
		} while (playerName.equals(""));
		logger.debug("Name set: " + playerName);
		// get connection method
		do {
			System.out.println(">Choose connection method:");
			System.out.println("(1) RMI");
			System.out.println("(2) Socket");
			chosenMethod = input.nextLine();
			if (chosenMethod.equals("1") || chosenMethod.equals("2")) {
				methodSet = true;
			}
		} while (!methodSet);
		logger.debug("Method set: " + chosenMethod);
		// get chosen map
		do {
			System.out.println(">Choose your favorite map:");
			System.out.println("(1) Fermi");
			System.out.println("(2) Galvani");
			System.out.println("(3) Galilei");
			String line = input.nextLine();
			if (line.equals("1")) {
				chosenMap = GameMapName.FERMI;
				mapSet = true;
			} else if (line.equals("2")) {
				chosenMap = GameMapName.GALVANI;
				mapSet = true;
			} else if (line.equals("3")) {
				chosenMap = GameMapName.GALILEI;
				mapSet = true;
			}
		} while (!mapSet);
		logger.debug("Map set: " + chosenMap);
		// name, connection and map set. now create connection
		if (chosenMethod.equals("1")) {
			connectionManager = new ConnectionManagerRMI(playerName, chosenMap);
			logger.debug("RMI connection manager created");
		} else {
			connectionManager = new ConnectionManagerSocket(playerName,
					chosenMap);
			logger.debug("Socket connection manager created");
		}
		clientData = connectionManager.getClientData();
		clientData.addObserver(this);
		logger.debug("Observer added to clientData");
		connectionManager.setup();
		logger.debug("Connection manager setup");
	}

	@Override
	public void run() {
		setup();
		while (!disconnect) {
			System.out.println(">Write a command:");
			String inputLine = input.nextLine();
			try {
				ClientAction action = ActionParser.createEvent(inputLine);
				connectionManager.send(action);
			} catch (NotAValidInput e) {
				System.out.println(">That's not a valid command");
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals("Chat")) {
			logger.debug("New Update: CHAT");
			// new chat message
			ResponseChat chat = clientData.getLastChat();
			System.out.println("[CHAT] " + chat.getPlayerName() + ": "
					+ chat.getMessage());
		} else if (arg.equals("Noise")) {
			logger.debug("New Update: NOISE");
			// new noise
			ResponseNoise noise = clientData.getLastNoise();
			System.out.println("[NOISE] " + noise.toString());
		} else if (arg.equals("Private")) {
			logger.debug("New Update: PRIVATE");
			// new private message
			ResponsePrivate privateMessage = clientData.getLastPrivate();
			System.out.println("[INFO] " + privateMessage.getMessage());
		} else if (arg.equals("Cards")) {
			logger.debug("New Update: CARDS");
			// new card update
			ResponseCard cardMessage = clientData.getCards();
			System.out.println("[HAND] Your hand is: " + cardMessage.getCard1()
					+ ", " + cardMessage.getCard2() + " and "
					+ cardMessage.getCard3());
		} else if (arg.equals("State")) {
			logger.debug("New Update: STATE");
			// new state update
			ResponseState stateMessage = clientData.getState();
			System.out.println("[STATE] Hi " + stateMessage.getPlayerName()
					+ ". You are playing as " + stateMessage.getCharacter());
			System.out.println("[STATE] Your state is "
					+ stateMessage.getState() + " and your position is "
					+ stateMessage.getPosition());
		} else if (arg.equals("Map")) {
			logger.debug("New Update: MAP");
			// new map update, happens only on game start.
			// CLI player is supposed to know the map, so no map "art" is
			// printed
			ResponseMap response = clientData.getMap();
			System.out.println("[MAP] The game is started. You will play on "
					+ response.getMapName());
			matchStarted = true;
		}

	}

	public static void main(String[] args) {
		(new BetterCLI()).run();
	}
}
