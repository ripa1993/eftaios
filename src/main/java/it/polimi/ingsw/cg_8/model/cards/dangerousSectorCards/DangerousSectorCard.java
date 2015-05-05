package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

import it.polimi.ingsw.cg_8.model.Coordinate;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;

public abstract class DangerousSectorCard extends Card {
	
	public abstract Coordinate makeNoise(Coordinate currCoord);
	public abstract ItemCard drawItem();
}
