package it.polimi.ingsw.cg_8.model.player;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * Round class is used in the {@link it.polimi.ingsw.cg_8.model.Model Model} to
 * keep the history of the player movements.
 * 
 * @author Simone
 * @version 1.0
 */
public class Round {
	/**
	 * Turn number
	 */
	private final int number;
	/**
	 * Ending position in the current turn
	 */
	private final Coordinate coordinate; 

	/**
	 * Constructor for round
	 * @param number round number
	 * @param coordinate ending position
	 */
	public Round(int number, Coordinate coordinate) {
		this.number = number;
		this.coordinate = coordinate;
	}
	/**
	 * Getter for round number
	 * @return round number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Getter for ending position coordinate
	 * @return round coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	@Override
	public String toString() {
		return "Round [number=" + number + ", coordinate=" + coordinate + "]";
	}

}
