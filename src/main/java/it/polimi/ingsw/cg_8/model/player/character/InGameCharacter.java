package it.polimi.ingsw.cg_8.model.player.character;

public abstract class InGameCharacter {
	public abstract boolean isAttackAllowed();
	public abstract boolean isDefendAllowed();
	public abstract int getMaxAllowedMovement();
}
