package it.polimi.ingsw.cg_8.model.sectors.normal;

/**
 * Secure sector. When a player ends his turn in this sector he can safely
 * finish his turn with no noises.
 * 
 * @author Simone
 *
 */

public class SecureSector extends NormalSector {
	/**
	 * Secure sector constructor
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	public SecureSector(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "SecureSector "+super.toString();
	}

}
