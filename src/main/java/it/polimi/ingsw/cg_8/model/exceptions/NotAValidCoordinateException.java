package it.polimi.ingsw.cg_8.model.exceptions;

/**
 * This exception is thrown when you try to access a non valid coordinate
 * 
 * @author Simone
 * @version 1.0
 */
public class NotAValidCoordinateException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -362337127919174038L;

	/**
	 * Creates an exception with message
	 * @param string message
	 */
	public NotAValidCoordinateException(String string) {
		super(string);
	}

	
}
