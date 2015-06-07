package it.polimi.ingsw.cg_8.client.gui;

import javax.swing.JOptionPane;

public class ClientGUI {



//	public void setPlayerName(String playerName) {
//		this.playerName = playerName;
//	}
//
//	public ConnectionManager getConnectionManager() {
//		return connectionManager;
//	}
//
//	public ClientGUIThread getGuiThread() {
//		return guiThread;
//	}
//
//	public String getPlayerName() {
//		return this.playerName;
//	}
//
//	private void run() {
//		guiThread.run();
//	}

	public static void main(String[] args) {

		String playerName = "Default";
		ConnectionManager connectionManager;
		ClientGUIThread guiThread = new ClientGUIThread();

		
		// get player name
		String name = JOptionPane
				.showInputDialog("Insert your name:", "Player");
		playerName = name;
		// get player connection mode
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
			// setup sockets
			connectionManager = new ConnectionManagerSocket(playerName);
		}
		// if connection = 0 -> RMI
		// if connection = 1 -> Socket
		// connect to the server
		connectionManager.setPlayerName(name);
		guiThread.setConnectionManager(connectionManager);
		
		guiThread.getConnectionManager().setup();
		// acquire map

		guiThread.run();
	}
}
