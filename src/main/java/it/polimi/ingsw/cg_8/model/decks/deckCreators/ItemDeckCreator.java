package it.polimi.ingsw.cg_8.model.decks.deckCreators;

import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.DefenseCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.decks.ItemDeck;

public class ItemDeckCreator extends DeckCreator {

	
	private static final int ATTACK_NUM = 2;
	private static final int TELEPORT_NUM = 2;
	private static final int ADRENALINE_NUM = 2;
	private static final int SEDATIVES_NUM = 3;
	private static final int LIGHTS_NUM = 2;
	
	@Override
	public ItemDeck createDeck() {
		
		ItemDeck itemDeck = new ItemDeck();
		for (int i = 0; i < ATTACK_NUM; i++) {
			itemDeck.addCard(new AttackCard());
		}
		for (int i = 0; i < TELEPORT_NUM; i++) {
			itemDeck.addCard(new TeleportCard());
		}
		for (int i = 0; i < ADRENALINE_NUM; i++) {
			itemDeck.addCard(new AdrenalineCard());
		}
		for (int i = 0; i < SEDATIVES_NUM; i++) {
			itemDeck.addCard(new SedativesCard());
		}
		for (int i = 0; i < LIGHTS_NUM; i++) {
			itemDeck.addCard(new SpotlightCard());
		}
		itemDeck.addCard(new DefenseCard()); // There is only one Defense Card in the ItemDeck
		
		return itemDeck;
	}
	
	
}
