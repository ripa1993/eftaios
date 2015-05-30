package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.DangerousSectorCard;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NoiseCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.TooManyCardsException;
import it.polimi.ingsw.cg_8.model.noises.MovementNoise;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.player.Hand;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;

/**
 * When the player reaches a {@link DangerousSector}, he can draw a
 * {@link DangerousSectorCard}.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class DrawDangerousSectorCard extends PlayerAction {
	/**
	 * The current state of the game.
	 */
	private Model model;
	/**
	 * The DangerousSectorCard drawn by the player.
	 */
	private Card dangerousSectorCard;
	/**
	 * The ItemCard drawn by the player.
	 */
	private ItemCard itemCard;

	boolean discardedItemCard = false;
	boolean emptyItemDeck = false;

	/**
	 * The class is not static so that it is possible to keep trace of the cards
	 * drawn by the player.
	 * 
	 * @param model
	 *            The current state of the game.
	 */
	public DrawDangerousSectorCard(Model model) {
		this.model = model;
		this.dangerousSectorCard = null;
		this.itemCard = null;
	}

	/**
	 * The player produces a noise, unless otherwise is specified. If the drawn
	 * card is decorated, the player has to make a fake noise, draw an item
	 * card, or both.
	 * 
	 * @throws TooManyCardsException
	 * @throws EmptyDeckException
	 * 
	 * @require currentPlayer.getPosition(currentTurn) !=
	 *          currentPlayer.getPosition(currentTurn-1) &&
	 *          currentPlayer.getPosition(currentTurn) instanceof
	 *          DangerousSector && currentPlayer hasn't attacked in this turn.
	 * @return If the player has to make a fake noise;
	 */
	public boolean drawDangerousSectorCard() {

		Player player = model.getCurrentPlayerReference();
		boolean hasToMakeFakeNoise = false;

		try {
			this.dangerousSectorCard = model.getDangerousSectorDeck()
					.drawCard();
		} catch (EmptyDeckException e) {
			/**
			 * This exception never occurs, the deck is always re-shuffled when
			 * empty.
			 */
			e.printStackTrace();
		}

		if (dangerousSectorCard instanceof NoiseCard) {

			// what kind of noise?
			if (((NoiseCard) dangerousSectorCard).hasToMakeFakeNoise() == false) {
				Noise movementNoise = new MovementNoise(model.getRoundNumber(),
						player, player.getLastPosition());
				model.getNoiseLogger().add(movementNoise);
				hasToMakeFakeNoise = false;
			} else {
				/**
				 * The player has to make a fake noise.
				 */
				hasToMakeFakeNoise = true;
			}

			// draw an object
			if (((NoiseCard) dangerousSectorCard).hasToDrawItem() == true) {

				try {
					itemCard = (ItemCard) model.getItemDeck().drawCard();
					
					/**
					 * Add the card to the player's hand, if possible.
					 */
					discardedItemCard = !(player.getHand().addItemCard(itemCard));
						
					 if (discardedItemCard == true) {
						model.getItemDeck().addUsedCard(itemCard);
					}

				} catch (EmptyDeckException e) {
					this.emptyItemDeck = true;
					this.itemCard = null;
				}

			}
		}
		return hasToMakeFakeNoise;
	}

	public Card getDangerousSectorCard() {
		return this.dangerousSectorCard;
	}

	public Card getItemCard() {
		return this.itemCard;
	}

	public boolean isDiscardedItemCard() {
		return discardedItemCard;
	}

	public boolean isEmptyItemDeck() {
		return emptyItemDeck;
	}
}
