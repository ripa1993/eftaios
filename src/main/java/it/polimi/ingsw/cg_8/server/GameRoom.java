package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.util.ArrayList;

public class GameRoom implements GameRoomInterface{
	
	private  ArrayList<SubscriberInterface> subscribers = new ArrayList<SubscriberInterface>();
	
	@Override
	public void subscribe(SubscriberInterface subscriber) {
		subscribers.add(subscriber);
	}


	@Override
	public void subscribeToGame(int clientID) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sendName(String playerName) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void makeAction(Player player, ClientAction action) {
		// TODO Auto-generated method stub
		
	}
	
	public void dispatchMessage(ServerResponse message) {
		
	}	
}
