package it.polimi.ingsw.cg_8.model.sectors.normal;

/**
 * Dangerous sector. When a player ends his turn in this sector, he can draw a
 * dangerous sector card or attack.
 * 
 * @author Simone
 *
 */
public class DangerousSector extends NormalSector {
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
		return "DangerousSector";
	}

}
