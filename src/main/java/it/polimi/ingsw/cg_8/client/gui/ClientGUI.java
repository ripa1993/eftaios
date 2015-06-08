package it.polimi.ingsw.cg_8.client.gui;

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
			connectionManager = new ConnectionManagerRMI(playerName);

		} else {
			connectionManager = new ConnectionManagerSocket(playerName);
		}
	
		connectionManager.setPlayerName(name);
		guiThread.setConnectionManager(connectionManager);
		
		guiThread.getConnectionManager().setup();
		// TODO: acquire map
		guiThread.run();
	}
}
