package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetAvailableAction;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionGetAvailableActionTest {

	@Test
	public void testCorrect() throws NotAValidInput {
		String input = "ACTIONS";
		ClientAction action = ActionParser.createEvent(input);
		assertTrue(action instanceof ActionGetAvailableAction);
	}

	@Test(expected = NotAValidInput.class)
	public void testException() throws NotAValidInput {
		String input = "ACTIONS nonvalid";
		ClientAction action = ActionParser.createEvent(input);
	}

}
