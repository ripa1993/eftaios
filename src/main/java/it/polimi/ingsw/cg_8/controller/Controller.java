package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.TurnPhase;
import it.polimi.ingsw.cg_8.model.exceptions.NotAValidMapException;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

/**
 * Main controller class: it handles the initialization of a new game, the main
 * game loop, and communicates with both the view and the model.
 * 
 * @author Alberto Parravicini
 * 
 */
public class Controller {
	
	Model model;
	Rules rules;

	/**
	 * Initialization of a new game. Note that the model is initialized with the
	 * init() function, placed inside model
	 * @throws NotAValidMapException 
	 */
	public Controller(GameMapName mapName, Rules rules) {

		try {
			model = new Model(mapName);
		} catch (NotAValidMapException e) {
			// e.printStackTrace();
		}
		
	}

	/**
	 * Used to update the model after changing the state of the game or
	 * validating an action.
	 */
	private void updateModel() {

	}

	/** Used to update the view. */
	public void updateView() {

	}

	/**
	 * Main game loop: after the startup the main cycle is started, until
	 * certain conditions are verified (everyone leaves the game, turn 40 is
	 * reached, every human player either escapes or is killed. Then the
	 * cleanup() method in called.
	 */
	public void gameLoop() {



		while (model.getTurnPhase() != TurnPhase.GAME_END) {
			processInput();
			updateModel();
			updateView();
		}
		// cleanup();
	}

	/**
	 * Used at the end of the game to remove resources that are no longer
	 * needed.
	 */
	public void cleanup() {

	}

	/**
	 * Input handler: analyze inputs given by the player and gives the
	 * appropriate response (e.g: calls an action). Used by the main game loop.
	 */
	private void processInput() {

	}
	
	public Model getModel() {
		return this.model;
	}
	
	public Rules getRules(){
		return this.rules;
	}
}
