package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;

public class NormalNoise implements NoiseCard {

	@Override
	public Coordinate makeNoise(Coordinate c) {	// The input coordinate is the player's current position
		return c;
	}

	@Override
	public ItemCard drawItem() {
		return null;
	}
	@Override
	public String getDescription() {
		return "Noise in the player's position";
	}
}
