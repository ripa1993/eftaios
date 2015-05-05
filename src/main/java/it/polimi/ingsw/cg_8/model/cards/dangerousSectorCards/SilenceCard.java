package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.Coordinate;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;

public class SilenceCard extends DangerousSectorCard {
	@Override
	public Coordinate makeNoise(Coordinate currCoor) {
		return null;
	}
	@Override
	public ItemCard drawItem() {
		return null;
	}
}
