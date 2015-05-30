package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.rmi.Remote;

public interface GameRoomInterface extends Remote {

	public void subscribe(SubscriberInterface subscriber);
	
	public void subscribeToGame(int PlayerID);
	
	public void sendName(String playerName);
	
	public void makeAction(Player player, ClientAction action);
	
	
}
