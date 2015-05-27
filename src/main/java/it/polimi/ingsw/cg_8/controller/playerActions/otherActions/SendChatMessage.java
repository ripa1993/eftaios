package it.polimi.ingsw.cg_8.controller.playerActions.otherActions;

import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;

import java.io.Serializable;

public class SendChatMessage implements ClientAction, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -17380136986634452L;
	private final String message;
	public SendChatMessage(String message){
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	
}
