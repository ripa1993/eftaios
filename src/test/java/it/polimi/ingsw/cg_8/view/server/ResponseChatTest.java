package it.polimi.ingsw.cg_8.view.server;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ResponseChatTest {
    ResponseChat response;

    @Before
    public void init() {
        response = new ResponseChat("A", "B");
    }

    @Test
    public void testGetPlayerName() {
        assertEquals("A", response.getPlayerName());
    }

    @Test
    public void testGetMessage() {
        assertEquals("B", response.getMessage());
    }

}
