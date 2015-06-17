package it.polimi.ingsw.cg_8.view.client.actions;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.io.Serializable;

/**
 * Action: move player to destination coordinate
 * 
 * @author Simone
 *
 */
public class ActionMove implements Serializable, ClientAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2224360105510637030L;
    /**
     * Destination coordinate
     */
    private final Coordinate coordinate;

    /**
     * Constructor
     * 
     * @param coordinate
     *            destination
     */
    public ActionMove(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Getter
     * 
     * @return destination coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

}
