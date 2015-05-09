package it.polimi.ingsw.cg_8.model.player;

import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public class Player {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((character == null) ? 0 : character.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		Player other = (Player) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	private PlayerState state;
	private final String name;
	private InGameCharacter character;
	private Coordinate position;
	
	
	// a player is created when he joins the game (match still not running)
	public Player(String name){
		state = PlayerState.CONNECTING;
		this.name = name;
		character = null;
	}
	
	// a player is initialized when the match actually starts
	public void init(InGameCharacter character, Coordinate startingPosition){
		this.character = character;
		this.position = startingPosition;
		state = PlayerState.ALIVE_WAITING;
	}
	
	public Coordinate getPosition() {
		return position;
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}

	public PlayerState getState() {
		return state;
	}

	public String getName() {
		return name;
	}

	public InGameCharacter getCharacter() {
		return character;
	}

	// set the player to disconnected status
	public void setDisconnected(){
		state = PlayerState.DISCONNECTED;
	}
	
	// kills the player
	public void setDead(){
		state = PlayerState.DEAD;
	}
	
	// changes the status from playing to waiting and viceversa
	public void cycleState(){
		if (state == PlayerState.ALIVE_WAITING) {
			state = PlayerState.ALIVE_PLAYING;
		} else if (state == PlayerState.ALIVE_PLAYING){
			state = PlayerState.ALIVE_WAITING;
		}
	}
	
	
}
