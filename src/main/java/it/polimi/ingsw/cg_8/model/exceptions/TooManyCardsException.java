package it.polimi.ingsw.cg_8.model.exceptions;

/**
 * This exception is thrown when a player cannot hold more cards
 * 
 * @author Simone
 * @version 1.0
 */
public class TooManyCardsException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7658760307809799814L;

	/**
	 * Creates an exception with message
	 * 
	 * @param string
	 *            message
	 */
	public TooManyCardsException(String string) {
		super(string);
	}

}
