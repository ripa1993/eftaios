package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.controller.playerActions.PlayerAction;
import it.polimi.ingsw.cg_8.model.Model;

/**
 * Action that use an item card. It is extend by specific item card types:
 * {@link UseAdrenalineCard Adrenaline}, {@link UseAttackCard Attack},
 * {@link UseDefenseCard Defense}, {@link UseSedativesCard Sedatives},
 * {@link UseSpotlightCard Spotlight} and {@link UseTeleportCard Teleport}
 * 
 * @author Simone
 *
 */
public abstract class UseItemCard extends PlayerAction {

	/**
	 * Reference to current game
	 */
	Model model;

	/**
	 * Constructor
	 * 
	 * @param model
	 *            reference to the game
	 */
	public UseItemCard(Model model) {
		this.model = model;
	}

	/**
	 * Applies card effect
	 * 
	 * @param model
	 *            current game
	 */
	public abstract void useCard();
}
