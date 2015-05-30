package it.polimi.ingsw.cg_8.view.server;

import java.io.Serializable;

public class ResponsePrivate implements Serializable, ServerResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3728410205672503413L;
	private final String message;
	
	public ResponsePrivate(String message){
		this.message=message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "The server said: " + message;
	}
}
