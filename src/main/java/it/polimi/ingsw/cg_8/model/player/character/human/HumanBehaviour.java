package it.polimi.ingsw.cg_8.model.player.character.human;

public interface HumanBehaviour {
	public abstract boolean isAttackAllowed();
	public abstract boolean isDefendAllowed();
	public abstract int getMaxAllowedMovement();
	public abstract boolean hasToDrawSectorCard();
}
