package it.polimi.ingsw.cg_8.model.player;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.cg_8.model.player.character.InGameCharacter;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public class Player {

	private PlayerState state;
	private final String name;
	private InGameCharacter character;
	private final List<Round> rounds;
	private final Hand hand;

	// a player is created when he joins the game (match still not running)
	public Player(String name) {
		state = PlayerState.CONNECTING;
		this.name = name;
		character = null;
		rounds = new ArrayList<Round>();
		hand = new Hand();
	}

	// a player is initialized when the match actually starts
	public void init(InGameCharacter character, Coordinate startingPosition) {
		this.character = character;
		state = PlayerState.ALIVE_WAITING;
		rounds.add(new Round(0, startingPosition));
	}	

	public int getRoundNumber(){
		return rounds.size()-1;
	}
	
	public Coordinate getLastPosition() {
		return rounds.get(getRoundNumber()).getCoordinate();
	}

	public void setPosition(Coordinate position) {
		rounds.add(new Round(getRoundNumber()+1,position));
	}
	
	// may be used when the player wants to be teleported after having moved, not sure if legal
	public void editLastPosition(Coordinate newCoordinate){
		int lastRoundNumber = getRoundNumber();
		rounds.remove(lastRoundNumber);
		setPosition(newCoordinate);
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
	public void setDisconnected() {
		state = PlayerState.DISCONNECTED;
	}

	// kills the player
	public void setDead() {
		state = PlayerState.DEAD;
	}

	// changes the status from playing to waiting and vice-versa
	public void cycleState() {
		if (state == PlayerState.ALIVE_WAITING) {
			state = PlayerState.ALIVE_PLAYING;
		} else if (state == PlayerState.ALIVE_PLAYING) {
			state = PlayerState.ALIVE_WAITING;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((character == null) ? 0 : character.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rounds == null) ? 0 : rounds.hashCode());
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
		if (rounds == null) {
			if (other.rounds != null)
				return false;
		} else if (!rounds.equals(other.rounds))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	public Hand getHand() {
		return hand;
	}

	

}
