package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.HashSet;
import java.util.List;
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

	private Player attacker;
	private Set<Player> victims;
	private Set<Player> playersInSector;
	
	
	public Attack(Player attacker) {
		this.attacker = attacker;
		victims = new HashSet<Player>();
		playersInSector = new HashSet<Player>();	
	}
	
	/** Checks if the attack is valid: if the player is a human, he has to use an AttackCard);
	 * 
	 * @param player
	 * @return
	 */
	public boolean validAttack() {
		boolean validAttack = false;

		if (attacker.getCharacter() instanceof Alien) {
			validAttack = true;
		} else if (attacker.getCharacter() instanceof Human) {
			List<ItemCard> heldCards = attacker.getHand().getHeldCards();
			for (ItemCard c : heldCards) {
				if (c instanceof AttackCard) {
					heldCards.remove(c);
					/* TODO: Use Attack Card */
					validAttack = true;
				}
			}
		}
		return validAttack;
	}

	/* Checks if a player has a defense card: if so, this function is called. */
	public void savePlayerWithDefense() {
		
		
	}

	public void killPlayer(Player victim) {
		victim.setDead();
		victims.add(victim);
	}

	public Set<Player> getPlayersInSector(Model model) {
		Coordinate destination = attacker.getLastPosition();
		/* Get players in "destination"*/
		List<Player> playerList = model.getPlayers();
		for(Player p: playerList) {
			if (p.getLastPosition().equals(destination) && p.getState().equals(PlayerState.ALIVE_WAITING)) {
				playersInSector.add(p);
			}
		}
		return playersInSector;
	}

	public Set<Player> getVictims() {
		return victims;
	}

}
