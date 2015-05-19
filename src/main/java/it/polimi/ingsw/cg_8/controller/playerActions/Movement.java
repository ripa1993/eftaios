package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@link Controller#processInput()} takes a movement input and calls the
 * {@link Rules#checkValidMovement()} functions. Then the validateMovement
 * function creates an instance of this class and verifies the validity of the
 * move.
 * 
 * @author Alberto Parravicini
 *
 */
public class Movement extends PlayerAction {
	/**
	 * The player who is moving
	 */
	private final Player player;
	/**
	 * The current location of the player
	 */
	private final Coordinate startingSector;
	/**
	 * The coordinates where the player is allowed to move
	 */
	private final Set<Coordinate> allowedCoordinates;
	/**
	 * Where the player would like to move
	 */
	private final Coordinate destination;
	/**
	 * Loading the map is required to calculate the reachable coordinates
	 */
	private final GameMap gameMap;
	/**
	 * How far the player can move
	 */
	private int range;

	/**
	 * 
	 * @param player
	 * @param destination
	 * @param gameMap
	 * @return Instance of the Movement class
	 */
	public Movement(Player player, Coordinate destination, GameMap gameMap) {
		this.player = player;
		startingSector = player.getLastPosition();
		allowedCoordinates = new HashSet<Coordinate>();
		this.destination = destination;
		this.gameMap = gameMap;
		this.range = player.getCharacter().getMaxAllowedMovement();
	}

	/**
	 * Add to allowedCoordinates the coordinates that can be reached by the
	 * player.
	 */
	private void setAllowedCoordinates() {
		allowedCoordinates.addAll(gameMap.getReachableCoordinates(
				startingSector, range));
	}

	/**
	 * Checks if the destination is reachable by the player
	 * @return Whether the destination is reachable
	 */
	private boolean isMoveAllowed() {
		if (destination != startingSector
				&& allowedCoordinates.contains(destination)) {
			return true;
		} else
			return false;
	}
	
	/** 
	 * @return Whether the move is allowed or not
	 */
	public boolean evaluateMove() {
		this.setAllowedCoordinates();
		return isMoveAllowed();
	}
	
	/**
	 * Changes the position of the player
	 */
	public void makeMove() {
		player.setPosition(destination);	
	}
}
