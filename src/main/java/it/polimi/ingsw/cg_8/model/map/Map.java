package it.polimi.ingsw.cg_8.model.map;

import java.util.HashMap;
import java.util.Set;

import it.polimi.ingsw.cg_8.model.sectors.*;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.AlienSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.HumanSector;

public abstract class Map implements ReachableCoordinatesInterface {

	private HashMap<Coordinate, Sector> sectors;
	private MapProxy mapProxy;

	public Map() {
		sectors = new HashMap<Coordinate, Sector>();
		mapProxy = new MapProxy(this);
	}

	// adds a generic sector s to coordinate c in the sectors map

	public void addSector(Coordinate c, Sector s) {
		sectors.put(c, s);
	}

	// addXSector adds a sector of X type to the sectors map in the c coordinate

	public void addDangerousSector(Coordinate c) {
		Sector currentSector = new DangerousSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}

	public void addSecureSector(Coordinate c) {
		Sector currentSector = new SecureSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}

	public void addEscapeHatchSector(Coordinate c) {
		Sector currentSector = new EscapeHatchSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}

	public void addHumanSector(Coordinate c) {
		Sector currentSector = new HumanSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}

	public void addAlienSector(Coordinate c) {
		Sector currentSector = new AlienSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}

	// using a proxy, calls its mapProxy in order to not recalculate reachable
	// coordinates if they have already been calculated
	@Override
	public Set<Coordinate> getReachableCoordinates(Coordinate c, Integer depth) {
		return mapProxy.getReachableCoordinates(c, depth);
	}

	// returns the sector relative to a coordinate, currently returns null if no
	// sector (?)
	public Sector getSectorByCoordinates(Coordinate c) {
		return sectors.get(c);
	}

	// returns false if the coordinate isn't associated to a sector, else true
	public boolean verifySectorExisistance(Coordinate c) {
		if (getSectorByCoordinates(c) == null) {
			return false;
		}
		return true;
	}

}
