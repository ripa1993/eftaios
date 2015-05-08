package it.polimi.ingsw.cg_8.model.map.creator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gm == null) ? 0 : gm.hashCode());
		result = prime * result + ((sectors == null) ? 0 : sectors.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapCreator other = (MapCreator) obj;
		if (gm == null) {
			if (other.gm != null)
				return false;
		} else if (!gm.equals(other.gm))
			return false;
		if (sectors == null) {
			if (other.sectors != null)
				return false;
		} else if (!sectors.equals(other.sectors))
			return false;
		return true;
	}

	private final GameMap gm;
	private final Map<Coordinate, Sector> sectors;
	
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
	
	protected void addDangerousSector(int x, int y){
		addDangerousSector(new Coordinate(x, y));
	}

	protected void addSecureSector(Coordinate c) {
		Sector currentSector = new SecureSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}
	
	protected void addSecureSector(int x, int y){
		addSecureSector(new Coordinate(x, y));
	}


	protected void addEscapeHatchSector(Coordinate c, int number) {
		Sector currentSector = new EscapeHatchSector(c.getX(), c.getY(), number);
		this.addSector(c, currentSector);
	}
	protected void addEscapeHatchSector(int x, int y, int num){
		addEscapeHatchSector(new Coordinate(x, y), num);
	}


	protected void addHumanSector(Coordinate c) {
		Sector currentSector = new HumanSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}
	
	protected void addHumanSector(int x, int y){
		addHumanSector(new Coordinate(x,y));
	}

	protected void addAlienSector(Coordinate c) {
		Sector currentSector = new AlienSector(c.getX(), c.getY());
		this.addSector(c, currentSector);
	}
	protected void addAlienSector(int x, int y){
		addAlienSector(new Coordinate(x,y));
	}

	// can be used only with secure and dangerous sectors, otherwise it will do
	// nothing
	protected void addColumn(SectorType st, int column, int startingRow,
			int endingRow) {
		Set<Coordinate> currentSet = new HashSet<Coordinate>();
		// add a column of coordinates to currentSet
		for (int i = startingRow; i <= endingRow; i++) {
			currentSet.add(new Coordinate(column, i));
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
	protected void addRow(SectorType st, int row, int startingColumn,
			int endingColumn) {
		Set<Coordinate> currentSet = new HashSet<Coordinate>();
		// add a column of coordinates to currentSet
		for (int i = startingColumn; i <= endingColumn; i++) {
			currentSet.add(new Coordinate(i, row));
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
