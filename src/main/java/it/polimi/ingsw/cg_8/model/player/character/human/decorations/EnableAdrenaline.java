package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;

/**
 * Allows the human to walk to distance 2 instead of distance 1
 * 
 * @author Simone
 *
 */
public class EnableAdrenaline extends HumanDecorator {
	/**
	 * Constructor
	 * 
	 * @param humanBehaviourToBeDecorated
	 *            behaviour to be decorated
	 */
	public EnableAdrenaline(HumanBehaviour humanBehaviourToBeDecorated) {
		super(humanBehaviourToBeDecorated);
	}

	@Override
	public int getMaxAllowedMovement() {
		return 2;
	}

	@Override
	public String toString() {
		return "EnableAdrenaline "+humanBehaviourToBeDecorated.toString();
	}
	
	

}
