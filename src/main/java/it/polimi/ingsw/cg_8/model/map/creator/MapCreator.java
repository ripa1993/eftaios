package it.polimi.ingsw.cg_8.model.map.creator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.SectorType;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.AlienSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.HumanSector;

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
	private final GameMap gm;
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
		this.gm = gm;
		sectors = gm.getSectors();
	}

	/**
	 * Getter for GameMap
	 * 
	 * @return GameMap
	 */

	public GameMap getGm() {
		return gm;
	}

	/**
	 * Adds a generic sector s to coordinate c in the sectors map
	 * 
	 * @param c
	 *            Coordinate
	 * @param s
	 *            Sector
	 */

	private void addSector(Coordinate c, Sector s) {
		sectors.put(c, s);
	}

	/**
	 * Adds a dangerous sector to the sectors map in the c coordinate
	 * 
	 * @param c
	 *            coordinate
	 */

	protected void addDangerousSector(Coordinate c) {
		Sector currentSector = new DangerousSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}

	/**
	 * Adds a dangerous sector to the sectors map in the (x,y) coordinate
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */

	protected void addDangerousSector(int x, int y) {
		addDangerousSector(new Coordinate(x, y));
	}

	/**
	 * Adds a secure sector to the sectors map in the c coordinate
	 * 
	 * @param c
	 *            coordinate
	 */

	protected void addSecureSector(Coordinate c) {
		Sector currentSector = new SecureSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}

	/**
	 * Adds a secure sector to the sectors map in the (x,y) coordinate
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */

	protected void addSecureSector(int x, int y) {
		addSecureSector(new Coordinate(x, y));
	}

	/**
	 * Adds a dangerous escape hatch with its number to the sectors map in the c
	 * coordinate
	 * 
	 * @param c
	 *            coordinate
	 * @param number
	 *            number of this escape hatch
	 */

	protected void addEscapeHatchSector(Coordinate c, int number) {
		Sector currentSector = new EscapeHatchSector(c.getX(), c.getY(), number);
		this.addSector(c, currentSector);
	}

	/**
	 * Adds a escape hatch sector with its numberto the sectors map in the (x,y)
	 * coordinate
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 * @param number
	 *            number of this escape hatch
	 * 
	 */

	protected void addEscapeHatchSector(int x, int y, int num) {
		addEscapeHatchSector(new Coordinate(x, y), num);
	}

	/**
	 * Adds a human spawn sector to the sectors map in the c coordinate
	 * 
	 * @param c
	 *            coordinate
	 */

	protected void addHumanSector(Coordinate c) {
		Sector currentSector = new HumanSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}

	/**
	 * Adds a human spawn sector to the sectors map in the (x,y) coordinate
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */
	protected void addHumanSector(int x, int y) {
		addHumanSector(new Coordinate(x, y));
	}

	/**
	 * Adds a alien spawn sector to the sectors map in the c coordinate
	 * 
	 * @param c
	 *            coordinate
	 */

	protected void addAlienSector(Coordinate c) {
		Sector currentSector = new AlienSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}

	/**
	 * Adds a alien spawn sector to the sectors map in the (x,y) coordinate
	 * 
	 * @param x
	 *            column number
	 * @param y
	 *            row number
	 */

	protected void addAlienSector(int x, int y) {
		addAlienSector(new Coordinate(x, y));
	}

	/**
	 * Adds a column of sectors. Can be used only with secure and dangerous
	 * sectors, otherwise it will do nothing
	 * 
	 * @param st
	 *            sector type
	 * @param column
	 *            column number
	 * @param startingRow
	 *            starting row number
	 * @param endingRow
	 *            ending row number
	 */
	protected void addColumn(SectorType st, int column, int startingRow,
			int endingRow) {
		Set<Coordinate> currentSet = new HashSet<Coordinate>();
		// add a column of coordinates to currentSet
		for (int i = startingRow; i <= endingRow; i++) {
			currentSet.add(new Coordinate(column, i));
		}
		Iterator<Coordinate> it = currentSet.iterator();
		sectorsIteration(it, st);
	}

	/**
	 * Adds sectors according to the elements in the Iterator
	 * 
	 * @param it
	 *            iterator of coordinates
	 * @param st
	 *            sector type
	 */
	private void sectorsIteration(Iterator<Coordinate> it, SectorType st) {
		if (st == SectorType.DANGEROUS_SECTOR) {
			while (it.hasNext()) {
				addDangerousSector(it.next());
			}
		} else if (st == SectorType.SECURE_SECTOR) {

			while (it.hasNext()) {
				addSecureSector(it.next());
			}

		} else
			return;

	}

	/**
	 * Adds a row of sectors. Can be used only with secure and dangerous
	 * sectors, otherwise it will do nothing
	 * 
	 * @param st
	 *            sector type
	 * @param row
	 *            row number
	 * @param startingColumn
	 *            starting column number
	 * @param endingColumn
	 *            ending column number
	 */
	protected void addRow(SectorType st, int row, int startingColumn,
			int endingColumn) {
		Set<Coordinate> currentSet = new HashSet<Coordinate>();
		// add a column of coordinates to currentSet
		for (int i = startingColumn; i <= endingColumn; i++) {
			currentSet.add(new Coordinate(i, row));
		}
		Iterator<Coordinate> it = currentSet.iterator();
		sectorsIteration(it, st);
	}

	/**
	 * Collection of statements that populate the map
	 * 
	 * @return complete map
	 */
	public abstract GameMap createMap();

	@Override
	public String toString() {
		return "MapCreator [gm=" + gm + ", sectors=" + sectors + "]";
	}

}
