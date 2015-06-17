package it.polimi.ingsw.cg_8.model.player.character;

import it.polimi.ingsw.cg_8.model.cards.character.CharacterCard;

/**
 * Character that can be played by a
 * {@link it.polimi.ingsw.cg_8.model.player.Player Player}. It is extended by
 * {@link alien.Alien Alien} and {@link human.Human Human}
 * 
 * @author Simone
 * @version 1.0
 */
public abstract class InGameCharacter {
    /**
     * Reference to the character card related to the current character
     */
    private final CharacterCard characterCard;

    /**
     * Constructor for InGameCharacter
     * 
     * @param characterCard
     *            character card
     */
    public InGameCharacter(CharacterCard characterCard) {
        this.characterCard = characterCard;
    }

    /**
     * @return true, if the attack is allowed<br>
     *         false, if the attack is denied
     */
    public abstract boolean isAttackAllowed();

    /**
     * @return true, if the defense is allowed<br>
     *         false, if the defense is denied
     */
    public abstract boolean isDefendAllowed();

    /**
     * @return max distance that the character can walk
     */
    public abstract int getMaxAllowedMovement();

    /**
     * 
     * @return true, if the player has to draw a sector card<br>
     *         false, if the player has not to draw a sector card
     */
    public abstract boolean hasToDrawSectorCard();

    /**
     * Getter for character card
     * 
     * @return character card owned by this character
     */
    public CharacterCard getCharacterCard() {
        return characterCard;
    }

    /**
     * Never used, overridden by its children.
     */
    @Override
    public String toString() {
        return "InGameCharacter [characterCard=" + characterCard + "]";
    }

}
