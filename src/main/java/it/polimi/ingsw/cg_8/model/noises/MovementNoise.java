package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
/**
 * Noise made when player draw a noise card (fake or real)
 * @author Simone
 *
 */
public class MovementNoise extends Noise {
	/**
	 * Constructor
	 * @param turnNumber turn number
	 * @param player player that made noise
	 * @param coordinate coordinate of the player
	 */
	public MovementNoise(int turnNumber, Player player, Coordinate coordinate) {
		super(turnNumber, player, coordinate);
	}

	public String toString(){
		return "Movement noise :"+super.toString();
	}
}
