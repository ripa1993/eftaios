package it.polimi.ingsw.cg_8.model.sectors.normal;

import it.polimi.ingsw.cg_8.model.sectors.Sector;

/**
 * Abstract class for normal sector in the game. It is extended by
 * {@link DangerousSector} and {@link SecureSector}
 * 
 * @author Simone
 * @version 1.0
 */
public abstract class NormalSector extends Sector {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3925058303611953264L;

	/**
	 * Default constructor for normal sector
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	public NormalSector(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
