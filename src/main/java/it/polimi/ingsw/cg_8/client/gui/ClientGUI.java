package it.polimi.ingsw.cg_8.client.gui;

import javax.swing.JOptionPane;

public class ClientGUI {
	private String playerName;
	private ConnectionManager connectionManager;
	private ClientGUIThread guiThread;
	
	public ClientGUI() {
		playerName = "Default";
		connectionManager = new ConnectionManager();
		guiThread = new ClientGUIThread(connectionManager);
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public ConnectionManager getConnectionManager() {
		return connectionManager;
	}

	public ClientGUIThread getGuiThread() {
		return guiThread;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	private void run() {
		guiThread.run();
	}

	public static void main(String[] args) {
		ClientGUI gui = new ClientGUI();
		// get player name
		String name = JOptionPane
				.showInputDialog("Insert your name:", "Player");
		gui.setPlayerName(name);
		// get player connection mode
		Object options[] = { "RMI", "Socket" };
		int connection = -1;
		while (connection == -1) {
			connection = JOptionPane.showOptionDialog(null,
					"Select the connection mode", "Connection setup",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);
		}
		// if connection = 0 -> RMI
		// if connection = 1 -> Socket
		// connect to the server
		gui.getConnectionManager().setPlayerName(name);
		gui.getConnectionManager().setGuiThread(gui.getGuiThread());
		if (connection == 0){
			gui.getConnectionManager().setupRMI();
		
		}
		else{
			// setup sockets
			gui.getConnectionManager().setupSocket();
		}
		
		// acquire map

		gui.run();
	}
}
