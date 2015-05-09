package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;

public abstract class HumanDecorator implements HumanBehaviour {
	
	protected HumanBehaviour humanBehaviourToBeDecorated;
	public HumanDecorator (HumanBehaviour humanBehaviourToBeDecorated){
		this.humanBehaviourToBeDecorated = humanBehaviourToBeDecorated;
	}


	@Override
	public boolean isAttackAllowed() {
		return humanBehaviourToBeDecorated.isAttackAllowed();
	}

	@Override
	public boolean isDefendAllowed() {
		return humanBehaviourToBeDecorated.isDefendAllowed();
	}

	@Override
	public int getMaxAllowedMovement() {
		return humanBehaviourToBeDecorated.getMaxAllowedMovement();
	}
	@Override
	public boolean hasToDrawSectorCard(){
		return humanBehaviourToBeDecorated.hasToDrawSectorCard();
	}

}
