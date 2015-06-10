package it.polimi.ingsw.cg_8.model.exceptions;

/**
 * This exception is caused when you try to draw a card from a deck that is
 * empty
 * 
 * @author Simone
 * @version 1.0
 */
public class EmptyDeckException extends Exception {
	/**
	 * Create an exception with message
	 * @param string message
	 */
	public EmptyDeckException(String string) {
		super(string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1604213716604663658L;

}
