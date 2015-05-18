package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;
/**
 * Allows the player to attack
 * @author Simone
 *
 */
public class EnableAttack extends HumanDecorator {
	/**
	 * Constructor
	 * @param humanBehaviourToBeDecorated behaviour to be decorated
	 */
	public EnableAttack(HumanBehaviour humanBehaviourToBeDecorated){
		super(humanBehaviourToBeDecorated);
	}
	
	@Override
	public boolean isAttackAllowed() {
		return true;
	}
	
	@Override
	public String toString() {
		return "EnableAttack "+humanBehaviourToBeDecorated.toString();
	}
}
