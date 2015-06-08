package it.polimi.ingsw.cg_8.model.noises;

import java.io.Serializable;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

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
	 * @param turnNumber turn number
	 * @param player player that made noise
	 * @param coordinate coordinate of the player
	 */
	public Noise(int turnNumber, Player player, Coordinate coordinate) {
		this.turnNumber = turnNumber;
		this.coordinate = coordinate;
		this.player = player;
	}

	public String toString() {
		return " Turn: " + turnNumber + " Player: " + player.getName()
				+ " Coordinate: " + coordinate.toString();
	}
}
