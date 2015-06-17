package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

/**
 * Player has to draw an item, decoration
 * 
 * @author Simone
 *
 */
public class NoiseWithItem extends NoiseDecorator {
    /**
     * Constructor
     * 
     * @param decoratedNoise
     *            noise to be decorated
     */
    public NoiseWithItem(NoiseCard decoratedNoise) {
        super(decoratedNoise);
    }

    @Override
    public boolean hasToDrawItem() {
        return true;
    }

    @Override
    public String toString() {
        if (decoratedNoise.hasToMakeFakeNoise()) {
            return "Fake noise with items";
        }
        return "Real noise with items";
    }
}
