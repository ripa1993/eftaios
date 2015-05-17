package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
/**
 * Galilei map
 * @author Simone
 *
 */
public class GalileiMap extends GameMap {
	/**
	 * Constructor
	 * @param humanSpawn human spawn coordinate
	 * @param alienSpawn alien spawn coordinate
	 */
	public GalileiMap(Coordinate humanSpawn, Coordinate alienSpawn) {
		super(humanSpawn, alienSpawn);
	}

}
