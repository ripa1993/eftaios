package it.polimi.ingsw.cg_8.model.player.character.human;

import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;
import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;
import it.polimi.ingsw.cg_8.model.player.character.human.decorations.EnableAdrenaline;
import it.polimi.ingsw.cg_8.model.player.character.human.decorations.EnableAttack;
import it.polimi.ingsw.cg_8.model.player.character.human.decorations.EnableDefense;
import it.polimi.ingsw.cg_8.model.player.character.human.decorations.EnableSedatives;

/**
 * Human character
 * 
 * @author Simone
 * @version 1.0
 */
public class Human extends InGameCharacter {
	/**
	 * Implementation of the strategy pattern, there are two kind of strategy:
	 * {@link NormalHuman Normal} and {@link decorations.HumanDecorator
	 * Decorated} (with special powers)
	 */
	private HumanBehaviour currentBehaviour;

	/**
	 * Constructor for human. By default the human's strategy is the normal one
	 * 
	 * @param characterCard
	 *            human character card
	 */
	public Human(CharacterCard characterCard) {
		super(characterCard);
		this.currentBehaviour = new NormalHuman();
	}

	/**
	 * Decorates the human, allowing the attack
	 */
	public void enableAttack() {
		HumanBehaviour tempBehaviour = new EnableAttack(currentBehaviour);
		setBehaviour(tempBehaviour);
	}

	/**
	 * Decorates the human, allowing the defense
	 */
	public void enableDefend() {
		HumanBehaviour tempBehaviour = new EnableDefense(currentBehaviour);
		setBehaviour(tempBehaviour);
	}

	/**
	 * Decorates the human, using sedatives, allowing to not draw dangerous
	 * sector card
	 */
	public void enableSedatives() {
		HumanBehaviour tempBehaviour = new EnableSedatives(currentBehaviour);
		setBehaviour(tempBehaviour);
	}

	/**
	 * Decorates the human, using adrenaline, allowing to more further distances
	 */
	public void enableAdrenaline() {
		HumanBehaviour tempBehaviour = new EnableAdrenaline(currentBehaviour);
		setBehaviour(tempBehaviour);
	}

	/**
	 * Changes the behaviour of the humna
	 * 
	 * @param hb
	 *            new behaviour
	 */
	public void setBehaviour(HumanBehaviour hb) {
		this.currentBehaviour = hb;
	}

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

	@Override
	public String toString() {
		return "Human";
	}

}
