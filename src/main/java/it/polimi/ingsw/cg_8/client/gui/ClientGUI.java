package it.polimi.ingsw.cg_8.client.gui;

import javax.swing.JOptionPane;

public class ClientGUI {
	private String playerName;

	public ClientGUI() {
		playerName = "Default";
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	private void run() {
		ClientGUIThread a = new ClientGUIThread();
		a.run();
	}

	public static void main(String[] args) {
		ClientGUI gui = new ClientGUI();
		String name = JOptionPane.showInputDialog("Insert your name:", "Player");
		gui.setPlayerName(name);
		gui.run();
	}
}
