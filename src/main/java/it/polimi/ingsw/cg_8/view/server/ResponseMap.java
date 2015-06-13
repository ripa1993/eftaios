package it.polimi.ingsw.cg_8.view.server;

import it.polimi.ingsw.cg_8.model.map.GameMapName;

public class ResponseMap implements ServerResponse {
	private GameMapName mapName;

	public ResponseMap(GameMapName mapName) {
		this.mapName = mapName;
		// TODO: maybe send hashmap?
	}

	public GameMapName getMapName() {
		return mapName;
	}
}
