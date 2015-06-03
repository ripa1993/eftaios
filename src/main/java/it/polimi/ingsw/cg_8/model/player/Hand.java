package it.polimi.ingsw.cg_8.model.player;

import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.exceptions.TooManyCardsException;

import java.util.ArrayList;
import java.util.List;

/**
 * An hand is composed by the item cards owned by a player
 * 
 * @author Simone
 *
 */
public class Hand {
	/**
	 * List of item cards owned by the player
	 */
	private final List<ItemCard> heldCards;
	/**
	 * Max number of item cards owned by a player
	 */
	private static final int MAX_CARDS = 3;

	/**
	 * Default constructor for Hand
	 */
	public Hand() {
		heldCards = new ArrayList<ItemCard>();
	}

	/**
	 * Getter for list of cards in hand
	 * 
	 * @return list of item cards in hand
	 */
	public List<ItemCard> getHeldCards() {
		return heldCards;
	}

	/**
	 * Returns the card according to the index and removes it from the heldCards
	 * 
	 * @param item
	 *            index of the item card to be returned
	 * @return item card requested
	 * @throws IllegalArgumentException
	 */
	public ItemCard getCard(int item) throws IllegalArgumentException {
		if (item < 0 || item > heldCards.size()) {
			throw new IllegalArgumentException();
		}
		return heldCards.remove(item);
	}

	/**
	 * Adds the card to the heldCards if the player can have more
	 * 
	 * @param item
	 *            card to be added to hand
	 * @throws TooManyCardsException
	 * @return If the card has been added to the player's hand.
	 */
	public boolean addItemCard(ItemCard item) {
		if (this.heldCards.size() < Hand.MAX_CARDS) {
			heldCards.add(item);
			return true;
		}
		return false;
	}

	public static int getMaxCards() {
		return MAX_CARDS;
	}

	@Override
	public String toString() {
		return "Hand [heldCards=" + heldCards + "]";
	}

}
