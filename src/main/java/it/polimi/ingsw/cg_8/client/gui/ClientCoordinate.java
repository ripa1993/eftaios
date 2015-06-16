package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Dummy coordinate used by the XML parser of the client.
 * 
 * @author Alberto
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientCoordinate {

	/**
	 * The horizontal coordinate.
	 */
	@XmlElement
	private int x;
	/**
	 * The vertical coordinate.
	 */
	@XmlElement
	private int y;

	/**
	 * The empty constructor is used by JAXB; X and Y are actually assigned by
	 * this constructor.
	 */
	public ClientCoordinate() {

	}

	/**
	 * Constructor used to create a ClientCoordinate from a Coordinate, used to
	 * perform check bewteen coordiantes on the client.
	 * 
	 * @param coordinate
	 */
	public ClientCoordinate(Coordinate coordinate) {
		this.x = coordinate.getX();
		this.y = coordinate.getY();
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
		ClientCoordinate other = (ClientCoordinate) obj;
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
