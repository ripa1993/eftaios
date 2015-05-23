package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.*;
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

public class AttackTest {
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
		model.getCurrentPlayerReference().cycleState();
		model.getCurrentPlayerReference().setPosition(new Coordinate(8, 8));

		EndTurn.endTurn(model);

		model.getCurrentPlayerReference().setPosition(new Coordinate(8, 8));

	}

	@Test
	public void validateAttackTest() {
		assertTrue(AttackValidator.validateAttack(model));
	}

	@Test
	public void attackSuccesfulTest() {
		Attack attack = new Attack(model);

		attack.makeAttack();
		System.out.println(attack.getVictims());
		assertTrue((attack.getVictims().get(0)).equals(model.getPlayers().get(1)));
		
	}

}
