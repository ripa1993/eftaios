package it.polimi.ingsw.cg_8;

import it.polimi.ingsw.cg_8.controller.Controller;
import it.polimi.ingsw.cg_8.controller.DefaultRules;
import it.polimi.ingsw.cg_8.controller.StateMachine;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.exceptions.EmptyDeckException;
import it.polimi.ingsw.cg_8.model.exceptions.GameAlreadyRunningException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws GameAlreadyRunningException,
			EmptyDeckException, NotAValidInput {

		Controller controller = new Controller(GameMapName.FERMI,
				new DefaultRules());
		Model model = controller.getModel();
		model.addPlayer("simone");
		model.addPlayer("alberto");
		model.addPlayer("fulvio");
		model.addPlayer("luca");
		model.initGame();
		System.out.println(model.getMap());
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println(model.getCurrentPlayerReference());
			System.out.println("Turn: " + model.getRoundNumber() + " phase: "
					+ model.getTurnPhase());
			System.out.println("Insert command:");
			String input = scanner.nextLine();
			ClientAction a = ActionParser.createEvent(input);
			StateMachine.evaluateAction(controller, a,
					model.getCurrentPlayerReference());

		}
	}
}
