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
