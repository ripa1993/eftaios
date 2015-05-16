package it.polimi.ingsw.cg_8.model.sectors;
/**
 * Abstract sector class, every sector extends this class
 * @author Simone
 *
 */

public abstract class Sector extends Coordinate {
/**
 * Default constructor for sector
 * @param x column number, first column is column 0
 * @param y	row number, first row is row 0
 */
	public Sector(int x, int y) {
		super(x, y);
	}
	
}
