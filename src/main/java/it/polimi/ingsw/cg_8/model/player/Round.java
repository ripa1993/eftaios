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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + number;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Round other = (Round) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (number != other.number)
			return false;
		return true;
	}
	
	
}
