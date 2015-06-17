package it.polimi.ingsw.cg_8.model.map.creator;

import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.map.GameMapSet;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;

import java.util.Iterator;
import java.util.Map;

/**
 * MapCreator abstract class.
 * 
 * @author Simone
 * @version 1.0
 */
public abstract class MapCreator {

	// x is horizontal, 0 = "A"
	// y is vertical, 0 = "01"

	/**
	 * GameMap that is going to be created
	 */
	private final GameMap gameMap;
	/**
	 * Sectors of this game map
	 */
	private final Map<Coordinate, Sector> sectors;

	/**
	 * Constructor
	 * 
	 * @param gm
	 *            GameMap
	 */
	public MapCreator(GameMap gm) {
		this.gameMap = gm;
		sectors = gm.getSectors();
	}

	/**
	 * Getter for GameMap
	 * 
	 * @return GameMap
	 */

	public GameMap getGm() {
		return gameMap;
	}

	/**
	 * Constructor for GameMap. Creates a new HashMap of sector, a new MapProxy
	 * and spawn coordinate
	 */
	protected abstract GameMapSet sectorParser();

	/**
	 * Adds a generic sector s to coordinate c in the sectors map
	 * 
	 * @param c
	 *            Coordinate
	 * @param s
	 *            Sector
	 */
	protected void addSector(Coordinate c, Sector s) {
		sectors.put(c, s);
	}

	/**
	 * Collection of statements that populate the map
	 * 
	 * @return complete map
	 */
	public GameMap createMap() {
		GameMapSet sectorList = this.sectorParser();

		Iterator<Sector> iterator = sectorList.getSectorList().iterator();
		while (iterator.hasNext()) {
			Sector currentSector = iterator.next();
			int x = currentSector.getX();
			int y = currentSector.getY();
			addSector(new Coordinate(x, y), currentSector);
		}
		return gameMap;

	}

	@Override
	public String toString() {
		return "MapCreator [gm=" + gameMap + ", sectors=" + sectors + "]";
	}

}
