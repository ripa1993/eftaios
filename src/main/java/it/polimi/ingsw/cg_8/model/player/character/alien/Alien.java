package it.polimi.ingsw.cg_8.model.player.character.alien;

import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;
import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;

/**
 * Alien character
 * 
 * @author Simone
 *
 */
public class Alien extends InGameCharacter {
	/**
	 * Implementation of strategy pattern. Current alien behaviour. The
	 * {@link AlienBehaviour Behaviour} can be {@link NormalBehaviour Normal} or
	 * {@link FedBehaviour Fed}
	 */
	private AlienBehaviour currentBehaviour;

	/**
	 * Constructor. By default the strategy is the {@link NormalBehaviour
	 * Normal} one.
	 * 
	 * @param characterCard
	 *            alien character card
	 */
	public Alien(CharacterCard characterCard) {
		super(characterCard);
		this.currentBehaviour = new NormalBehaviour();
	}

	/**
	 * Changes the current alien behaviour from {@link NormalBehaviour Normal}
	 * to {@link FedBehaviour Fed}
	 */
	public void feedAlien() {
		this.currentBehaviour = new FedBehaviour();
	}

	@Override
	public int getMaxAllowedMovement() {
		return currentBehaviour.getMaxMovementDistance();
	}

	@Override
	public boolean isAttackAllowed() {
		return true;
	}

	@Override
	public boolean isDefendAllowed() {
		return false;
	}

	@Override
	public boolean hasToDrawSectorCard() {
		return true;
	}

	

	@Override
	public String toString() {
		return "Alien [currentBehaviour=" + currentBehaviour
				+ ", getMaxAllowedMovement()=" + getMaxAllowedMovement()
				+ ", isAttackAllowed()=" + isAttackAllowed()
				+ ", isDefendAllowed()=" + isDefendAllowed()
				+ ", hasToDrawSectorCard()=" + hasToDrawSectorCard() + "]";
	}

}
