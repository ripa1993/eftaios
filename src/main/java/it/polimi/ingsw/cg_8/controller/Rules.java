package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * This interface aggregates the Action Validator that evaluates the validity of
 * a certain action. It is dependent on the model, as certain rules might change
 * according to the game state. Its methods are implemented by the
 * {@link DefaultRules}, or by other rules sets. >>>>>>>
 * refs/remotes/origin/feat/PlayerActions
 * 
 * @author Alberto Parravicini
 *
 */

public interface Rules {

	public boolean MovementValidator(Model model, Coordinate destination);

	public boolean AttackValidator(Model model);
}
