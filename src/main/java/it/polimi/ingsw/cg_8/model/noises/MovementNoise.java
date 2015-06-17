package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * Noise made when player draw a noise card (fake or real)
 * 
 * @author Simone
 * @version 1.0
 */
public class MovementNoise extends Noise {
    /**
     * 
     */
    private static final long serialVersionUID = -9128307181356071002L;

    /**
     * Constructor
     * 
     * @param turnNumber
     *            turn number
     * @param player
     *            player that made noise
     * @param coordinate
     *            coordinate of the player
     */
    public MovementNoise(int turnNumber, Player player, Coordinate coordinate) {
        super(turnNumber, player, coordinate);
    }

    @Override
    public String toString() {
        return "Movement noise :" + super.toString();
    }
}
