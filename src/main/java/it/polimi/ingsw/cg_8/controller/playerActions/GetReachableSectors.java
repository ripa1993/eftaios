package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.Set;

/**
 * Return the coordinates that can be reached by the player.
 * 
 * @author Alberto Parravicini
 *
 */
public class GetReachableSectors extends PlayerAction {

	/**
	 * @param model
	 *            The current state of the game
	 * @param player
	 *            The player who performs the action
	 * @return The coordinates that can be reached by the player.
	 */
	public static Set<Coordinate> getReachableSectors(Model model, Player player) {

		int maxDistance = player.getCharacter().getMaxAllowedMovement();

		return model.getMap().getReachableCoordinates(player.getLastPosition(),
				maxDistance);
	}
}
