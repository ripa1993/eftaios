package it.polimi.ingsw.cg_8.view.server;

import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;

import java.io.Serializable;
import java.util.ArrayList;
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
	private static final int CARD_NUM = 3;
	/**
	 * The item cards held by the player
	 */
	private final String card1;
	private final String card2;
	private final String card3;
	
	public ResponseCard(List<ItemCard> hand) {
		String[] cardArray = this.convertToString(hand);
		card1 = cardArray[0];
		card2 = cardArray[1];
		card3 = cardArray[2];
	}
	
	public String getHand() {
		return card1 + " " + card2 + " " + card3;
	}
	

	public String getCard1() {
		return card1;
	}


	public String getCard2() {
		return card2;
	}


	public String getCard3() {
		return card3;
	}

	@Override
	public String toString() {
		return "Server: " + card1 + " " + card2 + " " + card3;
	}
	
	/**
	 * Convert the player's hand in an array of strings.
	 * @param hand
	 */
	private String[] convertToString(List<ItemCard> hand) {
		String cardArray[] = new String[CARD_NUM];
		
 		for (int i = 0; i < hand.size(); i++) {		
 			cardArray[i] = hand.get(i).toString();
 		}
			
		for (int j = 0; j < CARD_NUM; j++) {
			if (cardArray[j] == null) {
				cardArray[j] = "No Card";
			}
		}
		return cardArray;
	}
}
