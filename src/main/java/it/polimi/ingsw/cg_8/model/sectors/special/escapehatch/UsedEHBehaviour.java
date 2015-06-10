package it.polimi.ingsw.cg_8.model.sectors.special.escapehatch;

/**
 * Implementation of the used escape hatch behaviour
 * 
 * @author Simone
 * @version 1.0
 */
public class UsedEHBehaviour implements EscapeHatchBehaviour {
	/**
	 * Default constructor
	 */
	public UsedEHBehaviour() {
	}

	/**
	 * Player is denied to escape
	 */
	@Override
	public boolean allowEscape() {
		return false;
	}

	@Override
	public String toString() {
		return "UsedEHBehaviour [allowEscape()=" + allowEscape() + "]";
	}

}
