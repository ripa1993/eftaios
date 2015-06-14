package it.polimi.ingsw.cg_8.view.server;

import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.io.Serializable;

public class ResponseMap implements ServerResponse, Serializable {

	private static final long serialVersionUID = 4248736548266721310L;
	/**
	 * The map that will be used in the game.
	 */
	private GameMapName mapName;

	public ResponseMap(GameMapName mapName) {
		this.mapName = mapName;

	}

	public GameMapName getMapName() {
		return mapName;
	}
}
