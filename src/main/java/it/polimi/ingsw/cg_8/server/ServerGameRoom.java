package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.client.SubscriberInterface;
import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.StateMachine;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.server.ServerResponse;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Class containing the methods used by the client to the server, and
 * vice-versa.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class ServerGameRoom extends ServerPublisher implements
		ServerGameRoomInterface {
	/**
	 * Reference to the client assigned to a specific instance of this class,
	 * used to send messages to it.
	 */
	private SubscriberInterface clientRMI;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(ServerGameRoom.class);
	
	/**
	 * The class has to be exported to be used by the client.
	 * 
	 * @param client
	 * @throws RemoteException
	 */
	protected ServerGameRoom(SubscriberInterface client) throws RemoteException {
		UnicastRemoteObject.exportObject(this, 7777);
		this.clientRMI = client;
	}

	/**
	 * Used by the client to send actions to the server, after the start of the
	 * game.
	 * 
	 * @param clientId
	 *            The client Id
	 * @param action
	 *            The action sent by the client
	 * @return Whether the action was accepted or not.
	 * @throws RemoteException
	 */
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

	/**
	 * Used by the server to send messages to the client.
	 * 
	 * @param message
	 *            A {@link ServerResponse} given by the server.
	 */
	@Override
	public void dispatchMessage(ServerResponse message) {

		try {
			clientRMI.publishMessage(message);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
	}
}
