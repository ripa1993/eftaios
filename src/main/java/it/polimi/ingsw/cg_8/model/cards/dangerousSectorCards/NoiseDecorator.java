package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

public abstract class NoiseDecorator extends DangerousSectorCard implements NoiseCard {
	
	protected final NoiseCard decoratedNoise;
	
	public NoiseDecorator(NoiseCard decoratedNoise) {
		this.decoratedNoise = decoratedNoise;
	}
	
	@Override
	public boolean hasToMakeFakeNoise() {
		return decoratedNoise.hasToMakeFakeNoise(); // Delegation
	}
	@Override
	public boolean hasToDrawItem() {
		return decoratedNoise.hasToDrawItem(); // Delegation
	}
}
