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
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * When the player reaches a {@link DangerousSector}, he can draw a
 * {@link DangerousSectorCard}.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class DrawDangerousSectorCard implements PlayerAction {
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
	/**
	 * Flag that signals if the item card drawn has been discarded
	 */
	boolean discardedItemCard = false;
	/**
	 * Flag that signals if the item card deck is empty
	 */
	boolean emptyItemDeck = false;
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager
	        .getLogger(DrawDangerousSectorCard.class);

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
			LOGGER.error(e.getMessage(), e);
		}

		if (dangerousSectorCard instanceof NoiseCard) {

			// what kind of noise?
			if (!((NoiseCard) dangerousSectorCard).hasToMakeFakeNoise()) {
				Noise movementNoise = new MovementNoise(model.getRoundNumber(),
				        player, player.getLastPosition());
				model.addNoise(movementNoise);
				hasToMakeFakeNoise = false;
			} else {
				/**
				 * The player has to make a fake noise.
				 */
				hasToMakeFakeNoise = true;
			}

			// draw an object
			if (((NoiseCard) dangerousSectorCard).hasToDrawItem()) {

				try {
					itemCard = (ItemCard) model.getItemDeck().drawCard();

					/**
					 * Add the card to the player's hand, if possible.
					 */
					discardedItemCard = !(player.getHand()
					        .addItemCard(itemCard));

					if (discardedItemCard) {
						model.getItemDeck().addUsedCard(itemCard);
					}

				} catch (EmptyDeckException e) {
					LOGGER.error(e.getMessage(), e);
					this.emptyItemDeck = true;
					this.itemCard = null;
					return hasToMakeFakeNoise;
				}

			}
		}
		return hasToMakeFakeNoise;
	}

	/**
	 * 
	 * @return dangerous sector card that has been drawn
	 */
	public Card getDangerousSectorCard() {
		return this.dangerousSectorCard;
	}

	/**
	 * 
	 * @return item card that has been drawn, if it hasn't been drawn it returns
	 *         a null
	 */
	public Card getItemCard() {
		return this.itemCard;
	}

	/**
	 * 
	 * @return true, if the item card has been discarded becaus player has
	 *         already 3 cards in hand<br>
	 *         false, if not
	 */
	public boolean isDiscardedItemCard() {
		return discardedItemCard;
	}

	/**
	 * 
	 * @return true, if the item deck is empty<br>
	 *         false, if not
	 */
	public boolean isEmptyItemDeck() {
		return emptyItemDeck;
	}
}
