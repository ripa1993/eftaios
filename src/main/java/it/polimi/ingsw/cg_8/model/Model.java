package it.polimi.ingsw.cg_8.model;

import it.polimi.ingsw.cg_8.model.cards.characterCards.AlienCard;
import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;
import it.polimi.ingsw.cg_8.model.cards.characterCards.HumanCard;
import it.polimi.ingsw.cg_8.model.decks.CharacterDeck;
import it.polimi.ingsw.cg_8.model.decks.DangerousSectorDeck;
import it.polimi.ingsw.cg_8.model.decks.Deck;
import it.polimi.ingsw.cg_8.model.decks.EscapeHatchDeck;
import it.polimi.ingsw.cg_8.model.decks.ItemDeck;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.CharacterDeckCreator;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.DangerousSectorDeckCreator;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.EscapeHatchDeckCreator;
import it.polimi.ingsw.cg_8.model.decks.deckCreators.ItemDeckCreator;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.map.creator.FermiCreator;
import it.polimi.ingsw.cg_8.model.map.creator.GalileiCreator;
import it.polimi.ingsw.cg_8.model.map.creator.GalvaniCreator;
import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains all the references to game objects: decks, players and map.
 * 
 * @author Simone
 * @version 1.0
 */
public class Model extends Observable {
	/**
	 * Not a valid map text
	 */
	private static final String NOT_VALID_MAP = "is not a valid map";
	/**
	 * List of players in the current game
	 */
	private final List<Player> players;
	/**
	 * The maximum number of turn allowed in the game; if this number is
	 * reached, the game ends with a draw.
	 */
	private static final int MAX_ROUND_NUMBER = 39;
	/**
	 * Number of current round. It is increased when all the players have played
	 * it.
	 */
	private int roundNumber;
	/**
	 * Position in the array players of current player
	 */
	private int currentPlayerIndex;
	/**
	 * Position in the array players of first player to play
	 */
	private int startingPlayerIndex;
	/**
	 * Reference to the starting player
	 */
	private TurnPhase turnPhase;
	/**
	 * Character card deck
	 */
	private Deck characterDeck;
	/**
	 * Dangerous sector card deck
	 */
	private Deck dangerousSectorDeck;
	/**
	 * Escape hatch card deck
	 */
	private Deck escapeHatchDeck;
	/**
	 * Item card deck
	 */
	private Deck itemDeck;

	/**
	 * Current map
	 */
	private GameMap map;
	/**
	 * List of all noises during a game
	 */
	private final List<Noise> noiseLogger;
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(Model.class);

	/**
	 * Constructor for model class
	 * 
	 * @param mapName
	 *            name of the map to be created in the model
	 * @throws NotAValidMapException
	 */
	public Model(GameMapName mapName) throws NotAValidMapException {
		players = new ArrayList<Player>();
		roundNumber = 0;
		currentPlayerIndex = 0;
		turnPhase = TurnPhase.GAME_SETUP;
		noiseLogger = new ArrayList<Noise>();
		characterDeck = new CharacterDeck();
		dangerousSectorDeck = new DangerousSectorDeck();
		escapeHatchDeck = new EscapeHatchDeck();
		itemDeck = new ItemDeck();
		// initialize map
		if (mapName == GameMapName.FERMI) {
			MapCreator mc = new FermiCreator();
			map = mc.createMap();
		} else if (mapName == GameMapName.GALILEI) {
			MapCreator mc = new GalileiCreator();
			map = mc.createMap();
		} else if (mapName == GameMapName.GALVANI) {
			MapCreator mc = new GalvaniCreator();
			map = mc.createMap();
		} else {
			throw new NotAValidMapException(mapName + NOT_VALID_MAP);
		}

	}

	/**
	 * This method adds a new player to a starting game
	 * 
	 * @param name
	 *            name of the player
	 * @throws GameAlreadyRunningException
	 */
	public Player addPlayer(String name) throws GameAlreadyRunningException {
		if (turnPhase == TurnPhase.GAME_SETUP) {
			Player tempPlayer = new Player(name);
			players.add(tempPlayer);
			return tempPlayer;
		} else {
			throw new GameAlreadyRunningException(
					"Game is already running, can't add a new player");
		}
	}

	/**
	 * This method removes a player from a starting game
	 */
	public void removePlayer(Player player) throws GameAlreadyRunningException {

		if (turnPhase == TurnPhase.GAME_SETUP) {
			players.remove(player);
		} else {
			throw new GameAlreadyRunningException(
					"Game is already running, can't remove a player");
		}

	}

