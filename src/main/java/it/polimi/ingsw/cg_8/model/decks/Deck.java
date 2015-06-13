package it.polimi.ingsw.cg_8.model.decks;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract deck, extended by {@link CharacterDeck}, {@link DangerousSectorDeck}
 * , {@link EscapeHatchDeck} and {@link ItemDeck}
 * 
 * @author Simone
 * @version 1.0
 */
public abstract class Deck {
	/**
	 * List of cards inside the deck
	 */
	private List<Card> cardList;
	/**
	 * List of cards inside the discard pile
	 */
	private List<Card> usedCards;

	/**
	 * Constructor, initializes the two lists
	 */
	public Deck() {
		this.cardList = new ArrayList<Card>();
		this.usedCards = new ArrayList<Card>();
	}

	/**
	 * Shuffles cardList
	 */
	public void shuffle() {
		Collections.shuffle(this.cardList);
	}

	/*
	 * Puts every used card into the main Deck. Used by "Item" and
	 * "Dangerous Sector" Decks It should be able to handle the exception of
	 * having an empty Used Card List, and return it to a higher level
	 */
	/**
	 * If the deck is empty and the discard pile contains at least one card,
	 * reshuffles the discard pile into the deck
	 * 
	 * @throws EmptyDeckException
	 * 
	 * 
	 */
	public void reshuffle() throws EmptyDeckException {

		if (this.isDeckEmpty() == true && this.isUsedCardsEmpty() == false) {
			for (Card i : this.usedCards) {
				this.addCard(i);
			}
			this.emptyUsedCardList();
		}

		if (this.isDeckEmpty() == true && this.isUsedCardsEmpty() == true) {
			throw new EmptyDeckException("Deck is empty");
		}

	}

	/**
	 * Clears the discard pile
	 * 
	 */
	private void emptyUsedCardList() {
		this.usedCards.clear();
	}

	/**
	 * Adds the card to the deck
	 * 
	 * @param card
	 */
	public void addCard(Card card) {
		this.cardList.add(card);
	}

	/**
	 * Adds the card to the discard pile
	 * 
	 * @param card
	 */
	public void addUsedCard(Card card) {
		this.usedCards.add(card);
	}

	/**
	 * Draw a card from the deck. Overridden by {@link ItemDeck} and
	 * {@link DangerousSectorDeck}
	 * 
	 * @return a card from the deck
	 * @throws EmptyDeckException
	 */
	public Card drawCard() throws EmptyDeckException {
		if (isDeckEmpty() == false) {
			return cardList.remove(0);
		} else
			throw new EmptyDeckException("The deck is empty");
	}

	/**
	 * Checks if the deck is empty
	 * 
	 * @return true, if the deck is empty<br>
	 *         false, if the deck is not empty
	 */
	protected boolean isDeckEmpty() {
		return this.cardList.isEmpty();
	}

	/**
	 * 
	 * @return true, if the discard pile is empty<br>
	 *         false, if it is not
	 */
	protected boolean isUsedCardsDeckEmpty() {
		return this.usedCards.isEmpty();
	}

	/**
	 * Checks if the discard pile is empty
	 * 
	 * @return true, if the discard pile is empty<br>
	 *         false, if the discard pile is not empty
	 */
	protected boolean isUsedCardsEmpty() {
		return this.usedCards.isEmpty();
	}

	/**
	 * Getter for deck's list of card
	 * 
	 * @return list of card
	 */
	public List<Card> getCards() {
		return this.cardList;
	}

	/**
	 * Getter for deck's discard pile
	 * 
	 * @return discard pile list of card
	 */
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
			return false;
		}
		if (usedCards == null) {
			if (other.usedCards != null)
				return false;
		} else if (!usedCards.equals(other.usedCards))
			return false;
		return true;
	}

}
