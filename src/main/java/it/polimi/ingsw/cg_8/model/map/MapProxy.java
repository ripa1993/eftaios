package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Implmementation of the Proxy, used to get reachable coordinates from the
 * GameMap
 * 
 * @author Simone
 * @version 1.0
 */
public class MapProxy implements ReachableCoordinatesInterface {

	/**
	 * GameMap realated to this proxy
	 */
	private final GameMap map;
	/**
	 * Nested Map and Set. First key is the coordinate. Second key is the depth.
	 * The value is a set of Coordinate
	 * 
	 */
	private final Map<Coordinate, Map<Integer, Set<Coordinate>>> reachableCoordinates;

	/**
	 * HashMap < StartingCoords, HashMap <Depth, Set<ReachableCoords>>>
	 * 
	 * @param map
	 *            related map
	 */
	public MapProxy(GameMap map) {
		reachableCoordinates = new HashMap<Coordinate, Map<Integer, Set<Coordinate>>>();
		this.map = map;
	}

	/**
	 * Getter for reachable coordinates data structure
	 * 
	 * @return reachable coordinates map
	 */
	public Map<Coordinate, Map<Integer, Set<Coordinate>>> getReachableCoordinates() {
		return reachableCoordinates;
	}

	/**
	 * Calculates the connected coordinates to a specified coordinate
	 * 
	 * @param c
	 *            starting coordinate
	 * @return connected coordinates from c coordinate
	 */
	@Override
	public Set<Coordinate> getConnectedCoordinates(Coordinate c) {
		Set<Coordinate> connectedCoordinates = new HashSet<Coordinate>();
		int currentX = c.getX();
		int currentY = c.getY();
		if (map.verifySectorExistance(new Coordinate(currentX, currentY + 1))) {
			connectedCoordinates.add(new Coordinate(currentX, currentY + 1));
		}
		if (map.verifySectorExistance(new Coordinate(currentX, currentY - 1))) {
			connectedCoordinates.add(new Coordinate(currentX, currentY - 1));

		}
		if ((currentX & 1) == 1) {
			if (map.verifySectorExistance(new Coordinate(currentX + 1, currentY))) {
				connectedCoordinates
				        .add(new Coordinate(currentX + 1, currentY));

			}
			if (map.verifySectorExistance(new Coordinate(currentX + 1,
			        currentY + 1))) {
				connectedCoordinates.add(new Coordinate(currentX + 1,
				        currentY + 1));
			}
			if (map.verifySectorExistance(new Coordinate(currentX - 1, currentY))) {
				connectedCoordinates
				        .add(new Coordinate(currentX - 1, currentY));
			}
			if (map.verifySectorExistance(new Coordinate(currentX - 1,
			        currentY + 1))) {
				connectedCoordinates.add(new Coordinate(currentX - 1,
				        currentY + 1));
			}
		} else if ((currentX & 1) == 0) {
			if (map.verifySectorExistance(new Coordinate(currentX + 1, currentY))) {
				connectedCoordinates
				        .add(new Coordinate(currentX + 1, currentY));

			}
			if (map.verifySectorExistance(new Coordinate(currentX + 1,
			        currentY - 1))) {
				connectedCoordinates.add(new Coordinate(currentX + 1,
				        currentY - 1));
			}
			if (map.verifySectorExistance(new Coordinate(currentX - 1, currentY))) {
				connectedCoordinates
				        .add(new Coordinate(currentX - 1, currentY));
			}
			if (map.verifySectorExistance(new Coordinate(currentX - 1,
			        currentY - 1))) {
				connectedCoordinates.add(new Coordinate(currentX - 1,
				        currentY - 1));
			}
		}

		return connectedCoordinates;
	}

	@Override
	public Set<Coordinate> getReachableCoordinates(Coordinate c, Integer depth) {
		if (reachableCoordinates.get(c) == null) {
			calculateReachableCoordinates(c);
		}
		Set<Coordinate> toBeReturned = new HashSet<Coordinate>();
		for (int i = 1; i <= depth; i++) {
			toBeReturned.addAll(reachableCoordinates.get(c).get(i));
		}
		return toBeReturned;
	}

	/**
	 * Calculates reachable coordinates for depth=1,2,3 using breadth first
	 * visit and adds them to {@link #reachableCoordinates}
	 * 
	 * @param c
	 *            starting coordinate
	 */
	private void calculateReachableCoordinates(Coordinate c) {

		// Breadth First Search implementation for coordinates
		Map<Integer, Set<Coordinate>> thisCoordinateHashMap = new HashMap<Integer, Set<Coordinate>>();
		Set<Coordinate> firstRun = new HashSet<Coordinate>();
		Set<Coordinate> secondRun = new HashSet<Coordinate>();
		Set<Coordinate> thirdRun = new HashSet<Coordinate>();

		// calculates reachable coordinates for depth = 1
		firstRun = getConnectedCoordinates(c);
		firstRun.remove(map.getAlienSpawn());
		firstRun.remove(map.getHumanSpawn());

		// reiterates the operation for every coordinate obtained in first run
		// (aka depth = 2)
		Iterator<Coordinate> i = firstRun.iterator();
		while (i.hasNext()) {
			Coordinate currentI = i.next();
			Set<Coordinate> tempSet = getConnectedCoordinates(currentI);
			secondRun.addAll(tempSet);
		}
		secondRun.remove(map.getAlienSpawn());
		secondRun.remove(map.getHumanSpawn());
		secondRun.remove(c);

		// reiterates the operation for every coordinate obtained in second run
		// (aka depth = 3)
		Iterator<Coordinate> i2 = secondRun.iterator();
		while (i2.hasNext()) {
			Coordinate currentI = i2.next();
			Set<Coordinate> tempSet = getConnectedCoordinates(currentI);
			thirdRun.addAll(tempSet);
		}
		thirdRun.remove(map.getAlienSpawn());
		thirdRun.remove(map.getHumanSpawn());
		thirdRun.remove(c);

		// save the 3 depth in a hashmap
		thisCoordinateHashMap.put(1, new HashSet<Coordinate>(firstRun));
		thisCoordinateHashMap.put(2, new HashSet<Coordinate>(secondRun));
		thisCoordinateHashMap.put(3, new HashSet<Coordinate>(thirdRun));

		// adds the hashmap to the reachableCoordinates hashmap
		reachableCoordinates.put(c, thisCoordinateHashMap);
	}

	/**
	 * Getter for GameMap related to this MapProxy
	 * 
	 * @return game map
	 */
	public GameMap getMap() {
		return map;
	}

	@Override
	public String toString() {
		return "MapProxy [map=" + map + ", reachableCoordinates="
		        + reachableCoordinates + "]";
	}
}
