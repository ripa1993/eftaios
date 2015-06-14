package it.polimi.ingsw.cg_8.view.server;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ResponseStateTest {
	ResponseState response;
	@Before
	public void init(){
		response = new ResponseState("A", "B", "C", "D", 0);
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
		assertEquals("C", response.getState());
	}

	@Test
	public void testGetPosition() {
		assertEquals("D", response.getPosition());
	}

	@Test
	public void testGetRoundNumber() {
		assertEquals("0", response.getRoundNumber());
	}

}
