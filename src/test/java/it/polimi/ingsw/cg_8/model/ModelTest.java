package it.polimi.ingsw.cg_8.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.controller.playerActions.EndTurn;
import it.polimi.ingsw.cg_8.model.decks.DangerousSectorDeck;
import it.polimi.ingsw.cg_8.model.decks.EscapeHatchDeck;
import it.polimi.ingsw.cg_8.model.decks.ItemDeck;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.FermiMapXML;
import it.polimi.ingsw.cg_8.model.map.GalileiMapXML;
import it.polimi.ingsw.cg_8.model.map.GalvaniMapXML;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {
	Model model;

	@Before
	public void init() {
		try {
			model = new Model(GameMapName.FERMI);
		} catch (NotAValidMapException e) {
			e.printStackTrace();
		}
		try {
			model.addPlayer("Simone");
		} catch (GameAlreadyRunningException e) {
			e.printStackTrace();
		}
		try {
			model.addPlayer("Alberto");
		} catch (GameAlreadyRunningException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetPlayer() {
		assertEquals(model.getPlayers().size(), 2);
	}

	@Test
	public void testGetCurrentPlayer() throws EmptyDeckException {
		model.initGame();
		int tempPlayer = model.getCurrentPlayer();
		System.out.println("1:" + model.getCurrentPlayer());
		EndTurn.endTurn(model);
		System.out.println("2:" + model.getCurrentPlayer());
		EndTurn.endTurn(model);
		System.out.println("3:" + model.getCurrentPlayer());

		assertEquals(tempPlayer, model.getCurrentPlayer());
	}

	@Test
	public void testGetStartingPlayer() throws EmptyDeckException {
		model.initGame();
		EndTurn.endTurn(model);
		EndTurn.endTurn(model);
		assertEquals(model.getCurrentPlayer(), model.getStartingPlayer());
	}

	@Test
	public void testGetTurnPhase() {
		assertEquals(model.getTurnPhase(), TurnPhase.GAME_SETUP);
	}

	@Test
	public void testGetCharacterDeck() throws EmptyDeckException {
		model.initGame();
		assertTrue(model.getCharacterDeck().getCards().isEmpty());
	}

	@Test
	public void testGetDangerousSectorDeck() {
		assertTrue(model.getDangerousSectorDeck() instanceof DangerousSectorDeck);
	}

	@Test
	public void testGetEscapeHatchDeck() {
		assertTrue(model.getEscapeHatchDeck() instanceof EscapeHatchDeck);
	}

	@Test
	public void testGetItemDeck() {
		assertTrue(model.getItemDeck() instanceof ItemDeck);
	}

	@Test
	public void testGetMap() {
		assertTrue(model.getMap() instanceof FermiMapXML);
	}

	@Test
	public void testConstructorGalvani() {
		try {
			Model model2 = new Model(GameMapName.GALVANI);
			model2.addPlayer("prova");
			model2.addPlayer("test");
			model2.initGame();
			assertTrue(model2.getMap() instanceof GalvaniMapXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConstructorGalilei() {
		try {
			Model model2 = new Model(GameMapName.GALILEI);
			model2.addPlayer("prova");
			model2.addPlayer("test");
			model2.initGame();
			assertTrue(model2.getMap() instanceof GalileiMapXML);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected = GameAlreadyRunningException.class)
	public void addPlayerAfterInit() throws GameAlreadyRunningException,
			EmptyDeckException {
		model.initGame();
		model.addPlayer("asd");
	}

	@Test(expected = GameAlreadyRunningException.class)
	public void removePlayerAfterInit() throws EmptyDeckException,
			GameAlreadyRunningException {
		model.initGame();
		Player player = model.getCurrentPlayerReference();
		model.removePlayer(player);
	}
}
