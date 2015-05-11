package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

public class NoiseWithItem extends NoiseDecorator {
	
	public NoiseWithItem(NoiseCard decoratedNoise) {
		super(decoratedNoise);
	}

	@Override
	public boolean hasToDrawItem() {
		return true;	
	}
}
