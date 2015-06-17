package it.polimi.ingsw.cg_8.model.sectors.special.spawn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * Human spawn sector class, it's the sector where humans start on the map. Only
 * one per map.
 * 
 * @author Simone
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HumanSector extends SpawnSector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3422910957939391661L;

	/**
	 * Constructor for {@link HumanSector}, used to performs checks and
	 * validations.
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	public HumanSector(int x, int y) {
		super(x, y);
	}

	/**
	 * Default constructor used in the XML parsing.
	 */
	public HumanSector() {
		super();
	}

	@Override
	public String toString() {
		return "HS";
	}

}
