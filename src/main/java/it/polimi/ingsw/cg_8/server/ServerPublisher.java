package it.polimi.ingsw.cg_8.server;

import it.polimi.ingsw.cg_8.view.server.ServerResponse;

public abstract class ServerPublisher {

	public abstract void dispatchMessage(ServerResponse message);
}
