package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

/**
 * Main client class for the GUI; it allows the user to set his user-name and select the
 * connection type he prefers. It also starts the {@link ConnectionManager} used to handle the
 * game.
 * @author Alberto Parravicini
 * @version 1.1
 */
public class ClientGUI {

	public static void main(String[] args) {

		String playerName = "Default";
		ConnectionManager connectionManager;
		ClientGUIThread guiThread = new ClientGUIThread();

		
		// get the player name.
		String name = JOptionPane
				.showInputDialog("Insert your name:", "Player");
		playerName = name;
		// get a connection mode.
		Object options[] = { "RMI", "Socket" };
		int connection = -1;
		while (connection == -1) {
			connection = JOptionPane.showOptionDialog(null,
					"Select the connection mode", "Connection setup",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);
		}
		
		if (connection == 0) {
			connectionManager = new ConnectionManagerRMI(playerName, GameMapName.FERMI);

		} else {
			connectionManager = new ConnectionManagerSocket(playerName, GameMapName.FERMI);
		}
	
		connectionManager.setPlayerName(name);
		guiThread.setConnectionManager(connectionManager);
		
		guiThread.getConnectionManager().setup();
		// TODO: acquire map
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.submit(guiThread);
	}
}
