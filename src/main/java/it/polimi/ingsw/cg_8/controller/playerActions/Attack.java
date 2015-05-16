package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

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

	private Set<Player> victims;
	private Set<Player> playersInSector;
	
	/** Checks if the attack is valid: if the player is a human, he has to use an AttackCard);
	 * 
	 * @param player
	 * @return
	 */
	public boolean validAttack(Player player) {
		boolean validAttack = false;

		if (player.getCharacter() instanceof Alien) {
			validAttack = true;
		} else if (player.getCharacter() instanceof Human) {
			List<ItemCard> heldCards = player.getHand().getHeldCards();
			for (ItemCard i : heldCards) {
				if (i instanceof AttackCard) {
					heldCards.remove(i);
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
