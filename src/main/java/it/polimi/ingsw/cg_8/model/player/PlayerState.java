package it.polimi.ingsw.cg_8.model.player;

public enum PlayerState {
	ALIVE_WAITING, ALIVE_PLAYING, DEAD, CONNECTING, DISCONNECTED;
	// ALIVE_WAITING: waiting for his turn
	// ALIVE_PLAYING: playing its turn
	// DEAD: player dead
	// CONNECTING: player is joining the game
	// DISCONNECTED: player has left the game
}
