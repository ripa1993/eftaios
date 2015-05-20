package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.controller.Rules;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseDefenseCard;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.itemCards.DefenseCard;
import it.polimi.ingsw.cg_8.model.noises.AttackNoise;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
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
	 * Current state of the game
	 */
	private Model model;
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
	public Attack(Model model) {
		this.model = model;
		this.attacker = model.getCurrentPlayerReference();
		victims = new HashSet<Player>();
		playersInSector = new HashSet<Player>();
	}

	/**
	 * Attack the players in the attacker' sector, unless they are defended; the
	 * attacker makes a noise, and so do the attacked players.
	 * 
	 * @param model
	 */
	public void makeAttack(Model model) {

		Set<Player> attackedPlayers = this.getPlayersInSector();

		for (Player p : attackedPlayers) {
			if (p.getCharacter().isDefendAllowed()) {
				UseDefenseCard defense = new UseDefenseCard();
				defense.useCard(new DefenseCard());
			} else {
				this.killPlayer(p);
				if (attacker.getCharacter() instanceof Alien) {
					((Alien)attacker.getCharacter()).feedAlien();
				}
			}
		}
		Noise attackNoise = new AttackNoise(model.getRoundNumber(), attacker,
				attacker.getLastPosition());
		model.getNoiseLogger().add(attackNoise);

	}

	/**
	 * @param model
	 * @return playersInSector: the players in the same sector as the attacker.
	 */
	public Set<Player> getPlayersInSector() {
		Coordinate target = attacker.getLastPosition();

		List<Player> playerList = model.getPlayers();
		for (Player p : playerList) {
			if (p.getLastPosition().equals(target)
					&& p.getState().equals(PlayerState.ALIVE_WAITING)) {
				playersInSector.add(p);
			}
		}
		return playersInSector;
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
	 * Get the players killed in this turn
	 * 
	 * @return victims
	 */
	public Set<Player> getVictims() {
		return victims;
	}

}
