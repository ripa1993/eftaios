package it.polimi.ingsw.cg_8.model.map.creator;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import it.polimi.ingsw.cg_8.model.map.GalvaniMap;
import it.polimi.ingsw.cg_8.model.map.GameMap;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.SectorType;


public class GalvaniCreator extends MapCreator {

	GameMap galvaniMap;

	public GalvaniCreator() {
		super(new GalvaniMap());
		galvaniMap = this.getGm();
	}
	
	private void addDS(){
		addColumn(SectorType.DANGEROUS_SECTOR, 0, 2, 6);
		addColumn(SectorType.DANGEROUS_SECTOR, 0, 8, 11);
		addColumn(SectorType.DANGEROUS_SECTOR, 2, 3, 10);
		addColumn(SectorType.DANGEROUS_SECTOR, 4, 4, 9);
		addColumn(SectorType.DANGEROUS_SECTOR, 5, 9, 10);
		addColumn(SectorType.DANGEROUS_SECTOR, 6, 5, 8);
		addColumn(SectorType.DANGEROUS_SECTOR, 7, 1, 2);
		addColumn(SectorType.DANGEROUS_SECTOR, 8, 1, 2);
		addColumn(SectorType.DANGEROUS_SECTOR, 8, 6, 7);
		addColumn(SectorType.DANGEROUS_SECTOR, 9, 2, 3);
		addColumn(SectorType.DANGEROUS_SECTOR, 12, 0, 1);
		addColumn(SectorType.DANGEROUS_SECTOR, 14, 6, 7);
		addColumn(SectorType.DANGEROUS_SECTOR, 15, 1, 2);
		addColumn(SectorType.DANGEROUS_SECTOR, 16, 2, 3);
		addColumn(SectorType.DANGEROUS_SECTOR, 16, 5, 8);
		addColumn(SectorType.DANGEROUS_SECTOR, 17, 2, 3);
		addColumn(SectorType.DANGEROUS_SECTOR, 17, 12, 13);
		addColumn(SectorType.DANGEROUS_SECTOR, 18, 0, 1);
		addColumn(SectorType.DANGEROUS_SECTOR, 18, 3, 9);
		addColumn(SectorType.DANGEROUS_SECTOR, 18, 12, 13);
		addColumn(SectorType.DANGEROUS_SECTOR, 19, 0, 1);
		addColumn(SectorType.DANGEROUS_SECTOR, 19, 10, 11);
		addColumn(SectorType.DANGEROUS_SECTOR, 20, 2, 3);
		addColumn(SectorType.DANGEROUS_SECTOR, 20, 9, 11);
		addColumn(SectorType.DANGEROUS_SECTOR, 21, 1, 3);
		addColumn(SectorType.DANGEROUS_SECTOR, 22, 2, 12);
		Set<Coordinate> dangerousCoordinate = new HashSet<Coordinate>();
		dangerousCoordinate.add(new Coordinate(1,1));
		dangerousCoordinate.add(new Coordinate(1,11));
		dangerousCoordinate.add(new Coordinate(2,1));
		dangerousCoordinate.add(new Coordinate(2,11));
		dangerousCoordinate.add(new Coordinate(3,0));
		dangerousCoordinate.add(new Coordinate(3,2));
		dangerousCoordinate.add(new Coordinate(3,11));
		dangerousCoordinate.add(new Coordinate(4,0));
		dangerousCoordinate.add(new Coordinate(4,2));
		dangerousCoordinate.add(new Coordinate(4,11));
		dangerousCoordinate.add(new Coordinate(5,1));
		dangerousCoordinate.add(new Coordinate(5,3));
		dangerousCoordinate.add(new Coordinate(6,3));
		dangerousCoordinate.add(new Coordinate(6,10));
		dangerousCoordinate.add(new Coordinate(6,12));
		dangerousCoordinate.add(new Coordinate(7,8));
		dangerousCoordinate.add(new Coordinate(7,10));
		dangerousCoordinate.add(new Coordinate(7,12));
		dangerousCoordinate.add(new Coordinate(8,4));
		dangerousCoordinate.add(new Coordinate(8,9));
		dangerousCoordinate.add(new Coordinate(8,11));
		dangerousCoordinate.add(new Coordinate(8,13));
		dangerousCoordinate.add(new Coordinate(9,0));
		dangerousCoordinate.add(new Coordinate(9,4));
		dangerousCoordinate.add(new Coordinate(9,7));
		dangerousCoordinate.add(new Coordinate(9,9));
		dangerousCoordinate.add(new Coordinate(9,13));
		dangerousCoordinate.add(new Coordinate(10,0));
		dangerousCoordinate.add(new Coordinate(10,3));
		dangerousCoordinate.add(new Coordinate(10,5));
		dangerousCoordinate.add(new Coordinate(10,10));
		dangerousCoordinate.add(new Coordinate(10,12));
		dangerousCoordinate.add(new Coordinate(11,0));
		dangerousCoordinate.add(new Coordinate(11,4));
		dangerousCoordinate.add(new Coordinate(11,8));
		dangerousCoordinate.add(new Coordinate(11,10));
		dangerousCoordinate.add(new Coordinate(11,12));
		dangerousCoordinate.add(new Coordinate(12,3));
		dangerousCoordinate.add(new Coordinate(12,5));
		dangerousCoordinate.add(new Coordinate(12,10));
		dangerousCoordinate.add(new Coordinate(13,0));
		dangerousCoordinate.add(new Coordinate(13,3));
		dangerousCoordinate.add(new Coordinate(13,5));
		dangerousCoordinate.add(new Coordinate(13,7));
		dangerousCoordinate.add(new Coordinate(13,9));
		dangerousCoordinate.add(new Coordinate(13,11));
		dangerousCoordinate.add(new Coordinate(13,13));
		dangerousCoordinate.add(new Coordinate(14,1));
		dangerousCoordinate.add(new Coordinate(14,9));
		dangerousCoordinate.add(new Coordinate(14,11));
		dangerousCoordinate.add(new Coordinate(15,4));
		dangerousCoordinate.add(new Coordinate(15,8));
		dangerousCoordinate.add(new Coordinate(15,10));
		dangerousCoordinate.add(new Coordinate(15,13));
		dangerousCoordinate.add(new Coordinate(16,0));
		dangerousCoordinate.add(new Coordinate(16,10));
		dangerousCoordinate.add(new Coordinate(17,0));
		dangerousCoordinate.add(new Coordinate(17,9));
		dangerousCoordinate.add(new Coordinate(19,13));
		dangerousCoordinate.add(new Coordinate(20,0));
		dangerousCoordinate.add(new Coordinate(20,13));
		dangerousCoordinate.add(new Coordinate(21,7));
		dangerousCoordinate.add(new Coordinate(21,12));
		Iterator<Coordinate> it = dangerousCoordinate.iterator();
		while(it.hasNext()){
			addDangerousSector(it.next());
		}

	}
	private void addSS(){
		
	}
	private void addEH(){
		
	}
	private void addSpawn(){
		
	}
	@Override
	public GameMap createMap() {
		addDS();
		addSS();
		addEH();
		addSpawn();
		
		return galvaniMap;
	}

}
