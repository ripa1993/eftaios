package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;


import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public interface NoiseCard {
	
	public Coordinate makeNoise(Coordinate c);	
	
	public ItemCard drawItem();
	
	public String getDescription();
}
