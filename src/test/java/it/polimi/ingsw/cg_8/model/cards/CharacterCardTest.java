package it.polimi.ingsw.cg_8.model.cards;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.model.cards.character.AlienCard;
import it.polimi.ingsw.cg_8.model.cards.character.CharacterCard;
import it.polimi.ingsw.cg_8.model.cards.character.HumanCard;

import org.junit.Before;
import org.junit.Test;

public class CharacterCardTest {

    CharacterCard c1;
    CharacterCard c2;
    CharacterCard c3;

    @Before
    public void init() {
        c1 = new HumanCard("name", "nick", "rank");
        c2 = new HumanCard("name", "nick", "rank");
        c3 = new AlienCard("name2", "nick", "rank");
    }

    @Test
    public void getNameTest() {
        assertEquals(c1.getName(), "name");
    }

    @Test
    public void getNicknameTest() {
        assertEquals(c1.getNickname(), "nick");
    }

    @Test
    public void getRankTest() {
        assertEquals(c1.getRank(), "rank");
    }

    @Test
    public void equalsTest() {
        assertTrue(c1.equals(c2));
    }

    @Test
    public void equalsTest2() {
        assertFalse(c2.equals(c3));
    }

    @Test
    public void classTest() {
        assertTrue(c1 instanceof HumanCard);
    }

    @Test
    public void classTest2() {
        assertTrue(c1 instanceof CharacterCard);
    }

    @Test
    public void classTest3() {
        assertTrue(c3 instanceof AlienCard);
    }

    @Test
    public void hashCodeTest() {
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void equalsTest3() {
        assertFalse(c1.equals(null));
    }

    @Test
    public void equalsTest4() {
        assertEquals(c1, c1);
    }

    @Test
    public void equalsTest5() {
        assertFalse(c1.equals(new HumanCard(null, "nickaname", "rank")));
    }

    @Test
    public void equalsTest6() {
        assertFalse(c1.equals(new HumanCard("name", null, "rank")));
    }

    @Test
    public void equalsTest7() {
        assertFalse(c1.equals(new HumanCard("name", "nick", null)));
    }

}
