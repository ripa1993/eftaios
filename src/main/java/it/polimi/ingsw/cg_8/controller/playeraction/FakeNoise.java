package it.polimi.ingsw.cg_8.controller.playeraction;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.noises.MovementNoise;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * After drawing a Fake Noise Dangerous Sector Card, the player need to
 * comunicate a Coordinate in which will happen a fake noise
 * 
 * @author Simone
 * @version 1.0
 */
public class FakeNoise implements PlayerAction {

    /**
     * Static method that adds a fake noise to the noise logger
     * 
     * @param model
     *            this game
     * @param coordinate
     *            noise coordinates
     */
    public static void fakeNoise(Model model, Coordinate coordinate) {
        Noise fakeNoise = new MovementNoise(model.getRoundNumber(), model
                .getPlayers().get(model.getCurrentPlayer()), coordinate);
        model.addNoise(fakeNoise);
    }

}
