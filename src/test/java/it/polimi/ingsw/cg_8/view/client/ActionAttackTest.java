package it.polimi.ingsw.cg_8.view.client;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.view.client.actions.ActionAttack;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import org.junit.Test;

public class ActionAttackTest {

    @Test
    public void testCorrect() throws NotAValidInput {
        String input = "ATTACK";
        ClientAction action = ActionParser.createEvent(input);
        assertTrue(action instanceof ActionAttack);
    }

    @Test(expected = NotAValidInput.class)
    public void testException() throws NotAValidInput {
        String input = "ATTACK nonvalid";
        ClientAction action = ActionParser.createEvent(input);
    }

    @Test(expected = NotAValidInput.class)
    public void testNothingToParse() throws NotAValidInput {
        String input = "";
        ClientAction action = ActionParser.createEvent(input);
    }

}
