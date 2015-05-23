package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetReachableCoordinates;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionGetReachableCoordinatesTest {

	@Test
	public void testCorrect() throws NotAValidInput {
		String input = "COORDINATES";
		ClientAction action = ActionParser.createEvent(input);
		assertTrue(action instanceof ActionGetReachableCoordinates);
	}

	@Test(expected = NotAValidInput.class)
	public void testException() throws NotAValidInput {
		String input = "COORDINATES nonvalid";
		ClientAction action = ActionParser.createEvent(input);
	}

}
