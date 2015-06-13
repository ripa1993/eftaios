package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.SpawnSector;

/**
 * Contains method to validate an attack by a requesting player
 * 
 * @author Simone
 * @version 1.0
 */
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
		Sector playerPosition = model.getMap().getSectors()
				.get(attacker.getLastPosition());
		if (attacker.getCharacter().isAttackAllowed() == true
				&& ((playerPosition instanceof SpawnSector) == false)
				&& ((playerPosition instanceof EscapeHatchSector) == false)) {
			validAttack = true;
		}
		return validAttack;
	}
}
