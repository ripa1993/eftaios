package it.polimi.ingsw.cg_8.controller.playerActions.otherActions;

import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.io.Serializable;

/**
 * ClientAction that contains a chat message that needs to be sent to all the
 * players
 * 
 * @author Simone
 * @version 1.0
 */
public class SendChatMessage implements ClientAction, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -17380136986634452L;
	/**
	 * Chat message
	 */
	private final String message;

	/**
	 * Constructor
	 * 
	 * @param message
	 *            message to be sent
	 */
	public SendChatMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 * @return message to be sent
	 */
	public String getMessage() {
		return message;
	}

}
