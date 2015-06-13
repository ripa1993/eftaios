package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * Noise made when a player attacks
 * 
 * @author Simone
 * @version 1.0
 */
public class AttackNoise extends Noise {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3887771292366833452L;
	/**
	 * Character
	 */
	private final boolean isAlien;

	/**
	 * Constructor
	 * 
	 * @param turnNumber
	 *            turn number
	 * @param player
	 *            player that made noise
	 * @param coordinate
	 *            coordinate of the player
	 */
	public AttackNoise(int turnNumber, Player player, Coordinate coordinate) {
		super(turnNumber, player, coordinate);
		if (player.getCharacter() instanceof Alien) {
			isAlien = true;
		} else {
			isAlien = false;
		}
	}

	/**
	 * 
	 * @return true, if the player is an alien<br>
	 *         false, if the player is a human
	 */
	public boolean isAlien() {
		return isAlien;
	}

	@Override
	public String toString() {
		String character;
		if (isAlien) {
			character = "Alien";
		} else {
			character = "Human";
		}
		return "Attack noise: " + super.toString() + " Character: " + character;
	}

}
