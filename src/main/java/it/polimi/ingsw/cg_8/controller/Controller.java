package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.controller.playerActions.otherActions.Disconnect;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.TurnPhase;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.server.ServerGameRoom;
import it.polimi.ingsw.cg_8.server.ServerPublisher;
import it.polimi.ingsw.cg_8.server.ServerSocketPublisherThread;
import it.polimi.ingsw.cg_8.view.server.ResponseNoise;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
	private Map<Integer, ServerPublisher> id2Publisher;
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
			this.id2Publisher = new HashMap<Integer, ServerPublisher>();
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

	// Hai un riferimento a un thread timer: quando viene chiamata una delle due
	// AddClient si controlla che il thread sia null (oppure uso un boolean di
	// controllo), e lo si inizializza.
	// Passati 60 secondi, il thread verifica che la partita non sia già
	// cominciata (gameState == running, se si hanno già 8 giocatori), e in tal
	// caso la fa partire.
	// Se si hanno 8 giocatori prima dei 60 secondi: faccio partire a livello
	// dei 2 server, metto nel controller un metodo comune però (che chiama
	// this.initGame() ecc...).
	public void addClientSocket(Integer id, String playerName,
			ServerSocketPublisherThread pub) throws GameAlreadyRunningException {
		Player tempPlayer = model.addPlayer(playerName);
		id2Player.put(id, tempPlayer);
		player2Id.put(tempPlayer, id);
		id2Publisher.put(id, pub);
		executor.submit(pub);
	}

	public void addClientRMI(int id, String playerName, ServerGameRoom view)
			throws GameAlreadyRunningException {
		Player tempPlayer = model.addPlayer(playerName);
		id2Player.put(id, tempPlayer);
		player2Id.put(tempPlayer, id);
		id2Publisher.put(id, view);

	}

	public void initGame() {
		try {
			model.initGame();
			// writeToAll( la partita è iniziata )
			this.writeToAll(new ResponsePrivate("Match is starting..."));
			this.writeToAll(new ResponsePrivate("The current player is: "
					+ model.getCurrentPlayerReference().getName()));
			this.writeToPlayer(model.getCurrentPlayerReference(),
					new ResponsePrivate(model.getCurrentPlayerReference()
							.toString()));
			this.writeToPlayer(model.getCurrentPlayerReference(),
					new ResponsePrivate("IT'S YOUR TURN!"));
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
			this.writeToId(current, message);
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
	public void writeToId(Integer id, ServerResponse message) {
		if (this.id2Player.get(id).getState() != PlayerState.DISCONNECTED) {
			id2Publisher.get(id).dispatchMessage(message);
		}
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

		this.writeToAll(new ResponseNoise(model.getLastNoiseEntry()));

		/**
		 * If a noise is passed, notify the players of that noise.
		 */
		if (arg instanceof Noise) {
			this.writeToAll(new ResponsePrivate(arg.toString()));
		}
		
		/**
		 * If the game is over, disconnect the players and communicate the proper message to them.
		 */
		else if (arg.equals(TurnPhase.GAME_END)) {
			List<Player> playerList = this.model.getPlayers();
			
			this.writeToAll(new ResponsePrivate("GAME OVER"));
			if (this.model.checkGameEndRound() == true) {
				this.writeToAll(new ResponsePrivate("The game reached its conclusion"));
			}
			if (this.model.checkGameEndNoEH() == true) {
				this.writeToAll(new ResponsePrivate("There are no Escape Hatches left to use"));
			}
			
			for (Player p : playerList) {
				double random = Math.random();
				if (p.getCharacter() instanceof Human && p.getState().equals(PlayerState.DEAD)) {
					if (random < 0.5) {
						this.writeToAll(new ResponsePrivate(p.getName() + " has met a terrible fate."));
					} else {
						this.writeToAll(new ResponsePrivate(p.getName() + " was slain in the darkness."));
					}
				}
				else if (p.getCharacter() instanceof Human && p.getState().equals(PlayerState.ESCAPED)) {
					this.writeToAll(new ResponsePrivate(p.getName() + " managed to escape."));
				}
				else if (p.getState().equals(PlayerState.DISCONNECTED)) {
					this.writeToAll(new ResponsePrivate(p.getName() + " left the game prematurely. \n\tNobody will miss him."));
				}
			}
			/**
			 * Wait 10 seconds, then automatically disconnect every player.
			 */
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				System.err.println("[DEBUG] Can't sleep at the end of the game");
			}
			for (Player p : playerList) {
				Disconnect.disconnect(p);
			}
		}

	}
}
