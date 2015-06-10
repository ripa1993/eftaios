package it.polimi.ingsw.cg_8.controller.playerActions.otherActions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SendChatMessageTest {

	@Test
	public void testGetMessage() {
		SendChatMessage msg = new SendChatMessage("ciao");
		assertEquals("ciao", msg.getMessage());
	}
}
