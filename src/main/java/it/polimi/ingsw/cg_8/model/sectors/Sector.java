package it.polimi.ingsw.cg_8.model.sectors;

/**
 * Abstract sector class, it is extended by any sector
 * 
 * @author Simone
 * @version 1.0
 */

public abstract class Sector extends Coordinate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 38270096213388500L;

	/**
	 * Default constructor for {@link Sector}
	 * 
	 * @param x
	 *            column number, first column is column 0
	 * @param y
	 *            row number, first row is row 0
	 */
	public Sector(int x, int y) {
		super(x, y);
	}

	@Override
	public String toString() {
		return "Sector [x=" + x + ", y=" + y + "]";
	}
}