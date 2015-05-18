package it.polimi.ingsw.cg_8.model.decks.deckCreators;

import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.FakeNoise;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NoiseWithItem;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NormalNoise;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.SilenceCard;
import it.polimi.ingsw.cg_8.model.decks.DangerousSectorDeck;

/**
 * Dangerous sector deck creator
 * 
 * @author Simone
 *
 */
public class DangerousSectorDeckCreator extends DeckCreator {
	/**
	 * Max number of silence card
	 */
	private static final int SILENCE_NUM = 5;
	/**
	 * Max number of noise with item card
	 */
	private static final int NOISE_ITEM_NUM = 4;
	/**
	 * Max number of noise with no item card
	 */
	private static final int NOISE_NO_ITEM_NUM = 6;
	/**
	 * Max number of fake noise with item card
	 */
	private static final int FAKE_NOISE_ITEM_NUM = 4;
	/**
	 * Max number of fake noise with no item card
	 */
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
