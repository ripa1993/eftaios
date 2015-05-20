package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NoiseCard;
import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.GreenEhCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.decks.EscapeHatchDeck;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.TooManyCardsException;
import it.polimi.ingsw.cg_8.model.noises.EscapeSectorNoise;
import it.polimi.ingsw.cg_8.model.noises.MovementNoise;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;

/**
 * The {@link Controller#processInput()} takes a movement input and calls the
 * {@link Rules#checkValidMovement()} functions. Then the validateMovement
 * function creates an instance of this class and verifies the validity of the
 * move.
 * 
 * @author Alberto Parravicini
 *
 */

public class Movement extends PlayerAction {

	/**
	 * The player who is moving
	 */
	private final Player player;
	/**
	 * Where the player would like to move
	 */
	private final Coordinate destination;
	/**
	 * 
	 */
	private final Model model;

	/**
	 * 
	 * @param player
	 * @param destination
	 * @param gameMap
	 * @return Instance of the Movement class
	 */
	public Movement(Model model, Coordinate destination) {
		this.player = model.getCurrentPlayerReference();
		this.destination = destination;
		this.model = model;
	}

	/**
	 * Changes the position of the player
	 */
	public void makeMove() {

		int lastModelTurn = model.getRoundNumber();
		int lastPlayerTurn = player.getRoundNumber();

		/**
		 * If the player used a teleport card, the position is changed using
		 * editLastPosition(Coordinate);
		 */
		if (lastPlayerTurn == lastModelTurn - 1) {
			player.setPosition(destination);
		} else if (lastPlayerTurn == lastModelTurn) {
			player.editLastPosition(destination);
		}

		/**
		 * Depending on the type of the reached sector, different actions are
		 * performed.
		 */
		if (destination instanceof SecureSector) {

			// TODO: logger
		} else if (destination instanceof DangerousSector) {

			drawDangerousSectorCard();
		} else if (destination instanceof EscapeHatchSector) {

			Noise escapeSectorNoise = new EscapeSectorNoise(
					model.getRoundNumber(), player, player.getLastPosition());
			model.getNoiseLogger().add(escapeSectorNoise);
			if (((EscapeHatchSector) destination).getStatus().allowEscape()) {
				Card escapeCard;
				try {
					escapeCard = drawEHSectorCard();
					if (escapeCard instanceof GreenEhCard) {
						// giocatore scappato
					}
				} catch (EmptyDeckException e) {
					// TODO: non si verifica, la partita termina se pesco la
					// quarta carta scialuppa.
				}
			}
		}
	}

	/**
	 * The player produces a noise, unless otherwise is specified. If the drawn
	 * card is decorated, the player has to make a fake noise, draw an item
	 * card, or both.
	 */
	private void drawDangerousSectorCard() {
		try {
			Card dangerousSectorCard = model.getDangerousSectorDeck()
					.drawCard();

			if (dangerousSectorCard instanceof NoiseCard) {
				if (((NoiseCard) dangerousSectorCard).hasToMakeFakeNoise() == false) {
					Noise movementNoise = new MovementNoise(
							model.getRoundNumber(), player,
							player.getLastPosition());
					model.getNoiseLogger().add(movementNoise);
				} else {
					// TODO: fakeNoise;
				}
				if (((NoiseCard) dangerousSectorCard).hasToDrawItem() == true) {
					try {
						player.getHand().addItemCard(
								(ItemCard) model.getItemDeck().drawCard());
					} catch (TooManyCardsException e) {
						// TODO: something?
					}
				}
			}
		} catch (EmptyDeckException e) {
			/**
			 * This exception should never happen, as the deck is re-shuffled
			 * when empty.
			 */
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Draw a card from the {@link EscapeHatchDeck}
	 * @return an EscapeHatchCard
	 * @throws EmptyDeckException
	 */
	private Card drawEHSectorCard() throws EmptyDeckException {
		return model.getEscapeHatchDeck().drawCard();
	}
}
