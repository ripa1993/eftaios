package it.polimi.ingsw.cg_8.model.player.character.human;

public class NormalHuman implements HumanBehaviour {

	// default human behaviour
	
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

}
