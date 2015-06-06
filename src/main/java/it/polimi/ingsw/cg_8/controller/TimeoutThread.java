package it.polimi.ingsw.cg_8.controller;

import java.util.concurrent.TimeUnit;

import it.polimi.ingsw.cg_8.server.Server;

public class TimeoutThread implements Runnable {

	private final static int TIMEOUT = 10;
	
	private Controller controller;

	public TimeoutThread(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		try {
			System.out.println(Server.MIN_PLAYER+" players joined. Starting game in 10s or if there are "+Server.MAX_PLAYER+" players.");
			TimeUnit.SECONDS.sleep(TIMEOUT);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
		if(controller.isGameStarted()==true){
			return;
		}
		else{
			controller.initGame();
			System.out.println("Game started because timeout is over.");
			return;
		}

	}
}
