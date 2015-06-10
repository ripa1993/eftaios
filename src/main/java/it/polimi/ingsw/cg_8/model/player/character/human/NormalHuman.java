package it.polimi.ingsw.cg_8.model.player.character.human;

/**
 * Default {@link Human} {@link HumanBehaviour Behaviour}
 * 
 * @author Simone
 * @version 1.0
 */
public class NormalHuman implements HumanBehaviour {

	@Override
	public boolean isAttackAllowed() {
		return false;
	}

	@Override
	public boolean isDefendAllowed() {
		return false;
	}

	@Override
	public int getMaxAllowedMovement() {
		return 1;
	}

	@Override
	public boolean hasToDrawSectorCard() {
		return true;
	}

	@Override
	public String toString() {
		return "NormalHuman";
	}

}
