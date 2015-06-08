package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
/**
 * Noise made when a player attacks
 * @author Simone
 *
 */
public class AttackNoise extends Noise {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3887771292366833452L;

	/**
	 * Constructor
	 * @param turnNumber turn number
	 * @param player player that made noise
	 * @param coordinate coordinate of the player
	 */
	public AttackNoise(int turnNumber, Player player, Coordinate coordinate) {
		super(turnNumber, player, coordinate);
	}
	
	public String toString(){
		return "Attack noise: "+super.toString();
	}

}
