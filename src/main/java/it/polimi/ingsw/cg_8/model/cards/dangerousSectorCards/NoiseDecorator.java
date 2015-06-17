package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

/**
 * Implementation of decorator pattern, to create a real/fake noise and
 * with/withoud item
 * 
 * @author Simone
 *
 */
public abstract class NoiseDecorator extends DangerousSectorCard implements
        NoiseCard {
	/**
	 * Noise that is going to be decorated
	 */
	protected final NoiseCard decoratedNoise;

	/**
	 * Constructor
	 * 
	 * @param decoratedNoise
	 *            noise to be decorated
	 */
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
