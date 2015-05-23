package it.polimi.ingsw.cg_8.view.client.actions;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.io.Serializable;

/**
 * Action: after having drawn a fake noise card, do a fake noise
 * 
 * @author Simone
 *
 */
public class ActionFakeNoise implements ClientAction, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -906260420042139525L;
	/**
	 * Fake noise coordinate
	 */
	private final Coordinate coordinate;

	/**
	 * Constructor
	 * 
	 * @param coordinate
	 *            fake noise coordinate
	 */
	public ActionFakeNoise(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Getter
	 * 
	 * @return fake noise coordinate
	 */
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
}
