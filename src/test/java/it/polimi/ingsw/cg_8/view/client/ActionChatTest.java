package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.view.client.actions.ActionChat;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionChatTest {

	@Test
	public void testCorrect() throws NotAValidInput {
		String input = "SAY this is my message";
		ClientAction action = ActionParser.createEvent(input);
		assertEquals(((ActionChat) action).getMessage(), "this is my message");
	}

	@Test(expected = NotAValidInput.class)
	public void testWrong() throws NotAValidInput {
		String input = "SAY";
		ClientAction action = ActionParser.createEvent(input);

	}
}
