package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.noises.EscapeSectorNoise;
import it.polimi.ingsw.cg_8.model.noises.Noise;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

/**
 * After drawing a Fake Noise Dangerous Sector Card, the player need to
 * comunicate a Coordinate in which will happen a fake noise
 * 
 * @author Simone
 *
 */
public class FakeNoise extends PlayerAction {

	/**
	 * Static method that adds a fake noise to the noise logger
	 * 
	 * @param model
	 *            this game
	 * @param coordinate
	 *            noise coordinates
	 */
	public static void fakeNoise(Model model, Coordinate coordinate) {
		Noise fakeNoise = new EscapeSectorNoise(model.getRoundNumber(), model
				.getPlayers().get(model.getCurrentPlayer()), coordinate);
		model.getNoiseLogger().add(fakeNoise);
	}

}
