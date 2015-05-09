package it.polimi.ingsw.cg_8.model.player.character.human;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;
import it.polimi.ingsw.cg_8.model.player.character.human.decorations.EnableAdrenaline;
import it.polimi.ingsw.cg_8.model.player.character.human.decorations.EnableAttack;
import it.polimi.ingsw.cg_8.model.player.character.human.decorations.EnableDefense;
import it.polimi.ingsw.cg_8.model.player.character.human.decorations.EnableSedatives;

public class Human extends InGameCharacter {
	private HumanBehaviour currentBehaviour;

	public Human(Player player) {
		super(player);
		this.currentBehaviour = new NormalHuman();
	}

	public void enableAttack() {
		HumanBehaviour tempBehaviour = new EnableAttack(currentBehaviour);
		setBehaviour(tempBehaviour);
	}

	public void enableDefend() {
		HumanBehaviour tempBehaviour = new EnableDefense(currentBehaviour);
		setBehaviour(tempBehaviour);
	}

	public void enableSedatives() {
		HumanBehaviour tempBehaviour = new EnableSedatives(currentBehaviour);
		setBehaviour(tempBehaviour);
	}

	public void enableAdrenaline() {
		HumanBehaviour tempBehaviour = new EnableAdrenaline(currentBehaviour);
		setBehaviour(tempBehaviour);
	}

	private void setBehaviour(HumanBehaviour hb) {
		this.currentBehaviour = hb;
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
