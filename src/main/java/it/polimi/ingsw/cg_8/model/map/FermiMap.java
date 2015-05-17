package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
/**
 * Fermi map
 * @author Simone
 *
 */
public class FermiMap extends GameMap {
	/**
	 * Constructor
	 * @param humanSpawn human spawn coordinate
	 * @param alienSpawn alien spawn coordinate
	 */
	public FermiMap(Coordinate humanSpawn, Coordinate alienSpawn) {
		super(humanSpawn, alienSpawn);
	}

}
