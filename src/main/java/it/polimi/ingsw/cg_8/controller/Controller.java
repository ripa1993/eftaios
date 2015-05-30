package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.server.ServerSocketPublisherThread;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main controller class: it handles the initialization of a new game, the main
 * game loop, and communicates with both the view and the model.
 * 
 * @author Alberto Parravicini
 * 
 */
public class Controller implements Observer {

	private Model model;
	private Rules rules;
	private Map<Integer, Player> id2Player;
	private Map<Player, Integer> player2Id;
	private Map<Integer, ServerSocketPublisherThread> id2Publisher;
	private ExecutorService executor;

	/**
	 * Initialization of a new game. Note that the model is initialized with the
	 * init() function, placed inside model
	 * 
	 * @throws NotAValidMapException
	 */
	public Controller(GameMapName mapName, Rules rules) {

		try {
			this.model = new Model(mapName);
			this.rules = rules;
			this.id2Player = new HashMap<Integer, Player>();
			this.player2Id = new HashMap<Player, Integer>();
			this.executor = Executors.newCachedThreadPool();
			this.id2Publisher = new HashMap<Integer, ServerSocketPublisherThread>();
			model.addObserver(this);
		} catch (NotAValidMapException e) {
			e.printStackTrace();
		}
		// TODO: ServerSocketPublisherThread should extend ServerPublisherThread
		// (also RMI do the same)
	}

	public Player getPlayerById(Integer id) {
		return id2Player.get(id);
	}

	public Integer getIdByPlayer(Player player) {
		return player2Id.get(player);
	}

	public void addClient(Integer id, String playerName,
			ServerSocketPublisherThread pub) throws GameAlreadyRunningException {
		Player tempPlayer = model.addPlayer(playerName);
		id2Player.put(id, tempPlayer);
		player2Id.put(tempPlayer, id);
		id2Publisher.put(id, pub);
		executor.submit(pub);
	}

	public void initGame() {
		try {
			model.initGame();
			// writeToAll( la partita è iniziata )
			this.writeToAll(new ResponsePrivate("Match is starting..."));
			this.writeToAll(new ResponsePrivate("The current player is: "
					+ model.getCurrentPlayerReference().getName()));
			this.writeToPlayer(model.getCurrentPlayerReference(),
					new ResponsePrivate(model.getCurrentPlayerReference().toString()));
		} catch (EmptyDeckException e) {
			System.err.println(e.getMessage());
		}

	}

	public Model getModel() {
		return this.model;
	}

	public Rules getRules() {
		return this.rules;
	}

	/**
	 * Writes a message to all the player subscribed to this game
	 * 
	 * @param message
	 *            message to be sent
	 */
	public void writeToAll(ServerResponse message) {
		Set<Integer> ids = id2Publisher.keySet();
		Iterator<Integer> it = ids.iterator();
		while (it.hasNext()) {
			Integer current = it.next();
			id2Publisher.get(current).dispatchMessage(message);
		}
	}

	/**
	 * Writes a private message to the selected clientId
	 * 
	 * @param id
	 *            id of the receiver player
	 * @param message
	 *            message to be sent
	 */
	// TODO: RMI publisher must implement dispatchMessage(ServerResponse
	// response)
	public void writeToId(Integer id, ServerResponse message) {
		id2Publisher.get(id).dispatchMessage(message);
	}

	/**
	 * Writes a private message to the selected player
	 * 
	 * @param player
	 *            receiver player
	 * @param message
	 *            message to be sent
	 */
	public void writeToPlayer(Player player, ServerResponse message) {
		int id = player2Id.get(player);
		writeToId(id, message);
	}

	public int getNumOfPlayers() {
		return player2Id.size();
	}

	@Override
	public void update(Observable o, Object arg) {
		this.writeToAll(new ResponsePrivate(model.getNoiseLogger().toString()));
	}
}
