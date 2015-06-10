package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.exceptions.NotAValidCoordinateException;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Abstract class that represents a map
 * 
 * @author Simone
 * @version 1.0
 */
public abstract class GameMap implements ReachableCoordinatesInterface {
	/**
	 * Map that has a coordinate as key and a sector as value
	 */
	private final Map<Coordinate, Sector> sectors;
	/**
	 * Reference to the map proxy related to this map
	 */
	private final MapProxy mapProxy;
	/**
	 * Human starting coordinate
	 */
	private Coordinate humanSpawn;
	/**
	 * Alien starting coordinate
	 */
	private Coordinate alienSpawn;

	/**
	 * Constructor for GameMap. Creates a new HashMap of sector, a new MapProxy
	 * and spawn coordinates
	 * 
	 * @param humanSpawn
	 *            coordinate of human spawn
	 * @param alienSpawn
	 *            coordinate of alien spawn
	 */

	public GameMap(Coordinate humanSpawn, Coordinate alienSpawn) {
		sectors = new HashMap<Coordinate, Sector>();
		mapProxy = new MapProxy(this);
		this.humanSpawn = humanSpawn;
		this.alienSpawn = alienSpawn;
	}

	/**
	 * Getter for human spawn
	 * 
	 * @return coordinate of human spawn
	 */
	public Coordinate getHumanSpawn() {
		return humanSpawn;
	}

	/**
	 * Getter for alien spawn
	 * 
	 * @return coordinate of alien spawn
	 */
	public Coordinate getAlienSpawn() {
		return alienSpawn;
	}

	/**
	 * Getter for this game map's Map<Coordinate, Sector>
	 * 
	 * @return Map<Coordinate, Sector>
	 */
	public Map<Coordinate, Sector> getSectors() {
		return sectors;
	}

	/**
	 * Getter for map proxy
	 * 
	 * @return this map's proxy
	 */
	public MapProxy getMapProxy() {
		return mapProxy;
	}

	/**
	 * Using a proxy, this method calls the mapProxy in order to not recalculate
	 * reachable coordinates if they have already been calculated
	 */
	@Override
	public Set<Coordinate> getReachableCoordinates(Coordinate c, Integer depth) {
		return mapProxy.getReachableCoordinates(c, depth);
	}
	
	@Override
	public Set<Coordinate> getConnectedCoordinates(Coordinate c){
		return mapProxy.getConnectedCoordinates(c);
	}

	/**
	 * Returns the sector relative to a coordinate
	 * 
	 * @param c
	 *            coordinate of the sector
	 * @return sector relative to this coordinate
	 * @throws NotAValidCoordinateException
	 */
	public Sector getSectorByCoordinates(Coordinate c)
			throws NotAValidCoordinateException {
		if (!verifySectorExistance(c)) {
			throw new NotAValidCoordinateException(
					"This coordinate is not in the map");
		} else {
			return sectors.get(c);
		}
	}

	/**
	 * Verifies the existence of a sector in this coordinate
	 * 
	 * @param c
	 *            coordinate of the sector
	 * @return true, if exists a sector at this coordinate<br>
	 *         false, if it doesn't exists
	 */
	public boolean verifySectorExistance(Coordinate c) {
		if (sectors.get(c) == null) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {

		Set<Entry<Coordinate, Sector>> tempSet = sectors.entrySet();

		return tempSet.toString();

	}

}
