package it.polimi.ingsw.cg_8.model.sectors.special.spawn;

/**
 * Human spawn sector class, it's the sector where humans start on the map. Only
 * one per map.
 * 
 * @author Simone
 *
 */
public class HumanSector extends SpawnSector {

	/**
	 * Constructor for {@link HumanSector}
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	public HumanSector(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "HumanSector";
	}

}
