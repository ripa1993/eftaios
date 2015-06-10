package it.polimi.ingsw.cg_8.model.sectors.normal;

/**
 * Dangerous sector. When a player ends his turn in this sector, he can draw a
 * dangerous sector card or attack.
 * 
 * @author Simone
 * @version 1.0
 */
public class DangerousSector extends NormalSector {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7964533045952694323L;

	/**
	 * Constructor for dangerous sector
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	public DangerousSector(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "DS";
	}

}
