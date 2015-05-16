package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.List;

/**
 * This class contains most of the game logic, in terms of rules and actions
 * allowed to the players
 * 
 * @author Alberto Parravicini
 *
 */
public class Rules {
	/* Checks whether a move is allowed or not. */
	public boolean checkValidMovement(Player player, Coordinate destination) {
		return false;
	}

	/* Changes the active player in a certain turn. */
	public Player changeCurrentPlayer() {
		return null;
	}

	/*
	 * Removes the player from the game, if certain conditions are met (the game
	 * is over, the player is inactive, etc...).
	 */
	public Player removePlayerFromGame(Player player) {
		return null;
	}

	public boolean validateAttack(Player player) {
		/*
		 * Check if the action is permitted (if the player is Human he needs to
		 * have an AttackCard), instantiate the Attack class, get the position
		 * of the attacker, getPlayersInSector, check if they have a shield
		 * card, if they do remove their card and notify everyone of their
		 * position (Instantiate Shield Card), if not kill them.
		 */
		boolean validAttack = false;
		
		if (player.getCharacter() instanceof Alien) {
			validAttack = true;
		}
		else if (player.getCharacter() instanceof Human) {
			List<ItemCard> heldCards = player.getHand().getHeldCards();
			for(ItemCard i :heldCards) {
				if (i instanceof AttackCard) {
					heldCards.remove(i);
				}
			
				
				
			}
		}
		return false;
	}

	public void useItemCard(Player player, ItemCard itemCard) {

	}

}
