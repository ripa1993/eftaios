package it.polimi.ingsw.cg_8.model.sectors;
/**
 * Abstract sector class, it is extended by any sector
 * @author Simone
 *
 */

public abstract class Sector extends Coordinate {
@Override
	public String toString() {
		return "Sector [x=" + x + ", y=" + y + "]";
	}

/**
 * Default constructor for {@link Sector}
 * @param x column number, first column is column 0
 * @param y	row number, first row is row 0
 */
	public Sector(int x, int y) {
		super(x, y);
	}
	
}
