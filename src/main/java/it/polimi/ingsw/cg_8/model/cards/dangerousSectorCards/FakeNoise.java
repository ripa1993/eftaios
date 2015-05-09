package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public class FakeNoise extends NoiseDecorator {
	
	public FakeNoise(NoiseCard  decoratedNoise) {
		super(decoratedNoise);
	}
	
	@Override
	public Coordinate makeNoise(Coordinate coor) {	// Input coordinate given by the player
		return coor;
	}
	
	@Override 
	public String getDescription() {
        return "Emitting fake noise";
    }
}
