package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;

/**
 * Action that uses a sedatives card. It allows a human to not draw a dangerous
 * sector card
 * 
 * @author Simone
 *
 */
public class UseSedativesCard extends UseItemCard {
	/**
	 * Constructor
	 * 
	 * @param model
	 *            reference to the game
	 */
	public UseSedativesCard(Model model) {
		super(model);
	}

	@Override
	public void useCard() {
		// TODO: change method to direct player access
		Player currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
		// Assume that the player is a human by ipothesis
		Human currentPlayerCharacter = (Human) currentPlayer.getCharacter();
		currentPlayerCharacter.enableSedatives();
	}

}
