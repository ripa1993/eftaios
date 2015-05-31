package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerGameRoomInterface extends Remote {

	
	public void makeAction(Player player, ClientAction action) throws RemoteException;
	
	
}
