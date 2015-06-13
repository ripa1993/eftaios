package it.polimi.ingsw.cg_8.model.player.character.alien;

/**
 * Implementation of strategy pattern
 * 
 * @author Simone
 * @version 1.0
 */
public interface AlienBehaviour {
	/**
	 * @return max distance that the character can walk
	 */
	public int getMaxMovementDistance();
}
