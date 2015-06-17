package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.actions.ActionFakeNoise;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionFakeNoiseTest {

	@Test
	public void testCorrect() throws NotAValidInput {
		String input = "NOISE B12";
		ClientAction action = ActionParser.createEvent(input);
		assertEquals(((ActionFakeNoise) action).getCoordinate(),
		        new Coordinate(1, 11));
	}

	@Test(expected = NotAValidInput.class)
	public void testException() throws NotAValidInput {
		String input = "NOISE nonvalid";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test(expected = NotAValidInput.class)
	public void testException2() throws NotAValidInput {
		String input = "NOISE Z01";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test(expected = NotAValidInput.class)
	public void testException3() throws NotAValidInput {
		String input = "NOISE B01 nope";
		ClientAction action = ActionParser.createEvent(input);
	}

	@Test(expected = NotAValidInput.class)
	public void testException4() throws NotAValidInput {
		String input = "NOISE";
		ClientAction action = ActionParser.createEvent(input);
	}

}
