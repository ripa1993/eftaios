package it.polimi.ingsw.cg_8.model.sectors.special.spawn;

import it.polimi.ingsw.cg_8.model.sectors.special.SpecialSector;

/**
 * Spawn sector abstract class
 * 
 * @author Simone
 * @version 1.0
 */
public abstract class SpawnSector extends SpecialSector {

	/**
	 * Default constructor for {@link SpecialSector}
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	public SpawnSector(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
