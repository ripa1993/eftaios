package it.polimi.ingsw.cg_8.model.sectors;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Coordinate is used to identify a sector position inside the map
 * 
 * @author Simone
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2956250090147335252L;
	/**
	 * Column number
	 */
	@XmlElement
	protected int x;
	/**
	 * Row number
	 */
	@XmlElement
	protected int y;

	/**
	 * Constructor for {@link Coordinate}, used performs checks and validations
	 * on existing coordinates.
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */

	// @ requires x>=0
	// @ requires y>=0
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;

	}

	/**
	 * Default constructor for {@link Coordinate}, used when parsing the XML
	 * document.
	 */
	public Coordinate() {

	}

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
		char letter = (char) (this.x + 65);
		String number = String.valueOf(this.y + 1);
		if (this.y >= 9) {
			return letter + number;
		} else {
			return letter + "0" + number;
		}
	}

}
