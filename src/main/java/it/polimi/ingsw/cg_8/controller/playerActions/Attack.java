package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.Set;

/**
 * When the input handler gets an action attack, the attack function is called.
 * The attack function instantiate this Class and uses its method to validate
 * the attack.
 * 
 * @author Alberto Parravicini
 *
 */
public class Attack extends PlayerAction {

	private Set<Player> victims;
	private Set<Player> playersInSector;


	/* Checks if a player has a defense card: if so, this function is called. */
	public void savePlayerWithDefense() {
		
	}
	
	public void killPlayer(Player victim) {
		/* Change the player's status */
		victims.add(victim);		
	}

	public Set<Player> getPlayerInSector(Coordinate destination) {
		return playersInSector;
	}
	
	public Set<Player> getVictims() {
		return victims;
	}
}
