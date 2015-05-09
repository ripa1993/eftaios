package it.polimi.ingsw.cg_8.model.player.character.human;

import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;

public class Human extends InGameCharacter {
	
	private HumanBehaviour currentBehaviour;

	@Override
	public boolean isAttackAllowed() {
		return currentBehaviour.isAttackAllowed();
	}

	@Override
	public boolean isDefendAllowed() {
		return currentBehaviour.isDefendAllowed();
	}

	@Override
	public int getMaxAllowedMovement() {
		return currentBehaviour.getMaxAllowedMovement();
	}

	@Override
	public boolean hasToDrawSectorCard() {
		return currentBehaviour.hasToDrawSectorCard();
	}

}
