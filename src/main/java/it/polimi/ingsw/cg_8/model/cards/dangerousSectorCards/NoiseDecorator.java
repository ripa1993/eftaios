package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.Coordinate;

public abstract class NoiseDecorator extends NoiseCard {
	private final NoiseCard decoratedNoise;
	
	public NoiseDecorator(NoiseCard decoratedNoise) {
		this.decoratedNoise = decoratedNoise;
	}
	
	@Override
	public Coordinate makeNoise(Coordinate coor) {
		return decoratedNoise.makeNoise(coor);
	}
}
