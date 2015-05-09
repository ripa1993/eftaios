package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;

public abstract class NoiseDecorator implements NoiseCard {
	
	protected final NoiseCard decoratedNoise;
	
	public NoiseDecorator(NoiseCard decoratedNoise) {
		this.decoratedNoise = decoratedNoise;
	}
	
	@Override
	public Coordinate makeNoise(Coordinate c) {
		return decoratedNoise.makeNoise(c); // Delegation
	}
	@Override
	public ItemCard drawItem() {
		return decoratedNoise.drawItem(); // Delegation
	}
	
	public String getDescription() {
        return decoratedNoise.getDescription(); //Delegation
    }
}
