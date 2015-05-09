package it.polimi.ingsw.cg_8.model.player.character;

public class Alien extends InGameCharacter {
	private AlienBehaviour currentBehaviour;
	
	public Alien () {
		this.currentBehaviour = new NormalBehaviour();
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

	
	
}
