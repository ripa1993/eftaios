package it.polimi.ingsw.cg_8.model.sectors.special.spawn;

/**
 * Alien spawn sector class, it's the sector where aliens start on the map. Only
 * one per map.
 * 
 * @author Simone
 * @version 1.0
 */

public class AlienSector extends SpawnSector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8065999857578160970L;

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
