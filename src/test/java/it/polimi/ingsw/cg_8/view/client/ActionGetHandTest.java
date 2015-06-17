package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.view.client.actions.ActionGetHand;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionGetHandTest {

    @Test
    public void testCorrect() throws NotAValidInput {
        String input = "CARDS";
        ClientAction action = ActionParser.createEvent(input);
        assertTrue(action instanceof ActionGetHand);
    }

    @Test(expected = NotAValidInput.class)
    public void testException() throws NotAValidInput {
        String input = "CARDS nonvalid";
        ClientAction action = ActionParser.createEvent(input);
    }

}
