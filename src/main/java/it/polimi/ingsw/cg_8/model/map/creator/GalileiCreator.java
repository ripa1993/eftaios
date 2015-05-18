package it.polimi.ingsw.cg_8.model.map.creator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import it.polimi.ingsw.cg_8.model.map.GalileiMap;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.SectorType;
/**
 * Implementation of the factory pattern, it creates a GalileiMap
 * @author Simone
 *
 */
public class GalileiCreator extends MapCreator {

	
	/**
	 * Map that is going to be populated
	 */
	private final GameMap galileiMap;
	/**
	 * Constructor
	 */
	public GalileiCreator() {
		super(new GalileiMap(new Coordinate(11,7), new Coordinate(11,5)));
		galileiMap = this.getGm();
	}
	/**
	 * Adds all Dangerous Sectors
	 */
	private void addDS() {
		addColumn(SectorType.DANGEROUS_SECTOR, 0, 1, 2);
		addColumn(SectorType.DANGEROUS_SECTOR, 1, 2, 3);
		addColumn(SectorType.DANGEROUS_SECTOR, 1, 7, 8);
		addColumn(SectorType.DANGEROUS_SECTOR, 1, 10, 11);
		addColumn(SectorType.DANGEROUS_SECTOR, 2, 1, 12);
		addColumn(SectorType.DANGEROUS_SECTOR, 3, 1, 2);
		addColumn(SectorType.DANGEROUS_SECTOR, 3, 7, 8);
		addColumn(SectorType.DANGEROUS_SECTOR, 3, 10, 12);
		addColumn(SectorType.DANGEROUS_SECTOR, 4, 2, 5);
		addColumn(SectorType.DANGEROUS_SECTOR, 4, 7, 10);
		addColumn(SectorType.DANGEROUS_SECTOR, 5, 1, 8);
		addColumn(SectorType.DANGEROUS_SECTOR, 6, 0, 5);
		addColumn(SectorType.DANGEROUS_SECTOR, 6, 7, 10);
		addColumn(SectorType.DANGEROUS_SECTOR, 7, 7, 8);
		addColumn(SectorType.DANGEROUS_SECTOR, 7, 11, 12);
		addColumn(SectorType.DANGEROUS_SECTOR, 8, 1, 4);
		addColumn(SectorType.DANGEROUS_SECTOR, 8, 6, 7);
		addColumn(SectorType.DANGEROUS_SECTOR, 8, 9, 10);
		addColumn(SectorType.DANGEROUS_SECTOR, 9, 1, 5);
		addColumn(SectorType.DANGEROUS_SECTOR, 9, 7, 10);
		addColumn(SectorType.DANGEROUS_SECTOR, 10, 2, 3);
		addColumn(SectorType.DANGEROUS_SECTOR, 12, 2, 3);
		addColumn(SectorType.DANGEROUS_SECTOR, 11, 11, 12);
		addColumn(SectorType.DANGEROUS_SECTOR, 12, 11, 13);
		addColumn(SectorType.DANGEROUS_SECTOR, 13, 0, 1);
		addColumn(SectorType.DANGEROUS_SECTOR, 13, 3, 5);
		addColumn(SectorType.DANGEROUS_SECTOR, 13, 7, 12);
		addColumn(SectorType.DANGEROUS_SECTOR, 14, 5, 7);
		addColumn(SectorType.DANGEROUS_SECTOR, 14, 9, 12);
		addColumn(SectorType.DANGEROUS_SECTOR, 15, 4, 10);
		addColumn(SectorType.DANGEROUS_SECTOR, 15, 12, 13);
		addColumn(SectorType.DANGEROUS_SECTOR, 16, 1, 2);
		addColumn(SectorType.DANGEROUS_SECTOR, 16, 6, 9);
		addColumn(SectorType.DANGEROUS_SECTOR, 16, 11, 12);
		addColumn(SectorType.DANGEROUS_SECTOR, 17, 1, 2);
		addColumn(SectorType.DANGEROUS_SECTOR, 18, 3, 8);
		addColumn(SectorType.DANGEROUS_SECTOR, 18, 11, 12);
		addColumn(SectorType.DANGEROUS_SECTOR, 19, 4, 5);
		addColumn(SectorType.DANGEROUS_SECTOR, 19, 10, 12);
		addColumn(SectorType.DANGEROUS_SECTOR, 20, 1, 3);
		addColumn(SectorType.DANGEROUS_SECTOR, 20, 5, 10);
		addColumn(SectorType.DANGEROUS_SECTOR, 20, 12, 13);
		addColumn(SectorType.DANGEROUS_SECTOR, 21, 2, 5);
		addColumn(SectorType.DANGEROUS_SECTOR, 21, 8, 11);
		addColumn(SectorType.DANGEROUS_SECTOR, 22, 12, 13);
		Set<Coordinate> dangerousCoordinate = new HashSet<Coordinate>();
		dangerousCoordinate.add(new Coordinate(0, 13));
		dangerousCoordinate.add(new Coordinate(1, 0));
		dangerousCoordinate.add(new Coordinate(1, 5));
		dangerousCoordinate.add(new Coordinate(1, 13));
		dangerousCoordinate.add(new Coordinate(3, 4));
		dangerousCoordinate.add(new Coordinate(4, 12));
		dangerousCoordinate.add(new Coordinate(5, 10));
		dangerousCoordinate.add(new Coordinate(6, 12));
		dangerousCoordinate.add(new Coordinate(7, 3));
		dangerousCoordinate.add(new Coordinate(7, 5));
		dangerousCoordinate.add(new Coordinate(8, 12));
		dangerousCoordinate.add(new Coordinate(9, 12));
		dangerousCoordinate.add(new Coordinate(10, 0));
		dangerousCoordinate.add(new Coordinate(10, 5));
		dangerousCoordinate.add(new Coordinate(10, 7));
		dangerousCoordinate.add(new Coordinate(10, 9));
		dangerousCoordinate.add(new Coordinate(10, 11));
		dangerousCoordinate.add(new Coordinate(10, 13));
		dangerousCoordinate.add(new Coordinate(11, 0));
		dangerousCoordinate.add(new Coordinate(11, 2));
		dangerousCoordinate.add(new Coordinate(11, 4));
		dangerousCoordinate.add(new Coordinate(11, 9));
		dangerousCoordinate.add(new Coordinate(12, 0));
		dangerousCoordinate.add(new Coordinate(12, 5));
		dangerousCoordinate.add(new Coordinate(12, 7));
		dangerousCoordinate.add(new Coordinate(12, 9));
		dangerousCoordinate.add(new Coordinate(14, 1));
		dangerousCoordinate.add(new Coordinate(15, 1));
		dangerousCoordinate.add(new Coordinate(16, 4));
		dangerousCoordinate.add(new Coordinate(17, 4));
		dangerousCoordinate.add(new Coordinate(17, 8));
		dangerousCoordinate.add(new Coordinate(17, 12));
		dangerousCoordinate.add(new Coordinate(18, 1));
		dangerousCoordinate.add(new Coordinate(19, 1));
		dangerousCoordinate.add(new Coordinate(21, 13));
		dangerousCoordinate.add(new Coordinate(22, 1));
		dangerousCoordinate.add(new Coordinate(22, 8));
		Iterator<Coordinate> it = dangerousCoordinate.iterator();
		while (it.hasNext()) {
			addDangerousSector(it.next());
		}
	}
	/**
	 * Adds all Secure Sectors
	 */
	private void addSS() {
		addColumn(SectorType.SECURE_SECTOR, 0, 3, 5);
		addColumn(SectorType.SECURE_SECTOR, 0, 8, 12);
		addColumn(SectorType.SECURE_SECTOR, 7, 0, 2);
		addRow(SectorType.SECURE_SECTOR, 13, 6, 9);
		addRow(SectorType.SECURE_SECTOR, 13, 2, 3);
		addRow(SectorType.SECURE_SECTOR, 6, 6, 7);
		addRow(SectorType.SECURE_SECTOR, 1, 10, 12);
		addRow(SectorType.SECURE_SECTOR, 8, 10, 12);
		addRow(SectorType.SECURE_SECTOR, 10, 10, 12);
		addRow(SectorType.SECURE_SECTOR, 13, 13, 14);
		addColumn(SectorType.SECURE_SECTOR, 15, 2, 3);
		addRow(SectorType.SECURE_SECTOR, 0, 15, 17);
		addRow(SectorType.SECURE_SECTOR, 3, 16, 17);
		addRow(SectorType.SECURE_SECTOR, 5, 16, 17);
		addColumn(SectorType.SECURE_SECTOR, 17, 6, 7);
		addColumn(SectorType.SECURE_SECTOR, 19, 6, 7);
		addRow(SectorType.SECURE_SECTOR, 0, 20, 21);
		addColumn(SectorType.SECURE_SECTOR, 22, 2, 5);
		addColumn(SectorType.SECURE_SECTOR, 22, 9, 11);

		Set<Coordinate> secureCoordinate = new HashSet<Coordinate>();
		secureCoordinate.add(new Coordinate(1, 4));
		secureCoordinate.add(new Coordinate(1, 9));
		secureCoordinate.add(new Coordinate(2, 0));
		secureCoordinate.add(new Coordinate(3, 9));
		secureCoordinate.add(new Coordinate(4, 1));
		secureCoordinate.add(new Coordinate(4, 11));
		secureCoordinate.add(new Coordinate(5, 0));
		secureCoordinate.add(new Coordinate(5, 9));
		secureCoordinate.add(new Coordinate(6, 11));
		secureCoordinate.add(new Coordinate(8, 0));
		secureCoordinate.add(new Coordinate(8, 8));
		secureCoordinate.add(new Coordinate(9, 0));
		secureCoordinate.add(new Coordinate(10, 4));
		secureCoordinate.add(new Coordinate(11, 3));
		secureCoordinate.add(new Coordinate(11, 13));
		secureCoordinate.add(new Coordinate(12, 4));
		secureCoordinate.add(new Coordinate(13, 2));
		secureCoordinate.add(new Coordinate(14, 4));
		secureCoordinate.add(new Coordinate(14, 8));
		secureCoordinate.add(new Coordinate(15, 11));
		secureCoordinate.add(new Coordinate(16, 10));
		secureCoordinate.add(new Coordinate(16, 13));
		secureCoordinate.add(new Coordinate(17, 11));
		secureCoordinate.add(new Coordinate(19, 13));
		secureCoordinate.add(new Coordinate(20, 4));
		secureCoordinate.add(new Coordinate(20, 11));
		secureCoordinate.add(new Coordinate(21, 7));
		Iterator<Coordinate> it = secureCoordinate.iterator();
		while (it.hasNext()) {
			addSecureSector(it.next());
		}
	}
	/**
	 * Adds all Escape Hatch sectors
	 */
	private void addEH() {
		addEscapeHatchSector(new Coordinate(1, 1), 1);
		addEscapeHatchSector(new Coordinate(21, 1), 2);
		addEscapeHatchSector(new Coordinate(21, 12), 3);
		addEscapeHatchSector(new Coordinate(1, 12), 4);
	}
	/**
	 * Adds all spawn sectors 
	 */
	private void addSpawn(){
		addHumanSector(new Coordinate(11,7));
		addAlienSector(new Coordinate(11,5));
	}
	
	@Override
	public GameMap createMap() {
		addDS();
		addSS();
		addEH();
		addSpawn();

		return galileiMap;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((galileiMap == null) ? 0 : galileiMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GalileiCreator other = (GalileiCreator) obj;
		if (galileiMap == null) {
			if (other.galileiMap != null)
				return false;
		} else if (!galileiMap.equals(other.galileiMap))
			return false;
		return true;
	}

}
