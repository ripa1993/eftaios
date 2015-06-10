package it.polimi.ingsw.cg_8.controller.playerActions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.GreenEhCard;
import it.polimi.ingsw.cg_8.model.decks.EscapeHatchDeck;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.noises.EscapeSectorNoise;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;
import it.polimi.ingsw.cg_8.server.ServerSocketPublisherThread;

/**
 * The {@link Controller#processInput()} takes a movement input and calls the
 * {@link Rules#checkValidMovement()} functions. Then the validateMovement
 * function creates an instance of this class and verifies the validity of the
 * move.
 * 
 * @author Alberto Parravicini
 * @version 1.0
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
	 * Current game
	 */
	private final Model model;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(Movement.class);

	/**
	 * Constructor
	 * 
	 * @param model
	 *            current game
	 * @param destination
	 *            coordinate of destination
	 */
	public Movement(Model model, Coordinate destination) {
		this.player = model.getCurrentPlayerReference();
		this.destination = destination;
		this.model = model;
	}

	/**
	 * Changes the position of the player inside the model
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
		} else if (lastPlayerTurn <= lastModelTurn) {
			player.editLastPosition(destination);
		}

		/**
		 * Depending on the type of the reached sector, different actions are
		 * performed.
		 */
		if (destination instanceof EscapeHatchSector) {

			Noise escapeSectorNoise = new EscapeSectorNoise(
					model.getRoundNumber(), player, player.getLastPosition());
			model.addNoise(escapeSectorNoise);
			if (((EscapeHatchSector) destination).getStatus().allowEscape()) {
				Card escapeCard;
				try {
					escapeCard = drawEHSectorCard();
					if (escapeCard instanceof GreenEhCard) {
						player.setEscaped();
					}
				} catch (EmptyDeckException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * Draw a card from the {@link EscapeHatchDeck}
	 * 
	 * @return an EscapeHatchCard
	 * @throws EmptyDeckException
	 */
	private Card drawEHSectorCard() throws EmptyDeckException {
		return model.getEscapeHatchDeck().drawCard();
	}
}
