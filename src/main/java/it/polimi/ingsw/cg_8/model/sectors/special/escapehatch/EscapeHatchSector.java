package it.polimi.ingsw.cg_8.model.sectors.special.escapehatch;

import it.polimi.ingsw.cg_8.model.sectors.special.SpecialSector;

/**
 * Escape hatch sector class. It's the sector where the player is allowed to try
 * to escape and win the game
 * 
 * @author Simone
 *
 */
public class EscapeHatchSector extends SpecialSector {
	/**
	 * Current behaviour of the escape hatch sector
	 */
	private EscapeHatchBehaviour status;
	/**
	 * Number of the escape hatch, 1 to 4
	 */
	final int number;

	/**
	 * Getter for current behaviour of the escape hatch sector
	 * 
	 * @return current behaviour of the escape hatch sector
	 */
	public EscapeHatchBehaviour getStatus() {
		return status;
	}

	/**
	 * Getter for escape hatch sector number
	 * 
	 * @return escape hatch sector number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Constructor for the escape hatch sector
	 * 
	 * @param x
	 *            column of the sector
	 * @param y
	 *            row of the sector
	 * @param number
	 *            number of the sector [1..4]
	 */
	public EscapeHatchSector(int x, int y, int number) {
		super(x, y);
		this.number = number;
		status = new NotUsedEHBehaviour();
	}

	/**
	 * Changes the status (behaviour) of the escape hatch from not used to used
	 */
	private void setUsed() {

		// change the status of the hatch from not used to used

		this.status = new UsedEHBehaviour();
	}

	/**
	 * Returns if the player is allowed to escape and changes the status of the
	 * escape hatch from not used to used
	 * 
	 * @return true, if you can use the hatch to escape<br>
	 *         false, if you cannot use the hatch to escape
	 */
	public boolean allowEscape() {

		// return true if you can use the hatch to escape, changes the status
		// from not used to used
		// return false if you cannot use the hatch to escape, because it has
		// been already used

		boolean allowedUse = status.allowEscape();
		if (allowedUse) {
			this.setUsed();
		}
		return allowedUse;
	}

	@Override
	public String toString() {
		return "EscapeHatchSector [status=" + status + ", number=" + number
				+ "]";
	}

}
