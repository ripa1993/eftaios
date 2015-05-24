package it.polimi.ingsw.cg_8.controller.playerActions;

import static org.junit.Assert.*;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import org.junit.Before;
import org.junit.Test;

public class MovementValidatorTest {
	Model model;
	Player currentPlayer;

	@Before
	public void init() throws NotAValidMapException,
			GameAlreadyRunningException, EmptyDeckException {
		model = new Model(GameMapName.FERMI);
		model.addPlayer("player1");
		model.addPlayer("player2");
		model.initGame();
		currentPlayer = model.getPlayers().get(model.getCurrentPlayer());
		currentPlayer.cycleState();
		System.out.println("Turn0" + model.getPlayers());
	}
	
	
	@Test
	public void validMoveHuman() {
		boolean check = false;
		if(currentPlayer.getCharacter() instanceof Human) {
			check = MovementValidator.validateMove(model, new Coordinate(11,10));
		}
		else {
			check = MovementValidator.validateMove(model, new Coordinate(11,7));
		}
		assertTrue(check);
	}
	
	@Test
	public void notValidMoveHuman() {
		boolean check = true;
		if(currentPlayer.getCharacter() instanceof Human) {
			check = MovementValidator.validateMove(model, new Coordinate(14,8));
		}
		else {
			check = MovementValidator.validateMove(model, new Coordinate(11,118));
		}
		assertFalse(check);
	}
}
