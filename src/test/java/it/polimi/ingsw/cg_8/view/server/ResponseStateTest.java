package it.polimi.ingsw.cg_8.view.server;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class ResponseStateTest {
    ResponseState response;

    @Before
    public void init() {
        response = new ResponseState("A", "B", "D", new Coordinate(1, 1), 0);
    }

    @Test
    public void testGetPlayerName() {
        assertEquals("A", response.getPlayerName());
    }

    @Test
    public void testGetCharacter() {
        assertEquals("B", response.getCharacter());
    }

    @Test
    public void testGetState() {
        assertEquals("D", response.getState());
    }

    @Test
    public void testGetPosition() {
        assertEquals(new Coordinate(1, 1), response.getPosition());
    }

    @Test
    public void testGetRoundNumber() {
        assertEquals("0", response.getRoundNumber());
    }

}
