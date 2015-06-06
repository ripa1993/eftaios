package it.polimi.ingsw.cg_8.controller.playerActions;

import it.polimi.ingsw.cg_8.controller.RoundTimer;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.TurnPhase;
import it.polimi.ingsw.cg_8.model.player.PlayerState;

import java.util.HashMap;
import java.util.Map;
/**
 * Action used by the player when he wants to end his turn.
 * @author Alberto Parravicini
 *
 */
public class EndTurn extends PlayerAction {
	private static Map<Model, RoundTimer> model2Thread = new HashMap<Model, RoundTimer>();

	/**
	 * Change the current player status to {@link PlayerState#ALIVE_WAITING};
	 * Set the next active player;
	 * Change the status of the new active player to {@link PlayerState#ALIVE_PLAYING}.
	 * @param model The current state of the game
	 */
	public static void endTurn(Model model) {
		if(model2Thread.get(model)==null){
			model2Thread.put(model, new RoundTimer(model));
		} else {
			model2Thread.get(model).interrupt();
		}
		model.nextPlayer();
		model2Thread.get(model).run();
		
		if (model.getTurnPhase() != TurnPhase.GAME_END) {
			//model.getCurrentPlayerReference().cycleState();
			model.setTurnPhase(TurnPhase.TURN_BEGIN);
		}
	}
	
	public static void gameBegin(Model model){
		model2Thread.put(model, new RoundTimer(model));
		model2Thread.get(model).run();
	}
}
