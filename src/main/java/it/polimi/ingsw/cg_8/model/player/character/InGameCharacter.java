package it.polimi.ingsw.cg_8.model.player.character;

import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;

public abstract class InGameCharacter {
	private final CharacterCard characterCard;
	
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

	public InGameCharacter(CharacterCard characterCard){
		
		this.characterCard = characterCard;
	}
	

	public abstract boolean isAttackAllowed();
	public abstract boolean isDefendAllowed();
	public abstract int getMaxAllowedMovement();
	public abstract boolean hasToDrawSectorCard();
}
