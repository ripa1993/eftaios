package it.polimi.ingsw.cg_8.view.server;

import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.io.Serializable;

/**
 * Server response used to communicate the map at the beginning of the game
 * 
 * @author Simone
 * @version 1.0
 */
public class ResponseMap implements ServerResponse, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4248736548266721310L;
	/**
	 * The map that will be used in the game.
	 */
	private GameMapName mapName;

	/**
	 * Constructor
	 * 
	 * @param mapName
	 *            map name as a value of the enum GameMapName
	 */
	public ResponseMap(GameMapName mapName) {
		this.mapName = mapName;

	}

	/**
	 * 
	 * @return map name as a value of the enum GameMapName
	 */
	public GameMapName getMapName() {
		return mapName;
	}
}
