package it.polimi.ingsw.cg_8.model.map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidCoordinateException;
import it.polimi.ingsw.cg_8.model.map.creator.GalileiCreator;
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

public class GalileiMapTest {

    private GameMap testMap;
    private MapCreator mapCreator;

    @Before
    public void init() {
        mapCreator = new GalileiCreator();
        testMap = mapCreator.createMap();
    }

    // trying to get a null sector
    @Test(expected = NotAValidCoordinateException.class)
    public void testGetSectorByC() throws NotAValidCoordinateException {
        testMap.getSectorByCoordinates(new Coordinate(0, 0));
    }

    // trying to get a Dangerous Sector
    @Test
    public void testGetSectorByC2() throws NotAValidCoordinateException {
        Sector mSector = testMap.getSectorByCoordinates(new Coordinate(0, 1));
        boolean result = false;
        if (mSector instanceof DangerousSector) {
            result = true;
        }
        assertTrue(result);
    }

    // trying to get a eh sector
    @Test
    public void testGetSectorByC3() throws NotAValidCoordinateException {
        Sector mSector = testMap.getSectorByCoordinates(new Coordinate(1, 1));
        boolean result = false;
        if (mSector instanceof EscapeHatchSector) {
            result = true;
        }
        assertTrue(result);
    }

    // trying to get a secure sector
    @Test
    public void testGetSectorByC4() throws NotAValidCoordinateException {
        Sector mSector = testMap.getSectorByCoordinates(new Coordinate(2, 0));
        boolean result = false;
        if (mSector instanceof SecureSector) {
            result = true;
        }
        assertTrue(result);
    }

    // trying to get a null sector
    @Test
    public void verifySectorExistance() {
        assertFalse(testMap.verifySectorExistance(new Coordinate(0, 6)));
    }

    // trying to get a valid sector
    @Test
    public void verifySectorExistance2() {
        assertTrue(testMap.verifySectorExistance(new Coordinate(11, 11)));
    }

    // trying to get reachable coordinates for (10,1) with depth=1
    @Test
    public void getReachableCoordinates() {
        Set<Coordinate> reachableCoordinatesFound = testMap
                .getReachableCoordinates(new Coordinate(2, 6), 1);
        Set<Coordinate> reachableCoordinatesReal = new HashSet<Coordinate>();
        reachableCoordinatesReal.add(new Coordinate(1, 5));
        reachableCoordinatesReal.add(new Coordinate(2, 5));
        reachableCoordinatesReal.add(new Coordinate(2, 7));
        assertTrue(reachableCoordinatesFound.equals(reachableCoordinatesReal));
    }

    // trying to get an unreachable coordinate for (10,1) with depth=3
    @Test
    public void getReachableCoordinates2() {
        Set<Coordinate> reachableCoordinatesFound = testMap
                .getReachableCoordinates(new Coordinate(10, 1), 3);
        assertFalse(reachableCoordinatesFound.contains(new Coordinate(17, 1)));
    }

}
