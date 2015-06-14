package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_8.view.client.actions.ActionSetName;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionSetNameTest {

	@Test
	public void testCorrect() throws NotAValidInput {
		String input = "NAME this is my name";
		ClientAction action = ActionParser.createEvent(input);
		assertEquals(((ActionSetName) action).getName(), "this is my name");
	}
	
	@Test(expected = NotAValidInput.class)
	public void testWrong() throws NotAValidInput {
		String input = "NAME";
		ClientAction action = ActionParser.createEvent(input);
	}
	

}
