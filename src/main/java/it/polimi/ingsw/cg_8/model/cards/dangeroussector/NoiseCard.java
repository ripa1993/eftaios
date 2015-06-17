package it.polimi.ingsw.cg_8.model.cards.dangeroussector;

/**
 * Interface used in noise card decoration, allows to make fake noise and to
 * draw item
 * 
 * @author Simone
 *
 */
public interface NoiseCard {
    /**
     * @return true, if the noise is fake<br>
     *         false, if the noise is real
     */
    public abstract boolean hasToMakeFakeNoise();

    /**
     * @return true, if the player has to draw an item card<br>
     *         false, if he doesn't have to
     */
    public abstract boolean hasToDrawItem();
}
