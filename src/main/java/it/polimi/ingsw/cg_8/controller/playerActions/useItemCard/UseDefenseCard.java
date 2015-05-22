package it.polimi.ingsw.cg_8.controller.playerActions.useItemCard;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;

/**
 * Action that uses a defense card. It allows a human to defend himself during
 * an attack
 * 
 * @author Simone
 *
 */
public class UseDefenseCard extends UseItemCard {

	/**
	 * Constructor
	 * 
	 * @param model
	 *            reference to the game
	 */
	public UseDefenseCard(Model model) {
		super(model);
	}

	@Override
	public void useCard() {
		// TODO: change method to direct player access
		Player currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
		// Assume that the player is a human by hypothesis
		Human currentPlayerCharacter = (Human) currentPlayer.getCharacter();
		currentPlayerCharacter.enableDefend();
	}

}
