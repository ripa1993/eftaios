package it.polimi.ingsw.cg_8.model.player.character.alien;
/**
 * Fed behaviour, the alien can move a distance of 3 sectors
 * @author Simone
 *
 */
public class FedBehaviour implements AlienBehaviour {

	

	@Override
	public int getMaxMovementDistance() {
		return 3;
	}

	@Override
	public String toString() {
		return "FedBehaviour";
	}
}
