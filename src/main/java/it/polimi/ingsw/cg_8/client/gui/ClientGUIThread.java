package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class ClientGUIThread implements Runnable {
	JFrame mainFrame;
	JPanel chatPanel, rightPanel, infoPanel, mapPanel, commandsPanel;
	JButton moveButton, attackButton, drawButton, endTurnButton,
			fakeNoiseButton, useItemCardButton, chatButton;
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

		// set up map pane
		JTextArea mapImageArea = new JTextArea() {
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

		// set up info panel
		infoPanel.add(infoTextPane);
		infoTextPane.setEditable(false);

		// set up chat panel
		chatPanel.add(chatTextPane, BorderLayout.NORTH);
		chatPanel.add(chatTextField, BorderLayout.CENTER);
		chatPanel.add(chatButton, BorderLayout.SOUTH);
		// send chat message when send button is pressed
		chatButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = chatTextField.getText();
				chatTextField.setText("");
				//TODO: create chat action
			}
			
		});
		// send chat message when enter is pressed
		chatTextField.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(KeyEvent.VK_ENTER == e.getKeyCode()){
					String message = chatTextField.getText();
					chatTextField.setText("");
					//TODO: create chat action
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
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
		mainFrame.setSize(1920, 1080);
//		mainFrame.pack();

		chatTextPane.setText("CHAT TEXT PANE TEST");
		infoTextPane.setText("INFO TEXT PANE TEST");
	}

	@Override
	public void run() {

	}

}
