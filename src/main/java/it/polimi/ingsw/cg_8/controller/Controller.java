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
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
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
	/**
	 * Allowed time per round in ms, if time is over player is disconnected
	 */
	private static final int TIMEOUT = 60000;
	/**
	 * Model of this game
	 */
	private Model model;
	/**
	 * Ruleset of this game
	 */
	private Rules rules;
	/**
	 * Map that links a clientId to a player instance
	 */
	private Map<Integer, Player> id2Player;
	/**
	 * Map that links a player instance to a clientId
	 */
	private Map<Player, Integer> player2Id;
	/**
	 * Map that links a clientId to a publisher thread
	 */
	private Map<Integer, ServerPublisher> id2Publisher;
	/**
	 * Executor service
	 */
	private ExecutorService executor;
	/**
	 * Timer used in round timeout, if timeout is over a player is disconnected
	 */
	private Timer timer;
	/**
	 * Task used in round timeout, if timeout is over a player is disconnected
	 */
	private TimerTask timerTask;
	/**
	 * Flag that shows if this is the first round of the game
	 */
	private boolean firstRun;
	/**
	 * Flag that shows if the previous player has been disconnected due to
	 * timeout
	 */
	private boolean taskCompleted;

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
			timer = new Timer();
			model.addObserver(this);
			firstRun = true;
			taskCompleted = true;
		} catch (NotAValidMapException e) {
			e.printStackTrace();
		}
		// TODO: ServerSocketPublisherThread should extend ServerPublisherThread
		// (also RMI do the same)
	}

	/**
	 * 
	 * @param id
	 *            clientId
	 * @return related player instance
	 */
	public Player getPlayerById(Integer id) {
		return id2Player.get(id);
	}

	/**
	 * 
	 * @param player
	 *            player instance
	 * @return related clientId
	 */
	public Integer getIdByPlayer(Player player) {
		return player2Id.get(player);
	}

	/**
	 * Adds a player that is using a socket connection to the game
	 * 
	 * @param id
	 *            clientId of the player
	 * @param playerName
	 *            name of the player
	 * @param pub
	 *            socket publisher thread of the player
	 * @throws GameAlreadyRunningException
	 *             if the player is trying to join an already started game
	 */
	public void addClientSocket(Integer id, String playerName,
			ServerSocketPublisherThread pub) throws GameAlreadyRunningException {
		Player tempPlayer = model.addPlayer(playerName);
		id2Player.put(id, tempPlayer);
		player2Id.put(tempPlayer, id);
		id2Publisher.put(id, pub);
		executor.submit(pub);
	}

	/**
	 * Adds a player that is using a RMI connection to the game
	 * 
	 * @param id
	 *            clientId of the player
	 * @param playerName
	 *            name of the player
	 * @param view
	 *            rmi game room, used by the publisher
	 * @throws GameAlreadyRunningException
	 *             if the player is trying to join an already started game
	 */
	public void addClientRMI(int id, String playerName, ServerGameRoom view)
			throws GameAlreadyRunningException {
		Player tempPlayer = model.addPlayer(playerName);
		id2Player.put(id, tempPlayer);
		player2Id.put(tempPlayer, id);
		id2Publisher.put(id, view);

	}

	/**
	 *  Starts the game and communicates to the players who is the first player
	 */
	public void initGame() {
		try {
			model.initGame();
			this.writeToAll(new ResponsePrivate("Match is starting..."));
			for (Player p : this.model.getPlayers()) {
				this.writeToPlayer(p, new ResponsePrivate(
						"You will be playing as:\n" + " "
								+ p.getCharacter().toString()));
			}
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
	/**
	 * 
	 * @return this game model
	 */
	public Model getModel() {
		return this.model;
	}
	/**
	 * 
	 * @return this game ruleset
	 */
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
	/**
	 * 
	 * @return number of players in the game
	 */
	public int getNumOfPlayers() {
		return player2Id.size();
	}

	@Override
	public void update(Observable o, Object arg) {

		

		/**
		 * If a noise is passed, notify the players of that noise.
		 */
		if (arg instanceof Noise) {
			this.writeToAll(new ResponseNoise(model.getLastNoiseEntry()));
		}

		/**
		 * If the game is over, disconnect the players and communicate the
		 * proper message to them.
		 */
		else if (arg.equals(TurnPhase.GAME_END)) {
			List<Player> playerList = this.model.getPlayers();

			this.writeToAll(new ResponsePrivate("GAME OVER"));
			if (this.model.checkGameEndRound() == true) {
				this.writeToAll(new ResponsePrivate(
						"The game reached its conclusion"));
			}
			if (this.model.checkGameEndNoEH() == true) {
				this.writeToAll(new ResponsePrivate(
						"There are no Escape Hatches left to use"));
			}

			for (Player p : playerList) {
				double random = Math.random();
				if (p.getCharacter() instanceof Human
						&& p.getState().equals(PlayerState.DEAD)) {
					if (random < 0.5) {
						this.writeToAll(new ResponsePrivate(p.getName()
								+ " has met a terrible fate."));
					} else {
						this.writeToAll(new ResponsePrivate(p.getName()
								+ " was slain in the darkness."));
					}
				} else if (p.getCharacter() instanceof Human
						&& p.getState().equals(PlayerState.ESCAPED)) {
					this.writeToAll(new ResponsePrivate(p.getName()
							+ " managed to escape."));
				} else if (p.getState().equals(PlayerState.DISCONNECTED)) {
					this.writeToAll(new ResponsePrivate(
							p.getName()
									+ " left the game prematurely. \n\tNobody will miss him."));
				}
			}
			/**
			 * Wait 10 seconds, then automatically disconnect every player.
			 */
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				System.err
						.println("[DEBUG] Can't sleep at the end of the game");
			}
			for (Player p : playerList) {
				Disconnect.disconnect(p);
			}
		}


		/**
		 * Player has 60 seconds to complete his turn, otherwise he is
		 * disconnected automatically
		 */
		if (arg instanceof Player) {
			final String playerName = ((Player) arg).getName();
			// remove previous timer task
			if (!firstRun && !taskCompleted) {
				timer.cancel();
			} else {
				firstRun = false;
			}
			
			//communicate new player to clients
			this.writeToAll(new ResponsePrivate("Next player is: "
					+ model.getCurrentPlayerReference().getName()));
			this.writeToPlayer(model.getCurrentPlayerReference(),
					new ResponsePrivate("IT'S YOUR TURN"));
			this.writeToPlayer(model.getCurrentPlayerReference(),
					new ResponsePrivate(model.getCurrentPlayerReference()
							.toString()));
			
			
			
			System.out.println("[DEBUG] Timeout started for player "
					+ ((Player) arg).getName() + ". He has " + (TIMEOUT / 1000)
					+ "s to complete his turn.");
			this.writeToAll(new ResponsePrivate("Timeout started for player "
					+ ((Player) arg).getName() + ". He has " + (TIMEOUT / 1000)
					+ "s to complete his turn."));
			// start new timer task
			taskCompleted = false;
			timer = new Timer();
			timerTask = new TimerTask() {

				@Override
				public void run() {
					synchronized (this) {
						System.out
								.println("Time is over, disconnecting player "
										+ playerName);
						writeToAll(new ResponsePrivate(
								"Time is over, disconnecting player "
										+ playerName));
						Disconnect
								.disconnect(model.getCurrentPlayerReference());
						taskCompleted = true;
						model.nextPlayer();
						model.setTurnPhase(TurnPhase.TURN_BEGIN);

					}
				}

			};
			timer.schedule(timerTask, TIMEOUT);

		}

	}
}
