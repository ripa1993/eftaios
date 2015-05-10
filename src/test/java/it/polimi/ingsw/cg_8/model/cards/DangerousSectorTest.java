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
	NoiseCard c4 = new NoiseWithItem(new FakeNoise(new NormalNoise())); // The other way round doesn't seem to work
	
	@Test
	public void testGetDescription1() {	
		assertEquals(c1.toString(), "Noise in the player's position");
	}

	@Test
	public void testGetDescription2() {
		assertEquals(c2.toString(), "Emitting fake noise");
	}

	@Test
	public void testGetDescription3() {
		assertEquals(c3.toString(), "Noise in the player's position Draw an item card");
	}

	@Test
	public void testGetDescription4() {
		assertEquals(c4.toString(), "Emitting fake noise Draw an item card");
	}

}
