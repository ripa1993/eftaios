package it.polimi.ingsw.cg_8.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.characterCards.AlienCard;
import it.polimi.ingsw.cg_8.model.cards.characterCards.CharacterCard;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;

import org.junit.BeforeClass;
import org.junit.Test;

public class AlienTest {
    static Alien normalAlien;
    static Alien fedAlien;
    static CharacterCard cc1;
    static CharacterCard cc2;

    @BeforeClass
    public static void init() {
        cc1 = new AlienCard("a", "b", "c");
        cc2 = new AlienCard("d", "e", "f");

        normalAlien = new Alien(cc1);
        fedAlien = new Alien(cc2);
        fedAlien.feedAlien();
    }

    // testing normal behaviour
    @Test
    public void getMaxAllowedMovement() {
        assertEquals(normalAlien.getMaxAllowedMovement(), 2);
    }

    @Test
    public void isAttackAllowed() {
        assertTrue(normalAlien.isAttackAllowed());
    }

    @Test
    public void isDefendAllowed() {
        assertFalse(normalAlien.isDefendAllowed());
    }

    @Test
    public void hasToDrawSectorCard() {
        assertTrue(normalAlien.hasToDrawSectorCard());
    }

    // testing fed behaviour
    @Test
    public void getMaxAllowedMovement2() {
        assertEquals(fedAlien.getMaxAllowedMovement(), 3);
    }

    @Test
    public void isAttackAllowed2() {
        assertTrue(fedAlien.isAttackAllowed());
    }

    @Test
    public void isDefendAllowed2() {
        assertFalse(fedAlien.isDefendAllowed());
    }

    @Test
    public void hasToDrawSectorCard2() {
        assertTrue(fedAlien.hasToDrawSectorCard());
    }

    // testing equals
    @Test
    public void equals() {
        assertFalse(normalAlien.equals(fedAlien));
    }

    @Test
    public void toStringTest() {
        assertEquals("Fed Alien", fedAlien.toString());
    }

}
