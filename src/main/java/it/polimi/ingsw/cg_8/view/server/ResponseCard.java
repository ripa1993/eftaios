package it.polimi.ingsw.cg_8.view.server;

import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;

import java.io.Serializable;
import java.util.List;

/**
 * Used to communicate to the client its item cards. The message is sent to the
 * player at the beginning of his turn, and when he draws or uses a card.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ResponseCard implements ServerResponse, Serializable {

	private static final long serialVersionUID = -8941807144566952210L;
	/**
	 * The item cards held by the player
	 */
	private final List<ItemCard> hand;

	public ResponseCard(List<ItemCard> hand) {
		this.hand = hand;
	}

	public List<ItemCard> getHand() {
		return hand;
	}

	@Override
	public String toString() {
		return "Server: " + hand;
	}
}
