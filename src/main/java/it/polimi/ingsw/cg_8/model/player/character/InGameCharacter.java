package it.polimi.ingsw.cg_8.model.player.character;

import it.polimi.ingsw.cg_8.model.player.Player;

public abstract class InGameCharacter {
	private final Player player;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
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
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}

	public InGameCharacter(Player player){
		this.player=player;
	}
	
	public Player getPlayer() {
		return player;
	}

	public abstract boolean isAttackAllowed();
	public abstract boolean isDefendAllowed();
	public abstract int getMaxAllowedMovement();
	public abstract boolean hasToDrawSectorCard();
}
