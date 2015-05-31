package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.ClientRMI;
import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.StateMachine;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerGameRoom extends ServerPublisher implements
		ServerGameRoomInterface {

	private ClientRMI clientRMI;

	protected ServerGameRoom(ClientRMI client) throws RemoteException {
		UnicastRemoteObject.exportObject(this, 7777);
		this.clientRMI = client;
	}

	@Override
	public void makeAction(int clientId, ClientAction action) throws RemoteException {

		Controller controller = Server.getId2Controller().get(
				clientId);
		boolean result = StateMachine.evaluateAction(controller,
				action, controller.getPlayerById(clientId));
		System.out.println("[DEBUG]"+result);
	}

	@Override
	public void dispatchMessage(ServerResponse message) {
		System.out.println(clientRMI.getClientId());
		clientRMI.publishMessage(message);
	}
}
