package it.polimi.ingsw.cg_8.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class ClientGUIThread implements Runnable {
	JFrame mainFrame;
	JPanel chatPanel, rightPanel, infoPanel, mapPanel, commandsPanel;
	JButton moveButton, attackButton, drawButton, endTurnButton, fakeNoiseButton, useItemCardButton, chatButton;
	JTextPane chatTextPane, infoTextPane;
	JTextField chatTextField;

	public ClientGUIThread() {
		mainFrame = new JFrame("Escape From The Aliens In Outer Space");
		chatPanel = new JPanel();
		rightPanel = new JPanel();
		infoPanel = new JPanel();
		mapPanel = new JPanel();
		commandsPanel = new JPanel();
		moveButton = new JButton("Movement");
		attackButton = new JButton("Attack");
		drawButton = new JButton("Draw");
		endTurnButton = new JButton("End Turn");
		fakeNoiseButton = new JButton("Do Fake Noise");
		useItemCardButton = new JButton("Use Item Card");
		chatButton = new JButton("Send");
		chatTextPane = new JTextPane();
		infoTextPane = new JTextPane();
		chatTextField = new JTextField(1);
		
		
		
		// add background color
		chatPanel.setBackground(Color.BLUE);
		infoPanel.setBackground(Color.CYAN);
		mapPanel.setBackground(Color.GREEN);
		commandsPanel.setBackground(Color.DARK_GRAY);
		mainFrame.setBackground(Color.YELLOW);
		rightPanel.setBackground(Color.PINK);

		// add exit behaviour
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set layouts
		mainFrame.setLayout(new BorderLayout());
		chatPanel.setLayout(new BorderLayout());
		infoPanel.setLayout(new BorderLayout());
		mapPanel.setLayout(new BorderLayout());

		chatPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mapPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		chatPanel.add(new JLabel("Chat"));
		infoPanel.add(new JLabel("Info"));
		mapPanel.add(new JLabel("Map"));

		// set up commands jpanel
		commandsPanel.setLayout(new FlowLayout());
		commandsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		commandsPanel.add(moveButton);
		commandsPanel.add(attackButton);
		commandsPanel.add(drawButton);
		commandsPanel.add(fakeNoiseButton);
		commandsPanel.add(useItemCardButton);
		commandsPanel.add(endTurnButton);
		commandsPanel.setVisible(true);

		// set up info panel
		infoPanel.add(infoTextPane);
		infoTextPane.setEditable(false);
		
		//set up chat panel
		chatPanel.add(chatTextPane, BorderLayout.NORTH);
		chatPanel.add(chatTextField, BorderLayout.CENTER);
		chatPanel.add(chatButton, BorderLayout.SOUTH);
		chatTextPane.setEditable(false);
		
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(chatPanel, BorderLayout.NORTH);
		rightPanel.add(infoPanel, BorderLayout.CENTER);
		rightPanel.add(commandsPanel, BorderLayout.SOUTH);
		
		mainFrame.add(mapPanel, BorderLayout.CENTER);
		mainFrame.add(rightPanel, BorderLayout.EAST);
		chatPanel.setVisible(true);
		infoPanel.setVisible(true);
		mapPanel.setVisible(true);
		mainFrame.setVisible(true);
		mainFrame.pack();

		chatTextPane.setText("CHAT TEXT PANE TEST");
		infoTextPane.setText("INFO TEXT PANE TEST");
	}

	@Override
	public void run() {

	}

}
