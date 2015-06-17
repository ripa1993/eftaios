package it.polimi.ingsw.cg_8.model.cards;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.FakeNoise;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NoiseCard;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NoiseWithItem;
import it.polimi.ingsw.cg_8.model.cards.dangerousSectorCards.NormalNoise;

public class DangerousSectorTest {

    NoiseCard c1 = new NormalNoise();
    NoiseCard c2 = new FakeNoise(new NormalNoise());
    NoiseCard c3 = new NoiseWithItem(new NormalNoise());
    NoiseCard c4 = new NoiseWithItem(new FakeNoise(new NormalNoise()));

    @Test
    public void hasToDrawItemTest1() {
        assertFalse(c1.hasToDrawItem());
    }

    @Test
    public void hasToDrawItemTest2() {
        assertFalse(c2.hasToDrawItem());
    }

    @Test
    public void hasToDrawItemTest3() {
        assertTrue(c3.hasToDrawItem());
    }

    @Test
    public void hasToDrawItemTest4() {
        assertTrue(c4.hasToDrawItem());
    }

    @Test
    public void hasToMakeFakeNoiseTest1() {
        assertFalse(c1.hasToMakeFakeNoise());
    }

    @Test
    public void hasToMakeFakeNoiseTest2() {
        assertTrue(c2.hasToMakeFakeNoise());
    }

    @Test
    public void hasToMakeFakeNoiseTest3() {
        assertFalse(c3.hasToMakeFakeNoise());
    }

    @Test
    public void hasToMakeFakeNoiseTest4() {
        assertTrue(c4.hasToMakeFakeNoise());
    }

    @Test
    public void toStringTest1() {
        System.out.println("1: " + c1);

        assertTrue(c1.toString() instanceof String);
    }

    @Test
    public void toStringTest2() {
        System.out.println("3: " + c3);

        assertTrue(c3.toString() instanceof String);

    }

    @Test
    public void toStringTest3() {
        assertTrue(c4.toString() instanceof String);
        System.out.println("4: " + c4);

    }

    @Test
    public void toStringTest4() {
        System.out.println("2: " + c2);
        assertTrue(c2.toString() instanceof String);
    }
}
