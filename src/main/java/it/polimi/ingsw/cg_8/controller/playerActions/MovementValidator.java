package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashSet;
import java.util.Set;

public class MovementValidator {
	
	/**
	 * 
	 * @param model
	 * @param destination
	 * @return Whether the move is valid or not
	 */
	public static boolean validateMove(Model model, Coordinate destination) {
		GameMap gameMap = model.getMap();
		Player player = model.getCurrentPlayerReference();
		Coordinate startingSector = player.getLastPosition();
		int maxDistance = player.getCharacter().getMaxAllowedMovement();
		
		Set<Coordinate> allowedCoordinates = setAllowedCoordinates(gameMap, startingSector, maxDistance);
		
		if (destination != startingSector
				&& allowedCoordinates.contains(destination)) {
			return true;
		} else
			return false;
	}
	
	
	/**
	 * Add to allowedCoordinates the coordinates that can be reached by the
	 * player.
	 */
	private static Set<Coordinate> setAllowedCoordinates(GameMap gameMap, Coordinate startingSector, int maxDistance) {
		Set<Coordinate> allowedCoordinates = new HashSet<Coordinate>();
		allowedCoordinates.addAll(gameMap.getReachableCoordinates(
				startingSector, maxDistance));
		return allowedCoordinates;
	}
}
