package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.Coordinate;

public class FakeNoise extends NoiseDecorator {
	public FakeNoise(NoiseCard  decoratedNoise) {
		super(decoratedNoise);
	}
	
	@Override
	public Coordinate makeNoise(Coordinate coor) {
		return coor;
	}
	
}
