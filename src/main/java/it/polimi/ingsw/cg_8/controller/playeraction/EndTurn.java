package it.polimi.ingsw.cg_8.controller.playeraction;

import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.TurnPhase;
import it.polimi.ingsw.cg_8.model.player.PlayerState;
import it.polimi.ingsw.cg_8.model.player.character.human.Human;
import it.polimi.ingsw.cg_8.model.player.character.human.NormalHuman;

/**
 * Action used by the player when he wants to end his turn.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class EndTurn implements PlayerAction {
    /**
     * Constructor
     */
    private EndTurn() {

    }

    /**
     * Change the current player status to {@link PlayerState#ALIVE_WAITING};
     * Set the next active player; Change the status of the new active player to
     * {@link PlayerState#ALIVE_PLAYING}.
     * 
     * @param model
     *            The current state of the game
     */
    public static void endTurn(Model model) {
        // reset human behaviour at the turn end
        if(model.getCurrentPlayerReference().getCharacter() instanceof Human){
            ((Human) (model.getCurrentPlayerReference().getCharacter())).setBehaviour(new NormalHuman());
        }
        model.nextPlayer();
        if (model.getTurnPhase() != TurnPhase.GAME_END) {
            model.setTurnPhase(TurnPhase.TURN_BEGIN);
        }
    }
}
