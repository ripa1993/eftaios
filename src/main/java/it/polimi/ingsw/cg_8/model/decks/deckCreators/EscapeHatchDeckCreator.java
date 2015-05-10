package it.polimi.ingsw.cg_8.model.decks.deckCreators;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.GreenEhCard;
import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.RedEhCard;
import it.polimi.ingsw.cg_8.model.decks.EscapeHatchDeck;

import java.util.ArrayList;
import java.util.List;

public class EscapeHatchDeckCreator extends DeckCreator {
	
	private final int num_card = 3;
	@Override
	public EscapeHatchDeck createDeck() {
		EscapeHatchDeck ehDeck = new EscapeHatchDeck();
		
		for(int i = 0; i < num_card; i++) {
			ehDeck.addCard(new GreenEhCard());
		}
		for(int i = 0; i < num_card; i++) {
			ehDeck.addCard(new RedEhCard());
		}
		return ehDeck;
	}

	@Override
	protected List<Card> createCardList() {
		return new ArrayList<Card>();
	}
}
