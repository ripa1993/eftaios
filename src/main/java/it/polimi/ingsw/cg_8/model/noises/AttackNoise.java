package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public class AttackNoise extends Noise {

	public AttackNoise(int turnNumber, Player player, Coordinate coordinate) {
		super(turnNumber, player, coordinate);
	}
	
	public String toString(){
		return "Attack noise: "+super.toString();
	}

}
