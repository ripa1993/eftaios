package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
/**
 * Galvani map
 * @author Simone
 *
 */
public class GalvaniMap extends GameMap {
	/**
	 * Constructor
	 * @param humanSpawn human spawn coordinate
	 * @param alienSpawn alien spawn coordinate
	 */
	public GalvaniMap(Coordinate humanSpawn, Coordinate alienSpawn) {
		super(humanSpawn, alienSpawn);
	}

}
