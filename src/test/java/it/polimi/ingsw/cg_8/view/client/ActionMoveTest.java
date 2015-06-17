package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.assertEquals;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.actions.ActionMove;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionMoveTest {

    @Test
    public void testCorrect() throws NotAValidInput {
        String input = "MOVE B12";
        ClientAction action = ActionParser.createEvent(input);
        assertEquals(((ActionMove) action).getCoordinate(), new Coordinate(1,
                11));
    }

    @Test(expected = NotAValidInput.class)
    public void testException() throws NotAValidInput {
        String input = "MOVE nonvalid";
        ClientAction action = ActionParser.createEvent(input);
    }

    @Test(expected = NotAValidInput.class)
    public void testException2() throws NotAValidInput {
        String input = "MOVE Z01";
        ClientAction action = ActionParser.createEvent(input);
    }

    @Test(expected = NotAValidInput.class)
    public void testException3() throws NotAValidInput {
        String input = "MOVE B01 nope";
        ClientAction action = ActionParser.createEvent(input);
    }

    @Test(expected = NotAValidInput.class)
    public void testException4() throws NotAValidInput {
        String input = "MOVE";
        ClientAction action = ActionParser.createEvent(input);
    }

}
