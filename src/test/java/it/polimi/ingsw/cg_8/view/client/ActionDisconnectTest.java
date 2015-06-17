package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDisconnect;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionDisconnectTest {

    @Test
    public void testCorrect() throws NotAValidInput {
        String input = "DISCONNECT";
        ClientAction action = ActionParser.createEvent(input);
        assertTrue(action instanceof ActionDisconnect);
    }

    @Test(expected = NotAValidInput.class)
    public void testException() throws NotAValidInput {
        String input = "DISCONNECT nonvalid";
        ClientAction action = ActionParser.createEvent(input);
    }

    @Test(expected = NotAValidInput.class)
    public void testNotACommand() throws NotAValidInput {
        String input = "CIAO";
        ClientAction action = ActionParser.createEvent(input);
    }
}
