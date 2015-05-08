package it.polimi.ingsw.cg_8.model.map.creator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.cg_8.model.map.GalvaniMap;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.SectorType;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.AlienSector;
import it.polimi.ingsw.cg_8.model.sectors.special.spawn.HumanSector;

public abstract class MapCreator {

	// x is horizontal, 0 = "A"
	// y is vertical, 0 = "01"

	GameMap gm;
	Map<Coordinate, Sector> sectors;
	
	public MapCreator(GameMap gm) {
		this.gm=gm;
		sectors = gm.getSectors();
	}

	// adds a generic sector s to coordinate c in the sectors map

	public GameMap getGm() {
		return gm;
	}

	private void addSector(Coordinate c, Sector s) {
		sectors.put(c, s);
	}

	// addXSector adds a sector of X type to the sectors map in the c coordinate

	protected void addDangerousSector(Coordinate c) {
		Sector currentSector = new DangerousSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}
	
	private void addDangerousSector(int x, int y){
		addDangerousSector(new Coordinate(x, y));
	}

	private void addSecureSector(Coordinate c) {
		Sector currentSector = new SecureSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}
	
	private void addSecureSector(int x, int y){
		addSecureSector(new Coordinate(x, y));
	}


	private void addEscapeHatchSector(Coordinate c, int number) {
		Sector currentSector = new EscapeHatchSector(c.getX(), c.getY(), number);
		this.addSector(c, currentSector);
	}
	private void addEscapeHatchSector(int x, int y, int num){
		addEscapeHatchSector(new Coordinate(x, y), num);
	}


	private void addHumanSector(Coordinate c) {
		Sector currentSector = new HumanSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}
	
	private void addHumanSector(int x, int y){
		addHumanSector(new Coordinate(x,y));
	}

	private void addAlienSector(Coordinate c) {
		Sector currentSector = new AlienSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}
	private void addAlienSector(int x, int y){
		addAlienSector(new Coordinate(x,y));
	}

	// can be used only with secure and dangerous sectors, otherwise it will do
	// nothing
	protected void addColumn(SectorType st, int column, int startingRow,
			int endingRow) {
		Set<Coordinate> currentSet = new HashSet<Coordinate>();
		// add a column of coordinates to currentSet
		for (int i = startingRow; i <= endingRow; i++) {
			currentSet.add(new Coordinate(i, column));
		}
		Iterator<Coordinate> it = currentSet.iterator();
		switch (st) {
		case DANGEROUS_SECTOR: {
			while (it.hasNext()) {
				addDangerousSector(it.next());
			}
		}
		case SECURE_SECTOR: {
			while (it.hasNext()) {
				addSecureSector(it.next());
			}

		}
		default: {
			return;
		}
		}
	}

	// can be used only with secure and dangerous sectors, otherwise it will do
	// nothing
	private void addRow(SectorType st, int row, int startingColumn,
			int endingColumn) {
		Set<Coordinate> currentSet = new HashSet<Coordinate>();
		// add a column of coordinates to currentSet
		for (int i = startingColumn; i <= endingColumn; i++) {
			currentSet.add(new Coordinate(row, i));
		}
		Iterator<Coordinate> it = currentSet.iterator();
		switch (st) {
		case DANGEROUS_SECTOR: {
			while (it.hasNext()) {
				addDangerousSector(it.next());
			}
		}
		case SECURE_SECTOR: {
			while (it.hasNext()) {
				addSecureSector(it.next());
			}

		}
		default: {
			return;
		}
		}
	}

	abstract public GameMap createMap();

}
