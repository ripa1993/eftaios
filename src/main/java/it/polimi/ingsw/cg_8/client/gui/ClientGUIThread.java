package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class ClientGUIThread implements Runnable {
	JFrame mainFrame;
	JPanel chatPanel, chatPanel2, rightPanel, infoPanel, commandsPanel,
			chatInfoPanel;
	JLayeredPane mapPanel;
	JButton moveButton, attackButton, drawButton, endTurnButton,
			fakeNoiseButton, useItemCardButton, chatButton;
	JTextPane chatTextPane, infoTextPane;
	JTextField chatTextField;
	JLabel backgroundMap;
	JLabel chatTextTitle;
	private JLabel infoTextTitle;
	JScrollPane chatScroll, infoScroll;

	public ClientGUIThread() {
		mainFrame = new JFrame("Escape From The Aliens In Outer Space");
		chatPanel = new JPanel();
		rightPanel = new JPanel();
		infoPanel = new JPanel();
		chatInfoPanel = new JPanel();
		mapPanel = new JLayeredPane();
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
		chatTextField = new JTextField();
		chatPanel2 = new JPanel();
		backgroundMap = new JLabel();
		infoScroll = new JScrollPane(infoTextPane);
		chatScroll = new JScrollPane(chatTextPane);

		// add black background color
//		chatPanel2.setBackground(Color.DARK_GRAY);
//		infoPanel.setBackground(Color.DARK_GRAY);
//		mapPanel.setBackground(Color.DARK_GRAY);
//		commandsPanel.setBackground(Color.DARK_GRAY);

		// add exit behaviour
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set layouts
		mainFrame.setLayout(new BorderLayout());
		chatPanel.setLayout(new BorderLayout());
		infoPanel.setLayout(new BorderLayout());
		mapPanel.setLayout(new BorderLayout());
		chatPanel2.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());

		// set borders
		chatPanel2.setBorder(new EmptyBorder(10, 10, 10, 10));
		infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mapPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		commandsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// set up map panel
		JLabel mapImageArea = new JLabel() {
			Image image = (new ImageIcon(Resource.FERMI_MAP)).getImage();
			{
				setOpaque(false);
			}

			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				super.paint(g);
			}
		};
		mapPanel.add(mapImageArea);
		mapPanel.setVisible(true);

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
		infoTextTitle = new JLabel();
		infoTextTitle.setText("INFORMATION");
		infoTextTitle.setForeground(Color.RED);
		infoPanel.add(infoTextTitle, BorderLayout.NORTH);
		infoPanel.add(infoScroll, BorderLayout.CENTER);
		infoTextPane.setEditable(false);

		// set up chat panel
		chatTextTitle = new JLabel();
		chatTextTitle.setText("CHAT");
		chatTextTitle.setForeground(Color.RED);
		chatPanel.add(chatTextTitle, BorderLayout.NORTH);
		chatPanel.add(chatScroll, BorderLayout.CENTER);
		chatPanel.add(chatTextField, BorderLayout.SOUTH);
		chatTextPane.setEditable(false);

		chatInfoPanel.setLayout(new GridLayout(2, 1));
		chatInfoPanel.add(infoPanel);
		chatInfoPanel.add(chatPanel2);
		chatPanel2.add(chatPanel, BorderLayout.CENTER);
		chatPanel2.add(chatButton, BorderLayout.SOUTH);
		rightPanel.add(chatInfoPanel, BorderLayout.CENTER);
		rightPanel.add(commandsPanel, BorderLayout.SOUTH);
		mainFrame.add(mapPanel, BorderLayout.CENTER);
		mainFrame.add(rightPanel, BorderLayout.EAST);
		chatPanel2.setVisible(true);
		chatPanel.setVisible(true);
		infoPanel.setVisible(true);
		mapPanel.setVisible(true);
		mainFrame.setVisible(true);
		mainFrame.setSize(1280, 720);

		chatTextPane.setText("");
		infoTextPane.setText("");
	}

	public void appendChat(String player, String msg) {
		String newMsg = chatTextPane.getText();
		newMsg += "\n" + player + ": " + msg;
		chatTextPane.setText(newMsg);
	}

	public void appendInfo(String type, String msg) {
		String newMsg = infoTextPane.getText();
		newMsg += "\n[" + type + "] " + msg;
		infoTextPane.setText(newMsg);
	}

	@Override
	public void run() {
		moveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String coordinateString = JOptionPane.showInputDialog(
						"Insert destination coordinate", "Coordinate");
				try {
					Coordinate coordinate = ActionParser
							.parseCoordinate(coordinateString);
					// TODO: make movement
				} catch (NotAValidInput e1) {
					JOptionPane.showMessageDialog(mainFrame,
							"Not a valid input!");
				}
			}

		});

		attackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane optionPane = new JOptionPane(
						"Do you want to attack?", JOptionPane.QUESTION_MESSAGE,
						JOptionPane.YES_NO_OPTION);
				JDialog dialog = optionPane.createDialog("Attack");
				dialog.setVisible(true);
				int selection = OptionPaneUtils.getSelection(optionPane);
				if (selection == JOptionPane.YES_OPTION) {
					// TODO: do an attack
				} else {
					// do nothing
				}
			}

		});

		drawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane optionPane = new JOptionPane(
						"Do you want to draw a dangerous sector card?",
						JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				JDialog dialog = optionPane
						.createDialog("Draw a dangerous sector card");
				dialog.setVisible(true);
				int selection = OptionPaneUtils.getSelection(optionPane);
				if (selection == JOptionPane.YES_OPTION) {
					// TODO: do draw
				} else {
					// do nothing
				}
			}

		});

		fakeNoiseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String coordinateString = JOptionPane.showInputDialog(
						"Insert target coordinate", "Coordinate");
				try {
					Coordinate coordinate = ActionParser
							.parseCoordinate(coordinateString);
					// TODO: make fake noise
				} catch (NotAValidInput e1) {
					JOptionPane.showMessageDialog(mainFrame,
							"Not a valid input!");
				}
			}

		});

		useItemCardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cardList[] = { "Attack", "Adrenaline", "Sedatives",
						"Spotlight", "Teleport" };
				String output = (String) JOptionPane.showInputDialog(mainFrame,
						"Pick a card", "Input", JOptionPane.QUESTION_MESSAGE,
						null, cardList, "Attack");
				if (output.equals("Attack")) {
					// TODO: use attack card
				} else if (output.equals("Adrenaline")) {
					// TODO: use adrenaline card
				} else if (output.equals("Sedatives")) {
					// TODO: use sedatives card
				} else if (output.equals("Spotlight")) {
					// TODO: use spotlight Card
				} else if (output.equals("Teleport")) {
					// TODO: use teleport card
				}

			}

		});

		// send chat message when send button is pressed
		chatButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = chatTextField.getText();
				chatTextField.setText("");
				// TODO: create chat action
			}

		});
		// send chat message when enter is pressed
		chatTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					String message = chatTextField.getText();
					chatTextField.setText("");
					// TODO: create chat action
					appendChat("me", message);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

}
