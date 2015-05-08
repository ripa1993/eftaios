package it.polimi.ingsw.cg_8.model.map;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.map.creator.GalvaniCreator;
import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.SectorType;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class GalvaniMapTest {

	private GameMap testMap;
	private MapCreator mapCreator;
	
	@Before
	public void init(){
		mapCreator=new GalvaniCreator();
		testMap = mapCreator.createMap();
	}
	
	// trying to get a null sector
	@Test
	public void testGetSectorByC() {
		assertEquals(testMap.getSectorByCoordinates(new Coordinate(0,0)) , null);
	}
	
	// trying to get a Dangerous Sector
	@Test
	public void testGetSectorByC2() {
		Sector mSector = testMap.getSectorByCoordinates(new Coordinate(10,0));
		assertEquals(SectorType.DANGEROUS_SECTOR, mSector.getSectorType());

	}
	
	// trying to get a eh sector
	@Test
	public void testGetSectorByC3(){
		Sector mSector = testMap.getSectorByCoordinates(new Coordinate(5,0));
		assertEquals(SectorType.EH_SECTOR, mSector.getSectorType());
				
	}
	
	// trying to get a secure sector
	@Test
	public void testGetSectorByC4(){
		Sector mSector = testMap.getSectorByCoordinates(new Coordinate(6,1));
		assertEquals(SectorType.SECURE_SECTOR, mSector.getSectorType());
	}
	
	// trying to get a null sector
	@Test
	public void verifySectorExistance(){
		assertFalse(testMap.verifySectorExistance(new Coordinate(7,0)));
	}
	
	// trying to get a valid sector
	@Test
	public void verifySectorExistance2(){
		assertTrue(testMap.verifySectorExistance(new Coordinate(11,10)));
	}

	// trying to get reachable coordinates for (10,0) with depth=1
	@Test
	public void getReachableCoordinates(){
		Set<Coordinate> reachableCoordinatesFound = testMap.getReachableCoordinates(new Coordinate(10,0), 1);
		Set<Coordinate> reachableCoordinatesReal = new HashSet<Coordinate>();
		reachableCoordinatesReal.add(new Coordinate(9,0));
		reachableCoordinatesReal.add(new Coordinate(11,0));
		assertTrue(reachableCoordinatesFound.equals(reachableCoordinatesReal));
	}
	
	//trying to get an unreachable coordinate for (10,0) with depth=3
	@Test
	public void getReachableCoordinates2(){
		Set<Coordinate> reachableCoordinatesFound = testMap.getReachableCoordinates(new Coordinate(10,0), 3);
		assertFalse(reachableCoordinatesFound.contains(new Coordinate(15,1)));
	}
}
