package it.polimi.ingsw.cg_8.model.player.character.alien;

/**
 * Normal behaviour, the alien can move a distance of 2 sectors
 * 
 * @author Simone
 * @version 1.0
 */
public class NormalBehaviour implements AlienBehaviour {
    /**
     * Max allowed distance
     */
    private static final int DISTANCE = 2;

    @Override
    public int getMaxMovementDistance() {
        return DISTANCE;
    }

    @Override
    public String toString() {
        return "NormalBehaviour";
    }

}
