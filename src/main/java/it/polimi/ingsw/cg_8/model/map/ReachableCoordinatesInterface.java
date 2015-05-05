package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.Set;

public interface ReachableCoordinatesInterface {

	public Set<Coordinate> getReachableCoordinates (Coordinate c, Integer depth);
	
}
