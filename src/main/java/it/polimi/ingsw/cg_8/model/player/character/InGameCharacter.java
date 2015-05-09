package it.polimi.ingsw.cg_8.model.player.character;

import it.polimi.ingsw.cg_8.model.player.Player;

public abstract class InGameCharacter {
	private Player player;
	
	public InGameCharacter(Player player){
		this.player=player;
	}
	
	public abstract boolean isAttackAllowed();
	public abstract boolean isDefendAllowed();
	public abstract int getMaxAllowedMovement();
	public abstract boolean hasToDrawSectorCard();
}
