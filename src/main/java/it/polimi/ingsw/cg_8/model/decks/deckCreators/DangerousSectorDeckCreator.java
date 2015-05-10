package it.polimi.ingsw.cg_8.model.decks.deckCreators;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.FakeNoise;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NoiseWithItem;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NormalNoise;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.SilenceCard;
import it.polimi.ingsw.cg_8.model.decks.DangerousSectorDeck;

import java.util.ArrayList;
import java.util.List;

public class DangerousSectorDeckCreator extends DeckCreator {

	private final int silenceNum = 5;
	private final int noiseItemNum = 4;
	private final int noiseNoItemNum = 6;
	private final int fakeNoiseItemNum = 4;
	private final int fakeNoiseNoItemNum = 6;
	@Override
	public DangerousSectorDeck createDeck() {

		DangerousSectorDeck dangDeck = new DangerousSectorDeck();
		
		for (int i = 0; i < silenceNum; i++) {
			dangDeck.addCard(new SilenceCard());
		}
		for (int i = 0; i < noiseItemNum; i++) {
			dangDeck.addCard(new NoiseWithItem(new NormalNoise()));
		}
		for (int i = 0; i < noiseNoItemNum; i++) {
			dangDeck.addCard(new NormalNoise());
		}	
		for (int i = 0; i < fakeNoiseItemNum; i++) {
			dangDeck.addCard(new NoiseWithItem(new FakeNoise(new NormalNoise())));
		}
		for (int i = 0; i < fakeNoiseNoItemNum; i++) {
			dangDeck.addCard(new FakeNoise(new NormalNoise()));
		}
		
		return dangDeck;
	}
	
	
	
	@Override
	protected List<Card> createCardList() {
		return new ArrayList<Card>();
	}
}
