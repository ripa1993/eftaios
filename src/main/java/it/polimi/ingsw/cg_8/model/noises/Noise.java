package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public abstract class Noise {
	private final int turnNumber;
	private final Coordinate coordinate;
	private final Player player;
	
	public Noise(int turnNumber, Player player, Coordinate coordinate){
		this.turnNumber=turnNumber;
		this.coordinate=coordinate;
		this.player=player;
	}
	
	public String toString(){
		return " Turn: "+turnNumber+" Player: "+player.getName()+" Coordinate: "+coordinate.toString();
	}
}
