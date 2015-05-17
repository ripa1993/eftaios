package it.polimi.ingsw.cg_8.model.player;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * Round class is used in the {@link it.polimi.ingsw.cg_8.model.Model Model} to
 * keep the history of the player movements.
 * 
 * @author Simone
 *
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Round other = (Round) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (number != other.number)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Round [number=" + number + ", coordinate=" + coordinate + "]";
	}

}
