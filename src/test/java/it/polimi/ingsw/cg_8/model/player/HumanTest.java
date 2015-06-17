package it.polimi.ingsw.cg_8.model.player;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.cards.character.CharacterCard;
import it.polimi.ingsw.cg_8.model.cards.character.HumanCard;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HumanTest {
    CharacterCard cc;
    static CharacterCard cc2;
    Human human;
    static Human human2;

    @Before
    public void init() {
        cc = new HumanCard("Pippo", "pluto", "paperino");

        human = new Human(cc);

    }

    @BeforeClass
    public static void init2() {
        cc2 = new HumanCard("Ingegneria", "del", "software");
        human2 = new Human(cc2);
        human2.enableAdrenaline();
        human2.enableSedatives();
        human2.enableDefend();
        human2.enableAttack();
    }

    // testing a non decorated human
    @Test
    public void isAttackAllowed() {
        assertFalse(human.isAttackAllowed());
    }

    @Test
    public void isDefendAllowed() {
        assertFalse(human.isDefendAllowed());
    }

    @Test
    public void getMaxAllowedMovement() {
        assertEquals(human.getMaxAllowedMovement(), 1);
    }

    @Test
    public void hasToDrawSectorCard() {
        assertTrue(human.hasToDrawSectorCard());
    }

    // testing an partially decorated human
    @Test
    public void isAttackAllowed2() {
        human.enableAttack();
        assertTrue(human.isAttackAllowed());
    }

    @Test
    public void isDefendAllowed2() {
        human.enableDefend();
        assertTrue(human.isDefendAllowed());
    }

    @Test
    public void getMaxAllowedMovement2() {
        human.enableAdrenaline();
        assertEquals(human.getMaxAllowedMovement(), 2);
    }

    @Test
    public void hasToDrawSectorCard2() {
        human.enableSedatives();
        assertFalse(human.hasToDrawSectorCard());
    }

    // testing a fully decorated man
    @Test
    public void isAttackAllowed3() {
        assertTrue(human2.isAttackAllowed());
    }

    @Test
    public void isDefendAllowed3() {
        assertTrue(human2.isDefendAllowed());
    }

    @Test
    public void getMaxAllowedMovement3() {
        assertEquals(human2.getMaxAllowedMovement(), 2);
    }

    @Test
    public void hasToDrawSectorCard3() {
        assertFalse(human2.hasToDrawSectorCard());
    }

    // testing equals
    @Test
    public void equals() {
        assertFalse(human.equals(human2));
    }
}
