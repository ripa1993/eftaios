package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapProxy implements ReachableCoordinatesInterface {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
		result = prime
				* result
				+ ((reachableCoordinates == null) ? 0 : reachableCoordinates
						.hashCode());
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
		MapProxy other = (MapProxy) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (reachableCoordinates == null) {
			if (other.reachableCoordinates != null)
				return false;
		} else if (!reachableCoordinates.equals(other.reachableCoordinates))
			return false;
		return true;
	}

	private final GameMap map;
	private final Map<Coordinate, Map<Integer, Set<Coordinate>>> reachableCoordinates;

	// HashMap < StartingCoords, HashMap <Depth, Set<ReachableCoords>>>
	public MapProxy(GameMap map) {
		reachableCoordinates = new HashMap<Coordinate, Map<Integer, Set<Coordinate>>>();
		this.map = map;
	}

	// gives the reachable coordinates from a starting coordinate
	private Set<Coordinate> getConnectedCoordinates(Coordinate c) {
		Set<Coordinate> connectedCoordinates = new HashSet<Coordinate>();
		int currentX = c.getX();
		int currentY = c.getY();
		connectedCoordinates.add(new Coordinate(currentX, currentY + 1));
		connectedCoordinates.add(new Coordinate(currentX, currentY - 1));
		connectedCoordinates.add(new Coordinate(currentX + 1, currentY + 1));
		connectedCoordinates.add(new Coordinate(currentX + 1, currentY - 1));
		connectedCoordinates.add(new Coordinate(currentX - 1, currentY + 1));
		connectedCoordinates.add(new Coordinate(currentX - 1, currentY + 1));

		
		// deletes the coordinates that aren't in the map (null sectors)
		Iterator<Coordinate> i = connectedCoordinates.iterator();
		while (i.hasNext()) {
			Coordinate currentCoordinate = i.next();
			if (map.verifySectorExisistance(currentCoordinate) == false) {
				connectedCoordinates.remove(currentCoordinate);
			}
		}

		return connectedCoordinates;
	}

	// returns the reachable coordinates given the coordinate and the depth
	@Override
	public Set<Coordinate> getReachableCoordinates(Coordinate c, Integer depth) {
		if(reachableCoordinates.get(c).get(depth)==null){
			calculateReachableCoordinates(c);
		}
		return reachableCoordinates.get(c).get(depth);
	}

	// calculates reachable coordinates for depth=1,2,3
	private void calculateReachableCoordinates(Coordinate c) {
		
		// Breadth First Search implementation for coordinates
		Map<Integer, Set<Coordinate>> thisCoordinateHashMap = new HashMap<Integer, Set<Coordinate>>();
		Set<Coordinate> firstRun = new HashSet<Coordinate>();
		Set<Coordinate> secondRun = new HashSet<Coordinate>();
		Set<Coordinate> thirdRun = new HashSet<Coordinate>();
		
		// calculates reachable coordinates for depth = 1
		firstRun = getConnectedCoordinates(c);
		
		// reiterates the operation for every coordinate obtained in first run (aka depth = 2)
		Iterator<Coordinate> i = firstRun.iterator();
		while(i.hasNext()){
			secondRun.addAll(getConnectedCoordinates((Coordinate) i.next()));
		}
		secondRun.remove(c);
		
		// reiterates the operation for every coordinate obtained in second run (aka depth = 3)
		Iterator<Coordinate> i2 = secondRun.iterator();
		while(i2.hasNext()){
			thirdRun.addAll(getConnectedCoordinates((Coordinate) i.next()));
		}
		thirdRun.remove(c);
		
		// save the 3 depth in a hashmap
		thisCoordinateHashMap.put(1, new HashSet<Coordinate>(firstRun));
		thisCoordinateHashMap.put(2, new HashSet<Coordinate>(secondRun));
		thisCoordinateHashMap.put(3, new HashSet<Coordinate>(thirdRun));
		
		//adds the hashmap to the reachableCoordinates hashmap
		reachableCoordinates.put(c, thisCoordinateHashMap);
	}

	public GameMap getMap() {
		return map;
	}
}
