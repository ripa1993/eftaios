package it.polimi.ingsw.cg_8.view.server;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.io.Serializable;

public class ResponseNoise implements Serializable, ServerResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -539924760536211551L;
	private final int turnNumber;
	private final Coordinate coordinate;
	private final String playerName;

	public ResponseNoise(int turnNumber, Coordinate coordinate, String playerName) {
		this.turnNumber = turnNumber;
		this.coordinate = coordinate;
		this.playerName = playerName;
	}
}
