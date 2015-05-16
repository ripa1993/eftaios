package it.polimi.ingsw.cg_8.model.decks.deckCreators;

import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.GreenEhCard;
import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.RedEhCard;
import it.polimi.ingsw.cg_8.model.decks.EscapeHatchDeck;

public class EscapeHatchDeckCreator extends DeckCreator {
	
	private static final int NUM_CARD_PER_TYPE = 3;
	@Override
	public EscapeHatchDeck createDeck() {
		EscapeHatchDeck ehDeck = new EscapeHatchDeck();
		
		for(int i = 0; i < NUM_CARD_PER_TYPE; i++) {
			ehDeck.addCard(new GreenEhCard());
		}
		for(int i = 0; i < NUM_CARD_PER_TYPE; i++) {
			ehDeck.addCard(new RedEhCard());
		}
		return ehDeck;
	}

	
}
