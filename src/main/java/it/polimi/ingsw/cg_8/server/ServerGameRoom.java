package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.StateMachine;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerGameRoom extends ServerPublisher implements
		ServerGameRoomInterface {

	private SubscriberInterface clientRMI;

	protected ServerGameRoom(SubscriberInterface client) throws RemoteException {
		UnicastRemoteObject.exportObject(this, 7777);
		this.clientRMI = client;
	}

	@Override
	public void makeAction(int clientId, ClientAction action)
			throws RemoteException {

		Controller controller = Server.getId2Controller().get(clientId);
		boolean result = StateMachine.evaluateAction(controller, action,
				controller.getPlayerById(clientId));
		System.out.println("[DEBUG]" + result);
		/**
		 * Can be used to print the action result on the client, for debugging
		 * purposes
		 */
		// this.dispatchMessage(new ResponsePrivate(String.valueOf(result)));
	}

	@Override
	public void dispatchMessage(ServerResponse message) {

		try {
			clientRMI.publishMessage(message);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
	}
}