	/**
	 * Initializes the game. It populates the decks, assign a character to each
	 * player and changes the turnPhase to TURN_BEGIN
	 * 
	 * @throws EmptyDeckException
	 */
	public void initGame() throws EmptyDeckException {
		// initialize decks
		int numPlayers = players.size();
		CharacterDeckCreator characterDeckCreator = new CharacterDeckCreator();
		DangerousSectorDeckCreator dangerousSectorDeckCreator = new DangerousSectorDeckCreator();
		EscapeHatchDeckCreator escapeHatchDeckCreator = new EscapeHatchDeckCreator();
		ItemDeckCreator itemDeckCreator = new ItemDeckCreator();
		characterDeck = characterDeckCreator.createDeck(numPlayers);
		dangerousSectorDeck = dangerousSectorDeckCreator.createDeck();
		escapeHatchDeck = escapeHatchDeckCreator.createDeck();
		itemDeck = itemDeckCreator.createDeck();
		// shuffle decks
		characterDeck.shuffle();
		dangerousSectorDeck.shuffle();
		escapeHatchDeck.shuffle();
		itemDeck.shuffle();
		// initialize map
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player tempPlayer = it.next();
			CharacterCard tempCard = (CharacterCard) characterDeck.drawCard();
			if (tempCard instanceof AlienCard) {
				InGameCharacter currentCharacter = new Alien(tempCard);
				tempPlayer.init(currentCharacter, map.getAlienSpawn());
			} else if (tempCard instanceof HumanCard) {
				InGameCharacter currentCharacter = new Human(tempCard);
				tempPlayer.init(currentCharacter, map.getHumanSpawn());
			}
		}

