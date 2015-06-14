package it.polimi.ingsw.cg_8.model.exceptions;

/**
 * This exception is thrown when you try to add a player to an already started
 * game
 * 
 * @author Simone
 * @version 1.0
 */
public class GameAlreadyRunningException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1854502117873075957L;

	/**
	 * Creates an exception with message
	 * 
	 * @param string
	 *            message
	 */
	public GameAlreadyRunningException(String string) {
		super(string);
	}

}
