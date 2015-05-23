package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDrawCard;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionDrawCardTest {

	@Test
	public void testCorrect() throws NotAValidInput {
		String input = "DRAW";
		ClientAction action = ActionParser.createEvent(input);
		assertTrue(action instanceof ActionDrawCard);
	}

	@Test(expected = NotAValidInput.class)
	public void testException() throws NotAValidInput {
		String input = "DRAW nonvalid";
		ClientAction action = ActionParser.createEvent(input);
	}
}
