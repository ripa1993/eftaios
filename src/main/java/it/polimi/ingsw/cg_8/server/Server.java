package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.DefaultRules;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static int clientId = 0;
	private static Controller nextGame;
	private final static int SERVER_SOCKET_RR_PORT = 29998;
	private final static int SERVER_SOCKET_PS_PORT = 29999;
	private static Map<Integer, Controller> id2Controller;
	
	public static Map<Integer, Controller> getId2Controller() {
		return id2Controller;
	}

	public static int getClientId() {
		return clientId;
	}

	public static void increaseClientId() {
		clientId++;
	}

	public static Controller getStartingGame() {
		return nextGame;
	}

	public static Controller createNewGame(GameMapName gameMapName) {
		nextGame = new Controller(gameMapName, new DefaultRules());
		id2Controller = new HashMap<Integer, Controller>();
		return nextGame;
	}

	public static void nullStartingGame(){
		nextGame = null;
	}
	
	public Server() {
		nextGame = null;
	}

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		try {
			executor.submit(new ServerSocketRRThread(SERVER_SOCKET_RR_PORT, SERVER_SOCKET_PS_PORT));
		} catch (IOException e) {
			System.err.println("Cannot start server socket CS on port: "
					+ SERVER_SOCKET_RR_PORT);
		}
	}
}
