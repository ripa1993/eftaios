package it.polimi.ingsw.cg_8.model.sectors.special.escapehatch;

/**
 * Implementation of the not used escape hatch behaviour
 * 
 * @author Simone
 *
 */
public class NotUsedEHBehaviour implements EscapeHatchBehaviour {

	/**
	 * Default constructor
	 */
	public NotUsedEHBehaviour() {
	}

	/**
	 * Player is allowed to escape
	 */
	@Override
	public boolean allowEscape() {
		return true;
	}

	@Override
	public String toString() {
		return "NotUsedEHBehaviour [allowEscape()=" + allowEscape() + "]";
	}

}
