package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashSet;
import java.util.Set;

/**
 * The input handler takes a movement input and calls the validateMovement
 * functions. Then the validateMovement function creates an instance of this
 * class and verifies the validity of the move.
 * 
 * @author Alberto Parravicini
 *
 */
public class Movement extends PlayerAction {

	private final Coordinate startingSector;
	private final Set<Coordinate> allowedCoordinates;
	private final Coordinate destination;
	private final GameMap gameMap;
	private int range;

	public Movement(Player player, Coordinate destination, GameMap gameMap) {
		startingSector = player.getLastPosition();
		allowedCoordinates = new HashSet<Coordinate>();
		this.destination = destination;
		this.gameMap = gameMap;
		this.range = player.getCharacter().getMaxAllowedMovement();
	}

	/*
	 * Add to allowedCoordinates the coordinates that can be reached by the
	 * player.
	 */
	private void setAllowedCoordinates() {
		allowedCoordinates.addAll(gameMap.getReachableCoordinates(
				startingSector, range));
	}

	private boolean isMoveAllowed() {
		if (destination != startingSector
				&& allowedCoordinates.contains(destination)) {
			return true;
		} else
			return false;
	}
	
	public boolean evaluateMove() {
		this.setAllowedCoordinates();
		return isMoveAllowed();		
	}
}
