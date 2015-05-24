package it.polimi.ingsw.cg_8.model.sectors;

import java.io.Serializable;

/**
 * Coordinate is used to identify a sector position inside the map
 * 
 * @author Simone
 *
 */
public class Coordinate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2956250090147335252L;
	/**
	 * Column number
	 */
	final int x;
	/**
	 * Row number
	 */
	final int y;

	/**
	 * Getter for column
	 * 
	 * @return column number
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter for row
	 * 
	 * @return row number
	 */
	public int getY() {
		return y;
	}

	/**
	 * Constructor for {@link Coordinate}
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */

	//@ requires x>=0
	//@ requires y>=0
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
		
	}

	/**
	 * Default constructor for {@link Coordinate}, creates a non valid coordinate
	 * (negative value)
	 */
	public Coordinate() {
		x = -1;
		y = -1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}
}
