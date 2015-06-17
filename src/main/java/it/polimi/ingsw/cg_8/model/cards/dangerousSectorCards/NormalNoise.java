package it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards;

/**
 * Normal noise, player makes real noise
 * 
 * @author Simone
 *
 */
public class NormalNoise extends DangerousSectorCard implements NoiseCard {

    @Override
    public boolean hasToMakeFakeNoise() {
        return false;
    }

    @Override
    public boolean hasToDrawItem() {
        return false;
    }

    @Override
    public String toString() {
        return "NormalNoise";
    }
}
