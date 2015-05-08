package it.polimi.ingsw.cg_8.model.map;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.map.creator.FermiCreator;
import it.polimi.ingsw.cg_8.model.map.creator.MapCreator;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.model.sectors.Sector;
import it.polimi.ingsw.cg_8.model.sectors.normal.DangerousSector;
import it.polimi.ingsw.cg_8.model.sectors.normal.SecureSector;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;

import org.hamcrest.Matcher;
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
	
	@Test
	public void testGetSectorByC() {
		assertEquals(testMap.getSectorByCoordinates(new Coordinate(0,0)) , null);
	}
	
	@Test
	public void testGetSectorByC2() {
		Sector mSector = testMap.getSectorByCoordinates(new Coordinate(10,3));
		int result = 0;
		if (mSector instanceof DangerousSector){
			result = 1;
		}
		assertEquals(1, result);

	}
	
	@Test
	public void testGetSectorByC3(){
		Sector mSector = testMap.getSectorByCoordinates(new Coordinate(9,4));
		int result = 0;
		if (mSector instanceof EscapeHatchSector){
			result = 1;
		}
		assertEquals(1, result);

				
	}
	
	@Test
	public void testGetSectorByC4(){
		Sector mSector = testMap.getSectorByCoordinates(new Coordinate(10,1));
		int result = 0;
		if (mSector instanceof SecureSector){
			result = 1;
		}
		assertEquals(1, result);
	}

}
