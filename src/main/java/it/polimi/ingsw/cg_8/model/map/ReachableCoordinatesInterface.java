package it.polimi.ingsw.cg_8.model.map;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.Set;

/**
 * Implementation of proxy pattern. Needed to not recalculate already calculated
 * reachable coordinates
 * 
 * @author Simone
 * @version 1.0
 */
public interface ReachableCoordinatesInterface {
    /**
     * Gives the reachable coordinates
     * 
     * @param c
     *            starting coordinates
     * @param depth
     *            max distance that can be reached [1..3]
     * @return set of reachable coordinates, excluding the starting one
     */
    public Set<Coordinate> getReachableCoordinates(Coordinate c, Integer depth);

    /**
     * Gives the adiacent coordinates
     * 
     * @param c
     *            center coordinate
     * @return the adiacent coordinates
     */
    public Set<Coordinate> getConnectedCoordinates(Coordinate c);

}
