package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;
/**
 * Implementation of Decorator pattern, used to customise the behaviour of a human
 * @author Simone
 *
 */
public abstract class HumanDecorator implements HumanBehaviour {
	/**
	 * Previous behaviour that is going to be decorated
	 */
	protected HumanBehaviour humanBehaviourToBeDecorated;
	/**
	 * Defualt constructor
	 * @param humanBehaviourToBeDecorated behaviour to be decorated
	 */
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
