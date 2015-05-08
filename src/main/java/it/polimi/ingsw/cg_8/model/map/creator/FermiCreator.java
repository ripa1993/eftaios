package it.polimi.ingsw.cg_8.model.map.creator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import it.polimi.ingsw.cg_8.model.map.FermiMap;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.SectorType;

public class FermiCreator extends MapCreator {

	GameMap fermiMap;

	public FermiCreator() {
		super(new FermiMap());
		fermiMap = this.getGm();
	}

	private void addDS(){
		Set<Coordinate> dangerousCoordinate = new HashSet<Coordinate>();
		dangerousCoordinate.add(new Coordinate(8,6));
		dangerousCoordinate.add(new Coordinate(8,8));
		dangerousCoordinate.add(new Coordinate(8,11));
		dangerousCoordinate.add(new Coordinate(9,2));
		dangerousCoordinate.add(new Coordinate(10,3));
		dangerousCoordinate.add(new Coordinate(10,12));
		dangerousCoordinate.add(new Coordinate(11,2));
		dangerousCoordinate.add(new Coordinate(11,3));
		dangerousCoordinate.add(new Coordinate(12,1));
		dangerousCoordinate.add(new Coordinate(12,3));
		dangerousCoordinate.add(new Coordinate(13,2));
		dangerousCoordinate.add(new Coordinate(13,6));
		dangerousCoordinate.add(new Coordinate(13,11));
		dangerousCoordinate.add(new Coordinate(14,9));
		dangerousCoordinate.add(new Coordinate(15,10));
		Iterator<Coordinate> it = dangerousCoordinate.iterator();
		while(it.hasNext()){
			addDangerousSector(it.next());
		}
	}
	
	private void addSS(){
		addColumn(SectorType.SECURE_SECTOR, 7, 9, 10);
		addColumn(SectorType.SECURE_SECTOR, 9, 6, 7);
		addColumn(SectorType.SECURE_SECTOR, 9, 9, 11);
		addColumn(SectorType.SECURE_SECTOR, 10, 1, 2);
		addColumn(SectorType.SECURE_SECTOR, 11, 4, 7);
		addColumn(SectorType.SECURE_SECTOR, 11, 10, 11);
		addColumn(SectorType.SECURE_SECTOR, 13, 9, 10);
		addColumn(SectorType.SECURE_SECTOR, 14, 5, 6);
		Set<Coordinate> secureCoordinate = new HashSet<Coordinate>();
		secureCoordinate.add(new Coordinate(8,5));
		secureCoordinate.add(new Coordinate(8,9));
		secureCoordinate.add(new Coordinate(9,3));
		secureCoordinate.add(new Coordinate(10,7));
		secureCoordinate.add(new Coordinate(10,10));
		secureCoordinate.add(new Coordinate(12,2));
		secureCoordinate.add(new Coordinate(12,7));
		secureCoordinate.add(new Coordinate(12,10));
		secureCoordinate.add(new Coordinate(12,12));
		secureCoordinate.add(new Coordinate(13,3));
		secureCoordinate.add(new Coordinate(13,7));
		secureCoordinate.add(new Coordinate(14,8));
		secureCoordinate.add(new Coordinate(14,11));
		secureCoordinate.add(new Coordinate(15,9));
		Iterator<Coordinate> it = secureCoordinate.iterator();
		while(it.hasNext()){
			addSecureSector(it.next());
		}
	}
	
	private void addEH(){
		addEscapeHatchSector(new Coordinate(9,4), 1);
		addEscapeHatchSector(new Coordinate(13,4), 2);
		addEscapeHatchSector(new Coordinate(9,0), 3);
		addEscapeHatchSector(new Coordinate(13,0), 4);
	}
	
	private void addSpawn(){
		addHumanSector(new Coordinate(11, 9));
		addAlienSector(new Coordinate(11,8));
	}
	@Override
	public GameMap createMap() {
		addDS();
		addSS();
		addEH();
		addSpawn();

		return fermiMap;
	}

}
