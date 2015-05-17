package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.controller.playerActions.Attack;
import it.polimi.ingsw.cg_8.controller.playerActions.Movement;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.cards.itemCards.DefenseCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.ItemCard;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.List;
import java.util.Set;

/**
 * This class contains most of the game logic, in terms of rules and actions
 * allowed to the players
 * 
 * @author Alberto Parravicini
 *
 */
public class Rules {
	private Model model;

	public Rules(Model model) {
		this.model = model;
	}

	/* Checks whether a move is allowed or not. */
	public boolean checkValidMovement(Coordinate destination) {
		boolean validMovement = false;
		Player player = model.getPlayers().get(model.getCurrentPlayer());
		GameMap gameMap = model.getMap();
		/*
		 * Get the sectors reachable by the player, see if the destination is
		 * among them.
		 */
		Movement move = new Movement(player, destination, gameMap);
		validMovement = move.evaluateMove();
		
		return validMovement;
	}

	/* TODO: Changes the active player in a certain turn. */
	public Player changeCurrentPlayer() {
		return null;
	}

	/*
	 * TODO: Removes the player from the game, if certain conditions are met (the game
	 * is over, the player is inactive, etc...).
	 */
	public Player removePlayerFromGame(Player player) {
		return null;
	}

	public boolean validateAttack() {
		/*
		 * Instantiate the Attack class, check if the action is permitted (if
		 * the player is Human he needs to have an AttackCard), make Noise, get
		 * the position of the attacker, getPlayersInSector, check if they have
		 * a shield card, if they do remove their card and notify everyone of
		 * their position (Instantiate Shield Card), if not kill them.
		 */
		Player player = model.getPlayers().get(model.getCurrentPlayer());
		Attack attackClass = new Attack(player);
		boolean validAttack = attackClass.validAttack();

		if (validAttack == true) {
			/* TODO: Make Noise! */
			Set<Player> attackedPlayers = attackClass.getPlayersInSector(model);

			for (Player p : attackedPlayers) {
				List<ItemCard> heldCards = p.getHand().getHeldCards();

				for (ItemCard c : heldCards) {
					if (c instanceof DefenseCard
							&& p.getCharacter() instanceof Human) {
						heldCards.remove(c);
						attackClass.savePlayerWithDefense(p);
					} else {
						attackClass.killPlayer(p);
					}
				}
			}
			return validAttack;
		} else {
			return validAttack;
		}
	}
	/* TODO: */
	public void useItemCard(Player player, ItemCard itemCard) {

	}

}
