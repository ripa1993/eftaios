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
}