		// random picks first player
		Random random = new Random();
		currentPlayerIndex = random.nextInt(players.size());
		nextPlayer();
		startingPlayerIndex = currentPlayerIndex;
		// sets turn phase and round number
		roundNumber = 1;
		turnPhase = TurnPhase.TURN_BEGIN;

	}

	/**
	 * Changes current player to next player in list. Increases roundNumber if a
	 * complete cycle has been done.
	 */
	public void nextPlayer() {

		if (checkGameEndNoEH()) {
			// no escape hatches left
			this.setGameOver();
			return;
		}

		if (checkGameEndNoPlayers()) {
			// all player dc'ed or dead or escaped
			this.setGameOver();
			return;
		}

		if (checkGameEndNoHumans()) {
			// no humans left
			this.setGameOver();
			return;
		}

		int counter = 0;

		for (Player p : players) {
			if (p.getState() == PlayerState.ALIVE) {
				counter++;
			}
		}
		if (counter > 1) {
			int tempNextPlayer = currentPlayerIndex + 1;
			if (tempNextPlayer == players.size()) {
				tempNextPlayer = 0;
			}

			if (tempNextPlayer == startingPlayerIndex) {
				this.roundNumber++;
			}

			if (checkGameEndRound()) {
				// finished round 39, so game ends
				this.setGameOver();
				return;
			}
			currentPlayerIndex = tempNextPlayer;
			if (players.get(currentPlayerIndex).getState() == PlayerState.ALIVE) {
				setChanged();
				notifyObservers(getCurrentPlayerReference());
				return;
			} else {
				nextPlayer();
			}
		} else {
			/**
			 * If there is only one player alive, and he is Human, he has to
			 * play.
			 */
			for (int i = 0; i < this.getPlayers().size(); i++) {
				Player p = this.getPlayers().get(i);
				if (p.getState() == PlayerState.ALIVE) {
					currentPlayerIndex = i;
				}
			}
			this.roundNumber++;
			if (checkGameEndRound()) {
				// finished round 39, so game ends
				this.setGameOver();
				return;
			}
			setChanged();
			notifyObservers(getCurrentPlayerReference());
			return;
		}
		return;
	}

	/**
	 * Check if turn number 40 has been reached
	 * 
	 * @return true, if game ends<br>
	 *         false, if not
	 */
	public boolean checkGameEndRound() {
		if (roundNumber > MAX_ROUND_NUMBER) {
			return true;
		}
		return false;
	}

	/**
	 * Check if there is at least 1 human left
	 * 
	 * @return true, if game ends<br>
	 *         false, if not
	 */
	private boolean checkGameEndNoHumans() {
		// not all humans dead or escaped
		int counterHumans = 0;
		for (Player p : players) {
			if (p.getCharacter() instanceof Human
					&& (p.getState() == PlayerState.ALIVE)) {
				counterHumans++;
			}
		}
		if (counterHumans == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Check if there is at least a player not dead or disconnected or escaped
	 * 
	 * @return true, if game ends<br>
	 *         false, if not
	 */
	private boolean checkGameEndNoPlayers() {
		// no one wants to play: all disconnected, dead or escaped
		int counterPlaying = 0;
		for (Player p : players) {
			if (p.getState() == PlayerState.ALIVE) {
				counterPlaying++;
			}
		}
		if (counterPlaying == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Check if not all escape hatches have been used
	 * 
	 * @return true, if game ends<br>
	 *         false, if not
	 */
	public boolean checkGameEndNoEH() {
		// 4th escape hatch card drawn, so 4th escape hatch used
		if (getEscapeHatchDeck().getCards().size() == 2) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return a reference to the current player
	 */
	public Player getCurrentPlayerReference() {
		return this.getPlayers().get(this.getCurrentPlayer());
	}

	/**
	 * 
	 * @return list of players in this game
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * 
	 * @return current round number
	 */
	public int getRoundNumber() {
		return roundNumber;
	}

	/**
	 * 
	 * @return current player index in getPlayers() list
	 */
	public int getCurrentPlayer() {
		return currentPlayerIndex;
	}

	/**
	 * 
	 * @return starting player index in getPlayers() list
	 */
	public int getStartingPlayer() {
		return startingPlayerIndex;
	}

	/**
	 * 
	 * @return turn phase
	 */
	public TurnPhase getTurnPhase() {
		return turnPhase;
	}

	/**
	 * 
	 * @return character deck for this game
	 */
	public Deck getCharacterDeck() {
		return characterDeck;
	}

	/**
	 * 
	 * @return dangerous sector deck for this game
	 */
	public Deck getDangerousSectorDeck() {
		return dangerousSectorDeck;
	}

	/**
	 * 
	 * @return escape hatch deck for this game
	 */
	public Deck getEscapeHatchDeck() {
		return escapeHatchDeck;
	}

	/**
	 * 
	 * @return item deck for this game
	 */
	public Deck getItemDeck() {
		return itemDeck;
	}

	/**
	 * 
	 * @return game map for this game
	 */
	public GameMap getMap() {
		return map;
	}

	/**
	 * 
	 * @return list of noise for this game
	 */
	public List<Noise> getNoiseLogger() {
		return noiseLogger;
	}

	/**
	 * Return the last noise saved in the logger: used to notify players of a
	 * noise produced by someone else.
	 * 
	 * @return The last noise produced in the game.
	 */
	public Noise getLastNoiseEntry() {
		return this.noiseLogger.get(this.noiseLogger.size() - 1);
	}

	/**
	 * Adds a nois to getNoiseLogger list of noise and notifies all the
	 * observers
	 * 
	 * @param noise
	 *            noise to be added
	 */
	public void addNoise(Noise noise) {
		this.noiseLogger.add(noise);
		this.setChanged();
		this.notifyObservers(noise);
	}

	/**
	 * Changes the turn phase
	 * 
	 * @param newTurnPhase
	 *            new turn phase
	 */
	public void setTurnPhase(TurnPhase newTurnPhase) {
		this.turnPhase = newTurnPhase;
	}

	/**
	 * Called by some Actions and by the model itself in order to end the game.
	 * When this method is called, it notifies the controller that will
	 * disconnect the still connected players in 10 seconds and send ending game
	 * messages to those players
	 */
	public void setGameOver() {
		this.turnPhase = TurnPhase.GAME_END;
		this.setChanged();
		this.notifyObservers(this.turnPhase);
	}

	/**
	 * Changes the map
	 * 
	 * @param chosenMap
	 *            chosen map that will be created
	 */
	public void setMap(GameMapName chosenMap) {
		if (chosenMap == GameMapName.FERMI) {
			MapCreator mc = new FermiCreator();
			map = mc.createMap();
		} else if (chosenMap == GameMapName.GALILEI) {
			MapCreator mc = new GalileiCreator();
			map = mc.createMap();
		} else if (chosenMap == GameMapName.GALVANI) {
			MapCreator mc = new GalvaniCreator();
			map = mc.createMap();
		} else {
			try {
				throw new NotAValidMapException(chosenMap + NOT_VALID_MAP);
			} catch (NotAValidMapException e) {
				LOGGER.error(chosenMap.toString() + NOT_VALID_MAP, e);
			}
		}
	}

	@Override
	public String toString() {
		return "Model [players=" + players + ", roundNumber=" + roundNumber
				+ ", currentPlayer=" + currentPlayerIndex + ", startingPlayer="
				+ startingPlayerIndex + ", turnPhase=" + turnPhase
				+ ", characterDeck=" + characterDeck + ", dangerousSectorDeck="
				+ dangerousSectorDeck + ", escapeHatchDeck=" + escapeHatchDeck
				+ ", itemDeck=" + itemDeck + ", map=" + map + "]";
	}

}
