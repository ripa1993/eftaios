package it.polimi.ingsw.cg_8.controller.playerActions.otherActions;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.controller.playerActions.other.GetAllowedActions;

import org.junit.Test;

public class GetAllowedActionsTest {

    @Test
    public void testPrintActions() {
        String actions = "List of supported commands:\n"
                + "SAY message - sends a chat message to all the other players\n"
                + "CARDS - gives a list of your item cards\n"
                + "COORDINATES - gives a list of reachable sectors from your position\n"
                + "ACTIONS - shows this message\n"
                + "MOVE coordinate - moves your character into target coordinate\n"
                + "DRAW - draws a dangerous sector card"
                + "ATTACK - does an attack in your position\n"
                + "USE [ADRENALINE|ATTACK|TELEPORT|SEDATIVES|SPOTLIGHT] - uses your itemcard\n"
                + "NOISE coordinate - does a fake noise in the target coordinate\n"
                + "END - ends your turn\n"
                + "DISCONNECT - disconnect from the server";
        assertEquals(actions, GetAllowedActions.printActions());
    }

}
