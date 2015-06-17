package it.polimi.ingsw.cg_8.view.client.actions;

import java.io.Serializable;

/**
 * Action: set player name
 * 
 * @author Simone
 *
 */
public class ActionSetName implements ClientAction, Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -7006504494661387905L;
    /**
     * New player name
     */
    private final String name;

    /**
     * Constructor
     * 
     * @param name
     *            new player name
     */
    public ActionSetName(String name) {
        this.name = name;
    }

    /**
     * Getter
     * 
     * @return new player name
     */
    public String getName() {
        return this.name;
    }

}
