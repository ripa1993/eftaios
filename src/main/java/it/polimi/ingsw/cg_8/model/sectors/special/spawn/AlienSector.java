package it.polimi.ingsw.cg_8.model.sectors.special.spawn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Alien spawn sector class, it's the sector where aliens start on the map. Only
 * one per map.
 * 
 * @author Simone
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AlienSector extends SpawnSector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8065999857578160970L;

	/**
	 * Constructor for {@link AlienSector}, used to performs checks
	 * and validations.
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	public AlienSector(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Default constructor used in the XML parsing.
	 */
	public AlienSector() {
		super();
	}

	@Override
	public String toString() {
		return "AS";
	}

}
