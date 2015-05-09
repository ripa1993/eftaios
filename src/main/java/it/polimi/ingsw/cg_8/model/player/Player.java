package it.polimi.ingsw.cg_8.model.player;

import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public class Player {
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
