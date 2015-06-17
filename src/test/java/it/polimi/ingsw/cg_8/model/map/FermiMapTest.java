package it.polimi.ingsw.cg_8.model.map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidCoordinateException;
import it.polimi.ingsw.cg_8.model.map.creator.FermiCreator;
import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class FermiMapTest {
	
	private GameMap testMap;
	private MapCreator mapCreator;
	
	@Before
	public void init(){
		mapCreator=new FermiCreator();
		testMap = mapCreator.createMap();
	}
	
	// trying to get a null sector
	@Test(expected = NotAValidCoordinateException.class)
	public void testGetSectorByC() throws NotAValidCoordinateException {
		testMap.getSectorByCoordinates(new Coordinate(0,0));
	}
	
	// trying to get a Dangerous Sector
	@Test
	public void testGetSectorByC2() throws NotAValidCoordinateException {
		Sector mSector = testMap.getSectorByCoordinates(new Coordinate(10,3));
		boolean result = false;
		if (mSector instanceof DangerousSector){
			result=true;
		}
		assertTrue(result);

	}
	
	// trying to get an escape hatch sector
	@Test
	public void testGetSectorByC3() throws NotAValidCoordinateException{
		Sector mSector = testMap.getSectorByCoordinates(new Coordinate(9,4));
		boolean result = false;
		if (mSector instanceof EscapeHatchSector){
			result=true;
		}
		assertTrue(result);
				
	}
	
	// trying to get a secure sector
	@Test
	public void testGetSectorByC4() throws NotAValidCoordinateException{
		Sector mSector = testMap.getSectorByCoordinates(new Coordinate(10,1));
		boolean result = false;
		if (mSector instanceof SecureSector){
			result=true;
		}
		assertTrue(result);
		
	}
	
	// trying to get a null sector
	@Test
	public void verifySectorExistance(){
		assertFalse(testMap.verifySectorExistance(new Coordinate(0,4)));
	}
	
	// trying to get a valid sector
	@Test
	public void verifySectorExistance2(){
		assertTrue(testMap.verifySectorExistance(new Coordinate(11,11)));
	}

	// trying to get reachable coordinates for (10,1) with depth=3
	@Test
	public void getReachableCoordinates(){
		Set<Coordinate> reachableCoordinatesFound = testMap.getReachableCoordinates(new Coordinate(10,1), 3);
		Set<Coordinate> reachableCoordinatesReal = new HashSet<Coordinate>();
		reachableCoordinatesReal.add(new Coordinate(9,0));
		reachableCoordinatesReal.add(new Coordinate(10,2));
		reachableCoordinatesReal.add(new Coordinate(10,3));
		reachableCoordinatesReal.add(new Coordinate(9,2));
		reachableCoordinatesReal.add(new Coordinate(11,2));
		reachableCoordinatesReal.add(new Coordinate(9,3));
		reachableCoordinatesReal.add(new Coordinate(11,3));
		reachableCoordinatesReal.add(new Coordinate(12,2));
		reachableCoordinatesReal.add(new Coordinate(12,3));
		assertTrue(reachableCoordinatesFound.equals(reachableCoordinatesReal));
	}
	
	//trying to get an unreachable coordinate for (10,1) with depth=3
	@Test
	public void getReachableCoordinates2(){
		Set<Coordinate> reachableCoordinatesFound = testMap.getReachableCoordinates(new Coordinate(10,1), 3);
		assertFalse(reachableCoordinatesFound.contains(new Coordinate(15,1)));
	}
	
	//trying to get already calculated reachable coordinates
	@Test
	public void getReachableCoordinates3(){
		Set<Coordinate> reachableCoordinatesDummy = testMap.getReachableCoordinates(new Coordinate(10,1), 3);
		Set<Coordinate> reachableCoordinatesFound = testMap.getReachableCoordinates(new Coordinate(10,1), 3);
		
		assertTrue(reachableCoordinatesFound.equals(reachableCoordinatesDummy));
	}
	
}
