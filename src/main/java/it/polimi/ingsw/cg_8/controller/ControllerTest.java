package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.controller.playerActions.Attack;
import it.polimi.ingsw.cg_8.controller.playerActions.AttackValidator;
import it.polimi.ingsw.cg_8.controller.playerActions.EndTurn;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.model.player.Player;
import it.polimi.ingsw.cg_8.model.player.character.alien.Alien;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

public class ControllerTest {

	public static void main(String[] args) throws NotAValidMapException,
			GameAlreadyRunningException, EmptyDeckException {
		
		Model model;
		Player currentPlayer;
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
		model.getCurrentPlayerReference()
				.setPosition(new Coordinate(8, 8));
		System.out.println(model.getCurrentPlayerReference().toString());
		
		EndTurn.endTurn(model);
		
		model.getCurrentPlayerReference()
				.setPosition(new Coordinate(8, 8));
		
		System.out.println(model.getCurrentPlayerReference().toString());
		
		System.out.println(AttackValidator.validateAttack(model));
		
		
		if (AttackValidator.validateAttack(model)) {
			Attack attack = new Attack(model);
			System.out.println(attack.getPlayersInSector());
			System.out.println( model.getPlayers());
			attack.makeAttack();
			System.out.println(attack.getVictims());
		}
		for(Player p : model.getPlayers()) {
			System.out.println(p.toString());
		}
		
	}
}
