package it.polimi.ingsw.cg_8.view.server;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ResponsePrivateTest {
	ResponsePrivate response;

	@Before
	public void init() {
		response = new ResponsePrivate("Test");
	}

	@Test
	public void testGetMessage() {
		assertEquals("Test", response.getMessage());
	}

}
