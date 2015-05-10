package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;

public class NoiseWithItem extends NoiseDecorator {
	
	public NoiseWithItem(NoiseCard decoratedNoise) {
		super(decoratedNoise);
	}

	@Override
	public ItemCard drawItem() {
		return null;		// It should return an item card, from the item card deck, function not implemented yet
	}
	
	public String toString() {
        return super.toString() + " Draw an item card";
    }
}
