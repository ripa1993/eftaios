package it.polimi.ingsw.cg_8.view.server;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import org.junit.Before;
import org.junit.Test;

public class ResponseMapTest {
    ResponseMap response;

    @Before
    public void init() {
        response = new ResponseMap(GameMapName.FERMI);
    }

    @Test
    public void testGetMapName() {
        assertEquals(GameMapName.FERMI, response.getMapName());
    }

}
