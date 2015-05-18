package it.polimi.ingsw.cg_8.model.player.character.alien;
/**
 * Normal behaviour, the alien can move a distance of 2 sectors
 * @author Simone
 *
 */
public class NormalBehaviour implements AlienBehaviour {

	@Override
	public int getMaxMovementDistance() {
		return 2;
	}

	@Override
	public String toString() {
		return "NormalBehaviour";
	}

}
