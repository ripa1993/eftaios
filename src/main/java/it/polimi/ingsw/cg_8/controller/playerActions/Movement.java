package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.Card;
import it.polimi.ingsw.cg_8.model.cards.escapeHatchCards.GreenEhCard;
import it.polimi.ingsw.cg_8.model.decks.EscapeHatchDeck;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidCoordinateException;
import it.polimi.ingsw.cg_8.model.noises.EscapeSectorNoise;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The {@link Controller#processInput()} takes a movement input and calls the
 * {@link Rules#checkValidMovement()} functions. Then the validateMovement
 * function creates an instance of this class and verifies the validity of the
 * move.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */

public class Movement implements PlayerAction {

    /**
     * The player who is moving
     */
    private final Player player;
    /**
     * Where the player would like to move
     */
    private final Coordinate destination;
    /**
     * Sector targeted my the movement action.
     */
    private Sector destinationSector;
    /**
     * Current game
     */
    private final Model model;
    /**
     * Log4j logger
     */
    private static final Logger LOGGER = LogManager.getLogger(Movement.class);

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
        this.destinationSector = new SecureSector();
    }

    /**
     * Changes the position of the player inside the model
     */
    public void makeMove() {

        int lastModelTurn = model.getRoundNumber();
        int lastPlayerTurn = player.getRoundNumber();

        try {
            destinationSector = model.getMap().getSectorByCoordinates(
                    destination);
        } catch (NotAValidCoordinateException e1) {
            LOGGER.error(e1.getMessage(), e1);
        }

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

        if (destinationSector instanceof EscapeHatchSector
                && ((EscapeHatchSector) destinationSector).allowEscape()) {
            // noise only if the escape hatch is usable
            Noise escapeSectorNoise = new EscapeSectorNoise(
                    model.getRoundNumber(), player, player.getLastPosition());
            Card escapeCard;
            try {
                escapeCard = drawEHSectorCard();
                if (escapeCard instanceof GreenEhCard) {
                    player.setEscaped();
                }
            } catch (EmptyDeckException e) {
                LOGGER.error(e.getMessage(), e);
            }
            model.addNoise(escapeSectorNoise);
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
