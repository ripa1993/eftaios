package it.polimi.ingsw.cg_8.model.noises;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public class TeleportNoise extends Noise {

	public TeleportNoise(int turnNumber, Player player, Coordinate coordinate) {
		super(turnNumber, player, coordinate);
	}

	@Override
	public String toString() {
		return "Teleport noise: " + super.toString();
	}

}
