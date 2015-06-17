package it.polimi.ingsw.cg_8.view.client.exceptions;

/**
 * This exception is thrown when the ActionParser can't recognise a valid
 * ClientAction in a string
 * 
 * @author Simone
 *
 */
public class NotAValidInput extends Exception {
    /**
	 * 
	 */
    private static final long serialVersionUID = 127305830991227883L;

    /**
     * Default constructor
     * 
     * @param string
     *            message
     */
    public NotAValidInput(String string) {
        super(string);
    }

}
