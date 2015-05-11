package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;

public class EnableSedatives extends HumanDecorator {
	public EnableSedatives(HumanBehaviour humanBehaviourToBeDecorated){
		super(humanBehaviourToBeDecorated);
		
	}
	@Override
	public boolean hasToDrawSectorCard(){
		return false;
	}
}
