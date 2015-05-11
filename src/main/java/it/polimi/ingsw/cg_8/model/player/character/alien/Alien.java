package it.polimi.ingsw.cg_8.model.player.character.alien;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;

public class Alien extends InGameCharacter {
	private AlienBehaviour currentBehaviour;
	
	public Alien (Player player) {
		super(player);
		this.currentBehaviour = new NormalBehaviour();
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
		Alien other = (Alien) obj;
		if (currentBehaviour == null) {
			if (other.currentBehaviour != null)
				return false;
		} else if (!currentBehaviour.equals(other.currentBehaviour))
			return false;
		return true;
	}

	public void feedAlien(){
		this.currentBehaviour = new FedBehaviour();
	}
	
	public int getMaxAllowedMovement(){
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

	
	
}
