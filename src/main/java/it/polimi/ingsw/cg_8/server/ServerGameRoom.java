package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.client.gui.BetterCLI;
import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.StateMachine;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerGameRoom extends ServerPublisher implements
		ServerGameRoomInterface {

	private SubscriberInterface clientRMI;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(ServerGameRoom.class);
	protected ServerGameRoom(SubscriberInterface client) throws RemoteException {
		UnicastRemoteObject.exportObject(this, 7777);
		this.clientRMI = client;
	}

	@Override
	public boolean makeAction(int clientId, ClientAction action)
			throws RemoteException {

		Controller controller = Server.getId2Controller().get(clientId);
		boolean result = StateMachine.evaluateAction(controller, action,
				controller.getPlayerById(clientId));
		logger.debug(result);
		/**
		 * Can be used to print the action result on the client, for debugging
		 * purposes
		 */
		// this.dispatchMessage(new ResponsePrivate(String.valueOf(result)));
		return result;
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
