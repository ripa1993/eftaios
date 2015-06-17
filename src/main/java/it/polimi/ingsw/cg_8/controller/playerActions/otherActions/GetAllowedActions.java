package it.polimi.ingsw.cg_8.controller.playerActions.otherActions;

import it.polimi.ingsw.cg_8.controller.playerActions.PlayerAction;

/**
 * This action lists all the available commands in a CLI
 * 
 * @author Simone
 * @version 1.0
 */
public class GetAllowedActions implements PlayerAction {
    /**
     * Constructor
     */
    private GetAllowedActions() {

    }

    /**
     * List of all recognized commands
     * 
     * @param player
     *            requesting player
     * @return list of all recognized commands
     */
    public static String printActions() {
        return "List of supported commands:\n"
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
    }
}
