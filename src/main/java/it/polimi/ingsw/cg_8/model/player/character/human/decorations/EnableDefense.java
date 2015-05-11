package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;

public class EnableDefense extends HumanDecorator {

	public EnableDefense(HumanBehaviour humanBehaviourToBeDecorated) {
		super(humanBehaviourToBeDecorated);
	}
	
	@Override
	public boolean isDefendAllowed() {
		return true;
	}

	
}
