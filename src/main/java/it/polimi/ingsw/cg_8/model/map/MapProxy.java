package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashMap;
import java.util.Set;

public class MapProxy implements ReachableCoordinatesInterface {

	HashMap<Coordinate, HashMap<Integer, Coordinate>> reachableCoordinates;
	// HashMap < StartingCoords, HashMap <Depth, ReachableCoords>>
	public MapProxy() {
		reachableCoordinates = new HashMap<Coordinate, HashMap<Integer, Coordinate>>();
	}

	@Override
	public Set<Coordinate> getReachableCoordinates(Coordinate c, Integer depth) {
		// TODO Breadth First Search
		return null;
	}

}
