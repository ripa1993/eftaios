package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * Noise made when a player tries to escape
 * 
 * @author Simone
 * @version 1.0
 */
public class EscapeSectorNoise extends Noise {
    /**
	 * 
	 */
    private static final long serialVersionUID = 4362675785601722848L;

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
    public EscapeSectorNoise(int turnNumber, Player player,
            Coordinate coordinate) {
        super(turnNumber, player, coordinate);
    }

    @Override
    public String toString() {
        return "Escape sector noise: " + super.toString();

    }
}
