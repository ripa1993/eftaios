package it.polimi.ingsw.cg_8.controller.playerActions;

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
	
	public Movement(Player player, Coordinate destination) {
		startingSector = player.getLastPosition(); /* Not sure if this is the right method to call */
		allowedCoordinates = new HashSet<Coordinate>();
		this.destination = destination;
	}
	
	/* Returns the coordinates that can be reached by the player. */
	public void setAllowedCoordinates() {

	}
	
	public Set<Coordinate> getAllowedCoordinates() {
		return allowedCoordinates;
	}
	
	public boolean isMoveAllowed() {
		if (destination != startingSector && allowedCoordinates.contains(destination)) {
			return true;
		}
		else return false;
	}
}
