package it.polimi.ingsw.cg_8.model.player;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public class Round {
	private final int number;
	private final Coordinate coordinate; // ending position in the turn #number
	
	
	public Round (int number, Coordinate coordinate){
		this.number = number;
		this.coordinate = coordinate;
	}


	public int getNumber() {
		return number;
	}


	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	
}
