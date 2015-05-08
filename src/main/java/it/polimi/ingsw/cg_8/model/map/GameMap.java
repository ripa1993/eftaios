package it.polimi.ingsw.cg_8.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.cg_8.model.sectors.*;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.AlienSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.HumanSector;

public abstract class GameMap implements ReachableCoordinatesInterface {

	private final Map<Coordinate, Sector> sectors;
	private final MapProxy mapProxy;
	
	
	public GameMap() {
		sectors = new HashMap<Coordinate, Sector>();
		mapProxy = new MapProxy(this);
	}
	
	public Map<Coordinate, Sector> getSectors() {
		return sectors;
	}

	public MapProxy getMapProxy() {
		return mapProxy;
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
