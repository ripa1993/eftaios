package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.controller.playeraction.AttackValidator;
import it.polimi.ingsw.cg_8.controller.playeraction.MovementValidator;
import it.polimi.ingsw.cg_8.controller.playeraction.UseItemCardValidator;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.item.ItemCard;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * Default Rules Set of the game. Its method can be overridden by implementing a
 * decorator.
 * 
 * @author Alberto Parravicini
 *
 */
public class DefaultRules implements Rules {

    @Override
    public boolean movementValidator(Model model, Coordinate destination) {
        return MovementValidator.validateMove(model, destination);
    }

    @Override
    public boolean attackValidator(Model model) {
        return AttackValidator.validateAttack(model);
    }

    @Override
    public boolean useItemCardValidator(Model model, ItemCard card) {
        return UseItemCardValidator.validateItemCardUsage(model, card);
    }

}
