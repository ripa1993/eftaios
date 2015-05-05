package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.Coordinate;

public class NoiseWithItem extends NoiseDecorator {
	
	public NoiseWithItem(NoiseCard decoratedNoise) {
		super(decoratedNoise);
	}

	@Override
	public Coordinate makeNoise(Coordinate coor) {
		drawItem();
		return coor;
	}
}
