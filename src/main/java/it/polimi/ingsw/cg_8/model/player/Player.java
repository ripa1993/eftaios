package it.polimi.ingsw.cg_8.model.player;

import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.player.character.human.NormalHuman;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Player class. Used in {@link it.polimi.ingsw.cg_8.model.Model Model} to keep
 * trace of all the players in the game.
 * 
 * @author Simone
 * @version 1.0
 */
public class Player implements Serializable {


	private static final long serialVersionUID = -6339404514199154790L;
	/**
	 * This player state
	 */
	private PlayerState state;
	/**
	 * This player name
	 */
	private final String name;
	/**
	 * This player character
	 */
	private transient InGameCharacter character;
	/**
	 * This player list of played rounds
	 */
	private transient final List<Round> rounds;
	/**
	 * This player hand, contains the owned item cards
	 */
	private transient final Hand hand;

	// a player is created when he joins the game (match still not running)
	/**
	 * Constructor for player, a player is created when he joins the game, while
	 * the match is still not running. He can't join in other phases
	 * 
	 * @param name
	 *            name of the player
	 */
	public Player(String name) {
		state = PlayerState.CONNECTING;
		this.name = name;
		character = null;
		rounds = new ArrayList<Round>();
		hand = new Hand();
	}

	/**
	 * Player is initialized when the match actually starts
	 * 
	 * @param character
	 *            player's role, given after drawing a character card
	 * @param startingPosition
	 *            starting position according to current map and player's
	 *            character
	 */
	public void init(InGameCharacter character, Coordinate startingPosition) {
		this.character = character;
		state = PlayerState.ALIVE;
		rounds.add(new Round(0, startingPosition));
	}

	/**
	 * Getter for round number
	 * 
	 * @return this player's round number
	 */
	public int getRoundNumber() {
		return rounds.size() - 1;
	}

	/**
	 * Getter for last position
	 * 
	 * @return this player's last position
	 */
	public Coordinate getLastPosition() {
		return rounds.get(getRoundNumber()).getCoordinate();
	}

	/**
	 * Setter for new position and its new round. Creates a new {@link Round}
	 * with the given position and current round number
	 * 
	 * @param position
	 *            position after movement
	 */
	public void setPosition(Coordinate position) {
		rounds.add(new Round(getRoundNumber() + 1, position));
	}

	// may be used when the player wants to be teleported after having moved,
	// not sure if legal
	/**
	 * Change last position in {@link Round} for example if the player wants to
	 * be teleported after having completed his movement phase. No need to
	 * create a new {@link Round} but it just updates the latest.
	 * 
	 * @param newCoordinate
	 *            new position that overwrites the last one
	 */
	public void editLastPosition(Coordinate newCoordinate) {
		int lastRoundNumber = getRoundNumber();
		rounds.remove(lastRoundNumber);
		setPosition(newCoordinate);
	}

	/**
	 * Set the player status to {@link PlayerState#DISCONNECTED DISCONNECTED}
	 */
	public void setDisconnected() {
		state = PlayerState.DISCONNECTED;
	}

	/**
	 * Set the player status to {@link PlayerState#DEAD DEAD}
	 */
	public void setDead() {
		state = PlayerState.DEAD;
	}

	/**
	 * Set the player status to {@link PlayerState#ESCAPED ESCAPED}
	 */
	public void setEscaped() {
		state = PlayerState.ESCAPED;
	}

	/*
	 * /** Changes the player status from {@link PlayerState#ALIVE_WAITING
	 * ALIVE_WAITING} to {@link PlayerState#ALIVE_PLAYING ALIVE_PLAYING} and
	 * viceversa
	 * 
	 * public void cycleState() { if (state == PlayerState.ALIVE_WAITING) {
	 * state = PlayerState.ALIVE_PLAYING; resetDecorations(); } else if (state
	 * == PlayerState.ALIVE_PLAYING) { state = PlayerState.ALIVE_WAITING; } }
	 */

	/**
	 * Restores human character to its original state. It is called
	 * automatically at the begin of a player's turn
	 */
	public void resetDecorations() {
		if (this.character instanceof Human) {
			((Human) this.character).setBehaviour(new NormalHuman());
		}
	}

	/**
	 * Getter for player's hand
	 * 
	 * @return this player's hand
	 */
	public Hand getHand() {
		return hand;
	}

	/**
	 * Getter for player state
	 * 
	 * @return this player's state
	 */
	public PlayerState getState() {
		return state;
	}

	/**
	 * Getter for player name
	 * 
	 * @return this player's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for player character
	 * 
	 * @return this player's character
	 */
	public InGameCharacter getCharacter() {
		return character;
	}

	@Override
	public String toString() {
		return "Player: " + name + ", Character: " + character + " "
				+ ", State: " + state + ", Position: " + getLastPosition() ;

	}

}
