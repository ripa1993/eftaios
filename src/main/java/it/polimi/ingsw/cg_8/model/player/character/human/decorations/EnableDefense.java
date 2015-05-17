package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;
/**
 * Allows the player to defend
 * @author Simone
 *
 */
public class EnableDefense extends HumanDecorator {
	/**
	 * Constructor
	 * @param humanBehaviourToBeDecorated behaviour to be decorated
	 */
	public EnableDefense(HumanBehaviour humanBehaviourToBeDecorated) {
		super(humanBehaviourToBeDecorated);
	}
	
	@Override
	public boolean isDefendAllowed() {
		return true;
	}
	
	@Override
	public String toString() {
		return "EnableDefense "+humanBehaviourToBeDecorated.toString();
	}

	
}
