package it.polimi.ingsw.cg_8.model.player.character.human;

/**
 * Human behaviour, used to implement Decorator pattern
 * 
 * @author Simone
 *
 */
public interface HumanBehaviour {
	/**
	 * @return true, if the attack is allowed<br>
	 *         false, if the attack is denied
	 */
	public abstract boolean isAttackAllowed();

	/**
	 * @return true, if the defense is allowed<br>
	 *         false, if the defense is denied
	 */
	public abstract boolean isDefendAllowed();

	/**
	 * @return max distance that the character can walk
	 */
	public abstract int getMaxAllowedMovement();

	/**
	 * 
	 * @return true, if the player has to draw a sector card<br>
	 *         false, if the player has not to draw a sector card
	 */
	public abstract boolean hasToDrawSectorCard();
}
