package it.polimi.ingsw.cg_8.model.player.character;

import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;

/**
 * Character that can be played by a
 * {@link it.polimi.ingsw.cg_8.model.player.Player Player}. It is extended by
 * {@link alien.Alien Alien} and {@link human.Human Human}
 * 
 * @author Simone
 *
 */
public abstract class InGameCharacter {
	/**
	 * Reference to the character card related to the current character
	 */
	private final CharacterCard characterCard;

	/**
	 * Constructor for InGameCharacter
	 * 
	 * @param characterCard
	 *            character card
	 */
	public InGameCharacter(CharacterCard characterCard) {
		this.characterCard = characterCard;
	}

	/**
	 * @return true, if the attack is allowed<br>
	 *         false, if the attack is denied
	 */
	public abstract boolean isAttackAllowed();

	/**
	 * @return true, if the defense is allowed<br>
	 *         false, if the defense is denied
	 */
	public abstract boolean isDefendAllowed();

	/**
	 * @return max distance that the character can walk
	 */
	public abstract int getMaxAllowedMovement();

	/**
	 * 
	 * @return true, if the player has to draw a sector card<br>
	 *         false, if the player has not to draw a sector card
	 */
	public abstract boolean hasToDrawSectorCard();
	/**
	 * Getter for character card
	 * @return character card owned by this character
	 */
	public CharacterCard getCharacterCard() {
		return characterCard;
	}

	@Override
	public String toString() {
		return "InGameCharacter [characterCard=" + characterCard + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((characterCard == null) ? 0 : characterCard.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InGameCharacter other = (InGameCharacter) obj;
		if (characterCard == null) {
			if (other.characterCard != null)
				return false;
		} else if (!characterCard.equals(other.characterCard))
			return false;
		return true;
	}
}
