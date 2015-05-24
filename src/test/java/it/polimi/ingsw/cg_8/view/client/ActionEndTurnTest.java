package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.view.client.actions.ActionEndTurn;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionEndTurnTest {

	@Test
	public void testCorrect() throws NotAValidInput {
		String input = "END";
		ClientAction action = ActionParser.createEvent(input);
		assertTrue(action instanceof ActionEndTurn);
	}

	@Test(expected = NotAValidInput.class)
	public void testException() throws NotAValidInput {
		String input = "END nonvalid";
		ClientAction action = ActionParser.createEvent(input);
	}
}
