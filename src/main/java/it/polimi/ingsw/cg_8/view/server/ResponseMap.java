package it.polimi.ingsw.cg_8.view.server;

import java.io.Serializable;

import it.polimi.ingsw.cg_8.model.map.GameMapName;

public class ResponseMap implements ServerResponse, Serializable {
	private GameMapName mapName;

	public ResponseMap(GameMapName mapName) {
		this.mapName = mapName;
		// TODO: maybe send hashmap?
	}

	public GameMapName getMapName() {
		return mapName;
	}
}
