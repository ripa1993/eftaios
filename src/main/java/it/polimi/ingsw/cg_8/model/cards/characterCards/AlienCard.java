package it.polimi.ingsw.cg_8.model.cards.characterCards;

/**
 * Alien character card
 * 
 * @author Simone
 *
 */
public class AlienCard extends CharacterCard {
    /**
     * Constructor
     * 
     * @param name
     *            alien name
     * @param nickname
     *            alien nickname
     * @param rank
     *            alien rank
     */
    public AlienCard(String name, String nickname, String rank) {
        super(name, nickname, rank);
    }

    @Override
    public String toString() {
        return "AlienCard [getName()=" + getName() + ", getNickname()="
                + getNickname() + ", getRank()=" + getRank() + "]";
    }
}
