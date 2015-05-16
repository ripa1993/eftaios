package it.polimi.ingsw.cg_8.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.cg_8.model.sectors.*;

public abstract class GameMap implements ReachableCoordinatesInterface {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mapProxy == null) ? 0 : mapProxy.hashCode());
		result = prime * result + ((sectors == null) ? 0 : sectors.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameMap other = (GameMap) obj;
		if (mapProxy == null) {
			if (other.mapProxy != null)
				return false;
		} else if (!mapProxy.equals(other.mapProxy))
			return false;
		if (sectors == null) {
			if (other.sectors != null)
				return false;
		} else if (!sectors.equals(other.sectors))
			return false;
		return true;
	}

	private final Map<Coordinate, Sector> sectors;
	private final MapProxy mapProxy;
	private Coordinate humanSpawn;
	private Coordinate alienSpawn;
	
	
	public GameMap() {
		sectors = new HashMap<Coordinate, Sector>();
		mapProxy = new MapProxy(this);
		humanSpawn = new Coordinate();
		alienSpawn = new Coordinate();
	}
	
	public Coordinate getHumanSpawn() {
		return humanSpawn;
	}

	public Coordinate getAlienSpawn() {
		return alienSpawn;
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
	public boolean verifySectorExistance(Coordinate c) {
		if (getSectorByCoordinates(c) == null) {
			return false;
		}
		return true;
	}

	public void setHumanSpawn(Coordinate humanSpawn) {
		this.humanSpawn = humanSpawn;
	}

	public void setAlienSpawn(Coordinate alienSpawn) {
		this.alienSpawn = alienSpawn;
	}
	
	

}
