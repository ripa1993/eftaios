package it.polimi.ingsw.cg_8.model.player.character.human;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;

public class Human extends InGameCharacter {
	private HumanBehaviour currentBehaviour;
	public Human(Player player) {
		super(player);
		this.currentBehaviour=new NormalHuman();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((currentBehaviour == null) ? 0 : currentBehaviour.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		if (currentBehaviour == null) {
			if (other.currentBehaviour != null)
				return false;
		} else if (!currentBehaviour.equals(other.currentBehaviour))
			return false;
		return true;
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

}
