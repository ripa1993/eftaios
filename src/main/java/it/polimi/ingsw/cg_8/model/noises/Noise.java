package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.io.Serializable;

/**
 * Noise made by a player
 * 
 * @author Simone
 *
 */
public abstract class Noise implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8200186418637096004L;
	/**
	 * Turn in which noise happened
	 */
	private final int turnNumber;
	/**
	 * Coordinates in which noise happened
	 */
	private final Coordinate coordinate;
	/**
	 * Player that made noise
	 */
	private final Player player;

	/**
	 * Constructor
	 * 
	 * @param turnNumber
	 *            turn number
	 * @param player
	 *            player that made noise
	 * @param coordinate
	 *            coordinate of the player
	 */
	public Noise(int turnNumber, Player player, Coordinate coordinate) {
		this.turnNumber = turnNumber;
		this.coordinate = coordinate;
		this.player = player;

	}

	/**
	 * 
	 * @return this noise turn number
	 */
	public int getTurnNumber() {
		return turnNumber;
	}

	/**
	 * 
	 * @return this noise coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * 
	 * @return this noise player
	 */
	public Player getPlayer() {
		return player;
	}

	public String toString() {

		return " Turn: " + turnNumber + " Player: " + player.getName()
				+ " Coordinate: " + coordinate.toString();
	}

}
