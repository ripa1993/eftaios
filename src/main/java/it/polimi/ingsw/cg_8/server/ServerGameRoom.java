package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.ClientRMI;
import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.util.ArrayList;

public class ServerGameRoom implements ServerGameRoomInterface{
	
	private  ArrayList<SubscriberInterface> subscribers = new ArrayList<SubscriberInterface>();
	
	public ServerGameRoom(ClientRMI client) {
		// TODO Auto-generated constructor stub
	}


	


	


	@Override
	public void makeAction(Player player, ClientAction action) {
		// TODO Auto-generated method stub
		
	}
	
	public void dispatchMessage(ServerResponse message) {
		
	}	
}
