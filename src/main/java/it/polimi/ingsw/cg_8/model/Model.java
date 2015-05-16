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
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.map.creator.FermiCreator;
import it.polimi.ingsw.cg_8.model.map.creator.GalileiCreator;
import it.polimi.ingsw.cg_8.model.map.creator.GalvaniCreator;
import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Contains all the references to game objects: decks, players and map.
 * 
 * @author Simone
 *
 */
public class Model {
	/**
	 * List of players in the current game
	 */
	private final List<Player> players;
	/**
	 * Number of current round. It is increased when all the players have played
	 * it.
	 */
	private int roundNumber;
	/**
	 * Position in the array players of current player
	 */
	private int currentPlayer;
	/**
	 * Position in the array players of first player to play
	 */
	private int startingPlayer;
	/**
	 * Current game phase
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

	public Model(GameMapName mapName) {
		players = new ArrayList<Player>();
		roundNumber = 0;
		currentPlayer = 0;
		turnPhase = TurnPhase.GAME_SETUP;
		characterDeck = new CharacterDeck();
		dangerousSectorDeck = new DangerousSectorDeck();
		escapeHatchDeck = new EscapeHatchDeck();
		itemDeck = new ItemDeck();
		// initialize map
		switch (mapName) {
		case FERMI: {
			MapCreator mc = new FermiCreator();
			map = mc.createMap();
			break;
		}
		case GALILEI: {
			MapCreator mc = new GalileiCreator();
			map = mc.createMap();
			break;
		}
		case GALVANI: {
			MapCreator mc = new GalvaniCreator();
			map = mc.createMap();
			break;
		}
		default: {
			// TODO: throw NotAValidMapException
		}
		}
	}

	/**
	 * This method adds a new player to a starting game
	 * 
	 * @param name
	 *            name of the player
	 */
	public void addPlayer(String name) {
		if (turnPhase == TurnPhase.GAME_SETUP) {
			Player tempPlayer = new Player(name);
			players.add(tempPlayer);
		} else {
			// TODO: throw GameAlreadyRunningException
		}
	}

	/**
	 * This method removes a player from a starting game
	 */
	public void removePlayer(Player player) {
		try {
			if (turnPhase == TurnPhase.GAME_SETUP) {
				players.remove(player);
			} else {
				// TODO: throw GameAlreadyRunningException
			}

		} catch (Exception e) {

		}
	}

	/**
	 * Initializes the game. It populates the decks, assign a character to each
	 * player and changes the turnPhase to TURN_BEGIN
	 */
	public void initGame() {
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
			} else {
				// TODO: throw NotACharacterCardException;
			}
		}

		// random picks first player
		Random random = new Random();
		currentPlayer = random.nextInt(players.size());
		nextPlayer();
		startingPlayer = currentPlayer;
		// sets turn phase and round number
		roundNumber = 1;
		turnPhase = TurnPhase.TURN_BEGIN;

	}

	/**
	 * Changes current player to next player in list. Increases roundNumber if a
	 * complete cycle has been done.
	 */
	public void nextPlayer() {
		int tempNextPlayer = currentPlayer + 1;
		if (tempNextPlayer == players.size()) {
			tempNextPlayer = 0;
		}

		if (tempNextPlayer == startingPlayer) {
			roundNumber++;
		}

		if (players.get(tempNextPlayer).getState() == PlayerState.ALIVE_WAITING) {
			currentPlayer = tempNextPlayer;
		} else {
			nextPlayer();
		}
	}
	public List<Player> getPlayers() {
		return players;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public int getStartingPlayer() {
		return startingPlayer;
	}

	public TurnPhase getTurnPhase() {
		return turnPhase;
	}

	public Deck getCharacterDeck() {
		return characterDeck;
	}

	public Deck getDangerousSectorDeck() {
		return dangerousSectorDeck;
	}

	public Deck getEscapeHatchDeck() {
		return escapeHatchDeck;
	}

	public Deck getItemDeck() {
		return itemDeck;
	}

	public GameMap getMap() {
		return map;
	}

}
