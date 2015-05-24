package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
/**
 * Noise made when a player is targeted by a spotlight card
 * @author Simone
 *
 */
public class SpotlightNoise extends Noise {
	/**
	 * Constructor
	 * @param turnNumber turn number
	 * @param player player that made noise
	 * @param coordinate coordinate of the player
	 */
	public SpotlightNoise(int turnNumber, Player player, Coordinate coordinate) {
		super(turnNumber, player, coordinate);
	}
	
	public String toString(){
		return "Spotlight noise: "+super.toString();
	}

}
