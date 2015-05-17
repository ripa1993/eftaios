package it.polimi.ingsw.cg_8.model.sectors.special.escapehatch;

/**
 * Behaviour of escape hatch sectors. This is used in the implementation of the
 * strategy pattern. There are two possible strategies: the one that allows the
 * player to escape (if the escape hatch has never been used) and the other one
 * that denies the player to escape (if the escape hatch has already been used)
 * 
 * @author Simone
 *
 */
public interface EscapeHatchBehaviour {
	/**
	 * @return true if the player is allowed to escape, false if the player is
	 *         denied to escape
	 */
	public boolean allowEscape();
}
