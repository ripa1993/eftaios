package it.polimi.ingsw.cg_8.model.sectors.special;

import it.polimi.ingsw.cg_8.model.sectors.Sector;

/**
 * Abstract special sector class, it is extended by
 * {@link it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector
 * EscapeHatchSector} and
 * {@link it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.SpawnSector
 * SpawnSector}
 * 
 * @author Simone
 * @version 1.0
 */
public abstract class SpecialSector extends Sector {
	/**
	 * Default constructor for {@link SpecialSector}
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	public SpecialSector(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "SpecialSector";
	}

}
