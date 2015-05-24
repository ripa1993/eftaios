package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.player.Player;

public class AttackValidator {
	
	/**
	 * Checks if the attack is valid: if the player is a human, he had to use an
	 * attack card before attacking, in the same turn.
	 * 
	 * @return validAttack: whether the attack is allowed or not
	 */
	public static boolean validateAttack(Model model) {
		
		Player attacker = model.getCurrentPlayerReference();
		boolean validAttack = false;

		if (attacker.getCharacter().isAttackAllowed() == true) {
			validAttack = true;
		}
		return validAttack;
	}
}
