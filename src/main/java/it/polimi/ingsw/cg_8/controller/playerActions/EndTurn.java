package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.TurnPhase;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
/**
 * Action used by the player when he wants to end his turn.
 * @author Alberto Parravicini
 *
 */
public class EndTurn extends PlayerAction {
	
	/**
	 * Change the current player status to {@link PlayerState#ALIVE_WAITING};
	 * Set the next active player;
	 * Change the status of the new active player to {@link PlayerState#ALIVE_PLAYING}.
	 * @param model The current state of the game
	 */
	public static void endTurn(Model model) {
		//model.getCurrentPlayerReference().cycleState();
		model.nextPlayer();
		if (model.getTurnPhase() != TurnPhase.GAME_END) {
			//model.getCurrentPlayerReference().cycleState();
			model.setTurnPhase(TurnPhase.TURN_BEGIN);
		}
	}
}
