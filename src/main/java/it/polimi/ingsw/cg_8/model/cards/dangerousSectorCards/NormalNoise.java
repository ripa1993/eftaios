package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.Coordinate;

public class NormalNoise extends NoiseCard {
	
	@Override
	public Coordinate makeNoise(Coordinate currCoor) {
		return currCoor;
	}
}
