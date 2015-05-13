package it.polimi.ingsw.cg_8.controller;

/**
 * 
 * @author Alberto Parravicini 
 * 		Main controller class: it handles the
 *         initialization of a new game, the main game loop, and communicates
 *         with both the view and the model.
 */
public final class Controller {

	public void startup() {
		/* Initialization of a new game. */
	}

	private void updateModel() {
		/*
		 * Used to update the model after changing the state of the game or
		 * validating an action.
		 */
	}

	public void updateView() {
		/* Used to update the view. */
	}

	public void loop() {
		/* Main game loop */
		startup();
		while (true) {	/* The cycle should be ended when a certain condition is verified. */
			processInput();
			updateModel();
			updateView();
		}
		//cleanup();
	}

	public void cleanup() {
		/*
		 * Used at the end of the game to remove resources that are no longer
		 * needed.
		 */
	}

	private void processInput() {
		/*
		 * Input handler: analyze inputs given by the player and gives the
		 * appropriate response (e.g: calls an action).
		 * Used by the main game loop.
		 */
	}
}
