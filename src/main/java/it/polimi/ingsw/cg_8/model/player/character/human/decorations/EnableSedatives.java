package it.polimi.ingsw.cg_8.model.player.character.human.decorations;

import it.polimi.ingsw.cg_8.model.player.character.human.HumanBehaviour;

/**
 * Allows the human to not draw dangerous sector card
 * 
 * @author Simone
 * @version 1.0
 */
public class EnableSedatives extends HumanDecorator {
    /**
     * Constructor
     * 
     * @param humanBehaviourToBeDecorated
     *            behaviour to be decorated
     */
    public EnableSedatives(HumanBehaviour humanBehaviourToBeDecorated) {
        super(humanBehaviourToBeDecorated);

    }

    @Override
    public boolean hasToDrawSectorCard() {
        return false;
    }

    @Override
    public String toString() {
        return "EnableSedatives " + humanBehaviourToBeDecorated.toString();
    }
}
