package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.BeforeClass;
import org.junit.Test;

public class AttackTest {
	static Model model;
	static Player currentPlayer;

	@BeforeClass
	public static void init() throws NotAValidMapException,
			GameAlreadyRunningException, EmptyDeckException {
		model = new Model(GameMapName.FERMI);
		model.addPlayer("a");
		model.addPlayer("b");
		model.initGame();
		currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
		
		
		if (currentPlayer.getCharacter() instanceof Alien) {
			model.nextPlayer();
			currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
		}
	
		
		model.getCurrentPlayerReference().setPosition(new Coordinate(8, 8));

		EndTurn.endTurn(model);

		model.getCurrentPlayerReference().setPosition(new Coordinate(8, 8));

	}
/*
	@Test
	public void validateAttackTest() {
		assertTrue(AttackValidator.validateAttack(model));
	}

	@Test
	public void attackSuccessfulTest() {
		Attack attack = new Attack(model);

		attack.makeAttack();
	
		assertTrue((attack.getVictims().get(0)).getCharacter() instanceof Human);
		
	}
*/
}
