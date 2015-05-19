package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * When the input handler gets an action attack, the
 * {@link Rules#validateAttack()} function is called. The attack function
 * instantiate this Class and uses its method to validate the attack.
 * 
 * @author Alberto Parravicini
 *
 */
public class Attack extends PlayerAction {

	/**
	 * The player who attacks
	 */
	private Player attacker;
	/**
	 * List of players in the same sector as the attacker.
	 */
	private Set<Player> playersInSector;
	/**
	 * List of players who are killed by the attack
	 */
	private Set<Player> victims;

	/**
	 * Constructor of the class
	 * 
	 * @param attacker
	 */
	public Attack(Player attacker) {
		this.attacker = attacker;
		victims = new HashSet<Player>();
		playersInSector = new HashSet<Player>();
	}

	/**
	 * Checks if the attack is valid: if the player is a human, he had to use an
	 * attack card before attacking, in the same turn.
	 * 
	 * @return validAttack: whether the attack is allowed or not
	 */
	public boolean validAttack() {

		boolean validAttack = false;

		if (attacker.getCharacter().isAttackAllowed() == true) {
			validAttack = true;
		}
		return validAttack;
	}

	/**
	 * Checks if a player has a defense card: if so, this function is called.
	 * 
	 * @param player
	 */

	public void savePlayerWithDefense(Player player) {
		// TODO: rimouvere carta difesa dal giocatore e chiamare il metodo
		// resetBehaviour sull'attaccato per togliere la decorazione
		// defenseEnable
	}

	/**
	 * Kills the player passed as input parameter
	 * 
	 * @param victim
	 */
	public void killPlayer(Player victim) {
		victim.setDead();
		victims.add(victim);
	}

	/**
	 * @param model
	 * @return playersInSector: the players in the same sector as the attacker.
	 */
	public Set<Player> getPlayersInSector(Model model) {
		Coordinate destination = attacker.getLastPosition();

		List<Player> playerList = model.getPlayers();
		for (Player p : playerList) {
			if (p.getLastPosition().equals(destination)
					&& p.getState().equals(PlayerState.ALIVE_WAITING)) {
				playersInSector.add(p);
			}
		}
		return playersInSector;
	}

	/**
	 * Get the players killed in this turn
	 * 
	 * @return victims
	 */
	public Set<Player> getVictims() {
		return victims;
	}

}
