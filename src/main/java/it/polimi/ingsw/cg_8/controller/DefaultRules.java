package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.controller.playerActions.AttackValidator;
import it.polimi.ingsw.cg_8.controller.playerActions.MovementValidator;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * Default Rules Set of the game.
 * Its method can be overriden by implementing a decorator.
 * @author Alberto Parravicini
 *
 */
public class DefaultRules implements Rules{

	@Override
	public boolean MovementValidator(Model model, Coordinate destination) {
		return MovementValidator.validateMove(model, destination);
	}

	@Override
	public boolean AttackValidator(Model model) {
		return AttackValidator.validateAttack(model);
	}

}
