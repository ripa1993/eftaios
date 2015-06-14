package it.polimi.ingsw.cg_8.model.exceptions;

/**
 * This exception is thrown when you try to create a game with a non valid map
 * 
 * @author Simone
 * @version 1.0
 */
public class NotAValidMapException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8464373364199976664L;

	/**
	 * Creates an exception with message
	 * 
	 * @param string
	 *            message
	 */
	public NotAValidMapException(String string) {
		super(string);
	}

}
