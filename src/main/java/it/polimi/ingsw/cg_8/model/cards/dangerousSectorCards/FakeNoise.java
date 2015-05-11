package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

public class FakeNoise extends NoiseDecorator {
	
	public FakeNoise(NoiseCard  decoratedNoise) {
		super(decoratedNoise);
	}
	
	@Override
	public boolean hasToMakeFakeNoise() {
		return true;
	}
}
