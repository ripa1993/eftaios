package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseAdrenalineCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseAttackCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseDefenseCard;
import it.polimi.ingsw.cg_8.controller.playerActions.useItemCard.UseItemCard;
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
		UseItemCard adrenaline = new UseAdrenalineCard(model);
		adrenaline.useCard();
		assertEquals(2, currentPlayer.getCharacter().getMaxAllowedMovement());
	}

	@Test
	public void testUseAttackCard() {
		UseItemCard attack = new UseAttackCard(model);
		attack.useCard();
		assertTrue(currentPlayer.getCharacter().isAttackAllowed());
	}

	@Test
	public void testUseDefenseCard() {
		UseItemCard defense = new UseDefenseCard(model);
		defense.useCard();
		assertTrue(currentPlayer.getCharacter().isDefendAllowed());
	}

	@Test
	public void testUseSedativesCard() {
		UseItemCard sedatives = new UseSedativesCard(model);
		sedatives.useCard();
		assertFalse(currentPlayer.getCharacter().hasToDrawSectorCard());
	}

	@Test
	public void testUseTeleportCard() {
		UseItemCard teleport = new UseTeleportCard(model);
		teleport.useCard();
		assertEquals(model.getMap().getHumanSpawn(),
				currentPlayer.getLastPosition());
	}

	@Test
	public void testUseSpotlightCard() {
		UseItemCard spotlight = new UseSpotlightCard(model, new Coordinate(
				model.getMap().getHumanSpawn().getX(), model.getMap()
						.getHumanSpawn().getY() + 1));
		spotlight.useCard();
		assertTrue(((UseSpotlightCard) spotlight).getFoundPlayers()
				.containsAll(model.getPlayers()));
	}

}
