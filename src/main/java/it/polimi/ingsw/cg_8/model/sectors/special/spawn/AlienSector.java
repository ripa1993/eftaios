package it.polimi.ingsw.cg_8.model.sectors.special.spawn;

/**
 * Alien spawn sector class, it's the sector where aliens start on the map. Only
 * one per map.
 * 
 * @author Simone
 *
 */

public class AlienSector extends SpawnSector {

	/**
	 * Constructor for {@link AlienSector}
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	public AlienSector(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "AS";
	}

}
