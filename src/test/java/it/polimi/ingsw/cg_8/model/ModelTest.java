package it.polimi.ingsw.cg_8.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.model.decks.DangerousSectorDeck;
import it.polimi.ingsw.cg_8.model.decks.EscapeHatchDeck;
import it.polimi.ingsw.cg_8.model.decks.ItemDeck;
import it.polimi.ingsw.cg_8.model.map.FermiMap;
import it.polimi.ingsw.cg_8.model.map.GalileiMap;
import it.polimi.ingsw.cg_8.model.map.GalvaniMap;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {
	Model model;

	@Before
	public void init() {
		model = new Model(GameMapName.FERMI);
		model.addPlayer("Simone");
		model.addPlayer("Alberto");
	}

	@Test
	public void testGetPlayer() {
		assertEquals(model.getPlayers().size(), 2);
	}
	
	@Test
	public void testGetRoundNumber(){
		model.initGame();
		model.nextPlayer();
		model.nextPlayer();
		model.nextPlayer();
		assertEquals(model.getRoundNumber(), 2);
	}
	
	@Test
	public void testGetCurrentPlayer(){
		model.initGame();
		int tempPlayer = model.getCurrentPlayer();
		model.nextPlayer();
		model.nextPlayer();
		assertEquals(tempPlayer, model.getCurrentPlayer());
	}
	
	@Test
	public void testGetStartingPlayer(){
		model.initGame();
		model.nextPlayer();
		model.nextPlayer();
		assertEquals(model.getCurrentPlayer(), model.getStartingPlayer());
	}
	
	@Test
	public void testGetTurnPhase(){
		assertEquals(model.getTurnPhase(), TurnPhase.GAME_SETUP);
	}
	
	@Test
	public void testGetCharacterDeck(){
		model.initGame();
		assertTrue(model.getCharacterDeck().getCards().isEmpty());
	}
	
	@Test
	public void testGetDangerousSectorDeck(){
		assertTrue(model.getDangerousSectorDeck() instanceof DangerousSectorDeck);
	}
	
	@Test
	public void testGetEscapeHatchDeck(){
		assertTrue(model.getEscapeHatchDeck() instanceof EscapeHatchDeck);
	}
	
	@Test
	public void testGetItemDeck(){
		assertTrue(model.getItemDeck() instanceof ItemDeck);
	}
	
	@Test
	public void testGetMap(){
		assertTrue(model.getMap() instanceof FermiMap);
	}
	
	@Test
	public void testConstructorGalvani(){
		Model model2 = new Model(GameMapName.GALVANI);
		model2.addPlayer("prova");
		model2.addPlayer("test");
		model2.initGame();
		assertTrue(model2.getMap() instanceof GalvaniMap);
	}
	
	@Test
	public void testConstructorGalilei(){
		Model model2 = new Model(GameMapName.GALILEI);
		model2.addPlayer("prova");
		model2.addPlayer("test");
		model2.initGame();
		assertTrue(model2.getMap() instanceof GalileiMap);
	}
}
