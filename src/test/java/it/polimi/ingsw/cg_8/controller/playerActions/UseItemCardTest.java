package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseAdrenalineCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseAttackCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseDefenseCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseSedativesCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseSpotlightCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseTeleportCard;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class UseItemCardTest {
	Model model;
	Player currentPlayer;

	@Before
	public void init() throws NotAValidMapException,
			GameAlreadyRunningException, EmptyDeckException {
		model = new Model(GameMapName.FERMI);
		model.addPlayer("pippo");
		model.addPlayer("pluto");
		model.initGame();
		currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
		if (currentPlayer.getCharacter() instanceof Alien) {
			model.nextPlayer();
			currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
		}
	}

	@Test
	public void testUseAdrenalineCard() {
		UseAdrenalineCard.useCard(model);
		assertEquals(2, currentPlayer.getCharacter().getMaxAllowedMovement());
	}

	@Test
	public void adrenalineCardRemoval() {
		boolean check = false;
		
		UseAdrenalineCard.useCard(model);
	}
	@Test
	public void testUseAttackCard() {
		UseAttackCard.useCard(model);
		assertTrue(currentPlayer.getCharacter().isAttackAllowed());
	}

	@Test
	public void testUseDefenseCard() {
		UseDefenseCard.useCard(currentPlayer);
		assertTrue(currentPlayer.getCharacter().isDefendAllowed());
	}

	@Test
	public void testUseSedativesCard() {
		UseSedativesCard.useCard(model);
		assertFalse(currentPlayer.getCharacter().hasToDrawSectorCard());
	}

	@Test
	public void testUseTeleportCard() {
		UseTeleportCard.useCard(model);
		assertEquals(model.getMap().getHumanSpawn(),
				currentPlayer.getLastPosition());
	}

	@Test
	public void testUseSpotlightCard() {
		Set<Player> foundPlayers = UseSpotlightCard.useCard(model,
				new Coordinate(model.getMap().getHumanSpawn().getX(), model
						.getMap().getHumanSpawn().getY()));
		assertTrue(foundPlayers.containsAll(model.getPlayers()));
	}

}
