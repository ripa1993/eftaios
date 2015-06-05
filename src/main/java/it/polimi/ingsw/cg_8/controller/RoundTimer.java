package it.polimi.ingsw.cg_8.controller;

import it.polimi.ingsw.cg_8.controller.playerActions.otherActions.Disconnect;
import it.polimi.ingsw.cg_8.model.Model;
import it.polimi.ingsw.cg_8.model.TurnPhase;

import java.util.concurrent.TimeUnit;

public class RoundTimer extends Thread {
	private final static int SECONDS_PER_TURN = 5;
	private Model model;

	public RoundTimer(Model model) {
		this.model = model;
	}

	@Override
	public void run() {
		try {
			// wait the player to do his actions
			TimeUnit.SECONDS.sleep(SECONDS_PER_TURN);
			// if timeout is over without receiving an interrupt
			synchronized (model) {
				System.out.println(model.getCurrentPlayerReference().getName()+" has timed out. Disconnecting.");
				Disconnect.disconnect(model.getCurrentPlayerReference());
				model.nextPlayer();
				model.setTurnPhase(TurnPhase.TURN_BEGIN);
				if (model.getTurnPhase()== TurnPhase.GAME_END){
					return;
				}else{
					this.run();	
				}
			}

		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

}
