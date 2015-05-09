package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;

public class EnableAdrenaline extends HumanDecorator {

	public EnableAdrenaline(HumanBehaviour humanBehaviourToBeDecorated) {
		super(humanBehaviourToBeDecorated);
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
		return 2;
	}
	@Override
	public boolean hasToDrawSectorCard(){
		return humanBehaviourToBeDecorated.hasToDrawSectorCard();
	}

}
