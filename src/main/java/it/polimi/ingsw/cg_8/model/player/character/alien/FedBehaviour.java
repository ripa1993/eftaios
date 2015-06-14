package it.polimi.ingsw.cg_8.model.player.character.alien;

/**
 * Fed behaviour, the alien can move a distance of 3 sectors
 * 
 * @author Simone
 * @version 1.0
 */
public class FedBehaviour implements AlienBehaviour {
	/**
	 * Max allowed distance
	 */
	private static final int DISTANCE = 3;

	@Override
	public int getMaxMovementDistance() {
		return DISTANCE;
	}

	@Override
	public String toString() {
		return "FedBehaviour";
	}
}
