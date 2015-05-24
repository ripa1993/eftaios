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

/**
 * When the player reaches a {@link DangerousSector}, he can draw a
 * {@link DangerousSectorCard}.
 * 
 * @author Alberto Parravicini
 *
 */
public class DrawDangerousSectorCard extends PlayerAction {

	/**
	 * The player produces a noise, unless otherwise is specified. If the drawn
	 * card is decorated, the player has to make a fake noise, draw an item
	 * card, or both.
	 * 
	 * @require currentPlayer.getPosition(currentTurn) !=
	 *          currentPlayer.getPosition(currentTurn-1) &&
	 *          currentPlayer.getPosition(currentTurn) instanceof
	 *          DangerousSector && currentPlayer hasn't attacked in this turn.
	 */
	public static boolean drawDangerousSectorCard(Model model) {

		Player player = model.getCurrentPlayerReference();

		try {
			Card dangerousSectorCard = model.getDangerousSectorDeck()
					.drawCard();

			if (dangerousSectorCard instanceof NoiseCard) {
				// draw an object
				if (((NoiseCard) dangerousSectorCard).hasToDrawItem() == true) {
					try {
						player.getHand().addItemCard(
								(ItemCard) model.getItemDeck().drawCard());
					} catch (TooManyCardsException e) {
						// TODO: something?
					}
				}
				// what kind of noise?
				if (((NoiseCard) dangerousSectorCard).hasToMakeFakeNoise() == false) {
					Noise movementNoise = new MovementNoise(
							model.getRoundNumber(), player,
							player.getLastPosition());
					model.getNoiseLogger().add(movementNoise);
					return false;
				} else {
					// TODO: fakeNoise; come ottenere la destinazione bersaglio?
					return true;
				}
			}
			return false;
		} catch (EmptyDeckException e) {
			/**
			 * This exception should never happen, as the deck is re-shuffled
			 * when empty.
			 */
			return false;
		}
		
	}
}
