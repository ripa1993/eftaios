package it.polimi.ingsw.cg_8.view.server;

import java.io.Serializable;

/**
 * General purpose server response, used for generic messages
 * 
 * @author Simone
 * @version 1.0
 */
public class ResponsePrivate implements Serializable, ServerResponse {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3728410205672503413L;
    /**
     * Message to be sent
     */
    private final String message;

    /**
     * Builds a new server response with the given message
     * 
     * @param message
     *            message to be sent
     */
    public ResponsePrivate(String message) {
        this.message = message;
    }

    /**
     * 
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Server: " + message;
    }
}
