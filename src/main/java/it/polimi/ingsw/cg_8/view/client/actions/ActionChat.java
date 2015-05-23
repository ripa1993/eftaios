package it.polimi.ingsw.cg_8.view.client.actions;

import java.io.Serializable;

/**
 * Action: send a chat message
 * 
 * @author Simone
 *
 */
public class ActionChat implements ClientAction, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4231024378871912612L;
	/**
	 * Chat message
	 */
	String message;

	/**
	 * Constructor
	 * 
	 * @param message
	 *            chat message
	 */
	public ActionChat(String message) {
		this.message = message;
	}

	/**
	 * Getter
	 * 
	 * @return chat message
	 */
	public String getMessage() {
		return this.message;
	}
}
