package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;

public class EnableAttack extends HumanDecorator {
	public EnableAttack(HumanBehaviour humanBehaviourToBeDecorated){
		super(humanBehaviourToBeDecorated);
	}
	
	@Override
	public boolean isAttackAllowed() {
		return true;
	}

}
