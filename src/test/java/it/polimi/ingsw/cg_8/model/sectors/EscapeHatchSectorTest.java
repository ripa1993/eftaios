package it.polimi.ingsw.cg_8.model.sectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.model.sectors.special.escapehatch.EscapeHatchSector;

import org.junit.Before;
import org.junit.Test;

public class EscapeHatchSectorTest {

    EscapeHatchSector ehs;

    @Before
    public void init() {
        ehs = new EscapeHatchSector(10, 20);
    }

    @Test
    public void testGetX() {

        assertEquals(10, ehs.getX());
    }

    @Test
    public void testGetY() {

        assertEquals(20, ehs.getY());
    }

    @Test
    public void testAllowEscape() {

        assertTrue(ehs.allowEscape());
    }

    @Test
    public void testAllowEscape2() {

        assertFalse(ehs.allowEscape() == ehs.allowEscape());
    }

}
