package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * This interface aggregates the Action Validator that evaluates the validity of
 * a certain action. It is dependent on the model, as certain rules might change
 * according to the game state. Its methods are implemented by the
 * {@link DefaultRules}, or by other rules sets.
 * 
 * @author Alberto Parravicini
 *
 */

public interface Rules {
    /**
     * Validates the movement action, according to the implemented rule set
     * 
     * @param model
     *            game where the action takes place
     * @param destination
     *            movement coordinate
     * @return true, if the action has been validated<br>
     *         false, if not
     */
    public boolean movementValidator(Model model, Coordinate destination);

    /**
     * Validates the attack action, according to the implemented rule set
     * 
     * @param model
     *            game where the action takes place
     * @return true, if the action has been validated<br>
     *         false, if not
     */
    public boolean attackValidator(Model model);

    /**
     * Validates the use of an item card, accord to the implemented rule set
     * 
     * @param model
     *            game where the action takes place
     * @param card
     *            item card that the player wants to use
     * @return true, if the action has been validated<br>
     *         false, if not
     */
    public boolean useItemCardValidator(Model model, ItemCard card);
}
