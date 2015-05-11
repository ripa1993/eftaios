package it.polimi.ingsw.cg_8.model.decks.deckCreators;

import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.DefenseCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.decks.ItemDeck;

import java.util.ArrayList;
import java.util.List;

public class ItemDeckCreator extends DeckCreator {

	
	private static final int attCardNum = 2;
	private static final int telCardNum = 2;
	private static final int adrCardNum = 2;
	private static final int sedCardNum = 3;
	private static final int lightCardNum = 2;
	
	@Override
	public ItemDeck createDeck() {
		
		ItemDeck itemDeck = new ItemDeck();
		for (int i = 0; i < attCardNum; i++) {
			itemDeck.addCard(new AttackCard());
		}
		for (int i = 0; i < telCardNum; i++) {
			itemDeck.addCard(new TeleportCard());
		}
		for (int i = 0; i < adrCardNum; i++) {
			itemDeck.addCard(new AdrenalineCard());
		}
		for (int i = 0; i < sedCardNum; i++) {
			itemDeck.addCard(new SedativesCard());
		}
		for (int i = 0; i < lightCardNum; i++) {
			itemDeck.addCard(new SpotlightCard());
		}
		itemDeck.addCard(new DefenseCard()); // There is only one Defense Card in the ItemDeck
		
		return itemDeck;
	}
	
	@Override
	protected List<Card> createCardList() {
		return new ArrayList<Card>();
	}
}
