package it.polimi.ingsw.cg_8.model.player;

import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private final List<ItemCard> heldCards;
	private static final int MAX_CARDS = 3;
	
	public Hand(){
		heldCards =  new ArrayList<ItemCard>();
	}

	public List<ItemCard> getHeldCards() {
		return heldCards;
	}

	
	// gives the card and removes it from the heldCards
	
	
	public ItemCard getCard(int item) {
		try {
			return heldCards.remove(item);
		} catch (Exception e) {
			// TODO: handle nullPointerException?
			// no such card
			return null;
		}
	}
	
	// adds the card to the heldCards
	public void addItemCard(ItemCard item){
		if(heldCards.size()<MAX_CARDS){
			heldCards.add(item);
		}
		else {
			// TODO: handle TooManyCardsException
		}
		
	}
	


	
	

}
