package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Deck {

	

	private List<Card> cardList;
	private List<Card> usedCards;

	public Deck() {
		this.cardList = new ArrayList<Card>();
		this.usedCards = new ArrayList<Card>();
	}

	
	public void shuffle() {
		Collections.shuffle(this.cardList);
	}
	
	
	//TODO: RESHUFFLE
	
	
	// Add/Remove cards from the lists
	public void addCard(Card card) {
		this.cardList.add(card);
	}
	
	public void addUsedCard(Card card) {
		this.usedCards.add(card);
	}
	
	public Card drawCard() {
		if (isDeckEmpty() == false) {
			return cardList.remove(0);
		} else
			return null; // TODO: how to manage empty decks (reshuffle, see above)
	}

	protected boolean isDeckEmpty() {
		if (cardList.isEmpty()) {
			return true;
		} else
			return false;
	}

	public List<Card> getCards() {
		return this.cardList;
	}
	public List<Card> getUsedCards() {
		return this.usedCards;
	}

	@Override
	public String toString() {
		return "Cards: \n" + cardList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cardList == null) ? 0 : cardList.hashCode());
		result = prime * result
				+ ((usedCards == null) ? 0 : usedCards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deck other = (Deck) obj;
		if (cardList == null) {
			if (other.cardList != null)
				return false;
		} else if (!cardList.equals(other.cardList)) {
			return false; }
		if (usedCards == null) {
			if (other.usedCards != null)
				return false;
		} else if (!usedCards.equals(other.usedCards))
			return false;
		return true;
	}

}
