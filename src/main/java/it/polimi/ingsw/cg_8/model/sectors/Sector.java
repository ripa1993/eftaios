package it.polimi.ingsw.cg_8.model.sectors;


public abstract class Sector extends Coordinate {

	public Sector(int x, int y) {
		super(x, y);
	}
	
	public abstract SectorType getSectorType();
}
