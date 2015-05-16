package it.polimi.ingsw.cg_8.model.decks.deckCreators;

import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.FakeNoise;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NoiseWithItem;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NormalNoise;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.SilenceCard;
import it.polimi.ingsw.cg_8.model.decks.DangerousSectorDeck;

public class DangerousSectorDeckCreator extends DeckCreator {

	private static final int SILENCE_NUM = 5;
	private static final int NOISE_ITEM_NUM = 4;
	private static final int NOISE_NO_ITEM_NUM = 6;
	private static final int FAKE_NOISE_ITEM_NUM = 4;
	private static final int FAKE_NOISE_NO_ITEM_NUM = 6;
	@Override
	public DangerousSectorDeck createDeck() {

		DangerousSectorDeck dangDeck = new DangerousSectorDeck();
		
		for (int i = 0; i < SILENCE_NUM; i++) {
			dangDeck.addCard(new SilenceCard());
		}
		for (int i = 0; i < NOISE_ITEM_NUM; i++) {
			dangDeck.addCard(new NoiseWithItem(new NormalNoise()));
		}
		for (int i = 0; i < NOISE_NO_ITEM_NUM; i++) {
			dangDeck.addCard(new NormalNoise());
		}	
		for (int i = 0; i < FAKE_NOISE_ITEM_NUM; i++) {
			dangDeck.addCard(new NoiseWithItem(new FakeNoise(new NormalNoise())));
		}
		for (int i = 0; i < FAKE_NOISE_NO_ITEM_NUM; i++) {
			dangDeck.addCard(new FakeNoise(new NormalNoise()));
		}
		
		return dangDeck;
	}
	
	
	


}
