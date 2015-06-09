package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.client.ClientData;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.noises.AttackNoise;
import it.polimi.ingsw.cg_8.model.noises.DefenseNoise;
import it.polimi.ingsw.cg_8.model.noises.EscapeSectorNoise;
import it.polimi.ingsw.cg_8.model.noises.MovementNoise;
import it.polimi.ingsw.cg_8.model.noises.SpotlightNoise;
import it.polimi.ingsw.cg_8.model.noises.TeleportNoise;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.actions.ActionAttack;
import it.polimi.ingsw.cg_8.view.client.actions.ActionChat;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDrawCard;
import it.polimi.ingsw.cg_8.view.client.actions.ActionEndTurn;
import it.polimi.ingsw.cg_8.view.client.actions.ActionFakeNoise;
import it.polimi.ingsw.cg_8.view.client.actions.ActionMove;
import it.polimi.ingsw.cg_8.view.client.actions.ActionUseCard;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;
import it.polimi.ingsw.cg_8.view.server.ResponseChat;
import it.polimi.ingsw.cg_8.view.server.ResponseNoise;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
import javax.swing.event.MouseInputAdapter;

/**
 * Class that defines the GUI.
 * 
 * @author Alberto Parravicini
 * @version 1.1
 */
public class ClientGUIThread implements Runnable, Observer {

	/**
	 * The server messages are stored here.
	 */
	private ClientData clientData;
	private JFrame mainFrame;
	private JPanel chatPanel, chatPanel2, rightPanel, infoPanel, commandsPanel,
			chatInfoPanel, mapPanel;
	private JButton moveButton, attackButton, drawButton, endTurnButton,
			fakeNoiseButton, useItemCardButton, chatButton;
	private JTextPane chatTextPane, infoTextPane;
	private JTextField chatTextField;
	private JLabel infoTextTitle, chatTextTitle;
	private JScrollPane chatScroll, infoScroll;
	private ConnectionManager connectionManager;
	private String backgroundImageResource;
	private Image backgroundImageScaled;
	private ImageIcon backgroundImage;

	public ClientGUIThread() {
		mainFrame = new JFrame("Escape From The Aliens In Outer Space");
		chatPanel = new JPanel();
		rightPanel = new JPanel();
		infoPanel = new JPanel();
		chatInfoPanel = new JPanel();
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
		infoScroll = new JScrollPane(infoTextPane);
		chatScroll = new JScrollPane(chatTextPane);
		backgroundImageResource = Resource.IMG_GALILEI_MAP;
		backgroundImage = new ImageIcon(backgroundImageResource);
		backgroundImageScaled = new ImageIcon(backgroundImage.getImage()
				.getScaledInstance(1920, -1, Image.SCALE_SMOOTH)).getImage();

		// add exit behaviour
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		// setup map panel
		mapPanel = new JPanel() {

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				int updatedWidth = this.getWidth();
				int updatedHeight = this.getHeight();

				if (this.getWidth() - backgroundImageScaled.getWidth(null) > this
						.getHeight() - backgroundImageScaled.getHeight(null)) {
					updatedWidth = updatedHeight
							* backgroundImageScaled.getWidth(null)
							/ backgroundImageScaled.getHeight(null);
				}
				if (this.getWidth() - backgroundImageScaled.getWidth(null) < this
						.getHeight() - backgroundImageScaled.getHeight(null)) {
					updatedHeight = updatedWidth
							* backgroundImageScaled.getHeight(null)
							/ backgroundImageScaled.getWidth(null);
				}

				int x = (this.getWidth() - updatedWidth) / 2;
				int y = (this.getHeight() - updatedHeight) / 2;
				g.drawImage(backgroundImageScaled, x, y, updatedWidth,
						updatedHeight, null);
			}

			public Image getBackgroundImageScaled() {
				return backgroundImageScaled;
			}
		};
		mapPanel.setVisible(true);

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

	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.clientData = connectionManager.getClientData();
		clientData.addObserver(this);

	}

	public ConnectionManager getConnectionManager() {
		return this.connectionManager;
	}

	@Override
	public void run() {
		moveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String coordinateString = JOptionPane.showInputDialog(
						"Insert destination coordinate", "Coordinate");
				if (coordinateString != null) {
					try {

						Coordinate coordinate = ActionParser
								.parseCoordinate(coordinateString);
						connectionManager.send(new ActionMove(coordinate));

					} catch (NotAValidInput e1) {
						JOptionPane.showMessageDialog(mainFrame,
								"Not a valid input!");
					}
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
					connectionManager.send(new ActionAttack());
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
					connectionManager.send(new ActionDrawCard());
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
				if (coordinateString != null) {
					try {
						Coordinate coordinate = ActionParser
								.parseCoordinate(coordinateString);
						// TODO: make fake noise
						connectionManager.send(new ActionFakeNoise(coordinate));

					} catch (NotAValidInput e1) {
						JOptionPane.showMessageDialog(mainFrame,
								"Not a valid input!");
					}
				}
			}

		});

		endTurnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane optionPane = new JOptionPane(
						"Do you want to end your turn?",
						JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
				JDialog dialog = optionPane.createDialog("End turn");
				dialog.setVisible(true);
				int selection = OptionPaneUtils.getSelection(optionPane);
				if (selection == JOptionPane.YES_OPTION) {
					connectionManager.send(new ActionEndTurn());
				} else {
					// do nothing
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
					connectionManager.send(new ActionUseCard(new AttackCard()));
				} else if (output.equals("Adrenaline")) {
					connectionManager.send(new ActionUseCard(
							new AdrenalineCard()));

				} else if (output.equals("Sedatives")) {
					connectionManager.send(new ActionUseCard(
							new SedativesCard()));

				} else if (output.equals("Spotlight")) {
					String coordinateString = JOptionPane.showInputDialog(
							"Insert target coordinate", "Coordinate");
					try {
						Coordinate coordinate = ActionParser
								.parseCoordinate(coordinateString);
						connectionManager.send(new ActionUseCard(
								new SpotlightCard(), coordinate));
					} catch (NotAValidInput e1) {
						JOptionPane.showMessageDialog(mainFrame,
								"Not a valid input!");
					}
				} else if (output.equals("Teleport")) {
					connectionManager
							.send(new ActionUseCard(new TeleportCard()));

				}

			}

		});

		// send chat message when send button is pressed
		chatButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String message = chatTextField.getText();
				chatTextField.setText("");
				connectionManager.send(new ActionChat(message));
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
					connectionManager.send(new ActionChat(message));
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		mapPanel.addMouseListener(new MouseInputAdapter() {
			private final static int NUM_COLUMN = 23;
			private final static int NUM_ROW = 29;

			private Coordinate getCoordinate(MouseEvent e){
				// result coordinates
				int col = 0;
				int row = 0;

				// click coordinates
				int mouseX = e.getX();
				int mouseY = e.getY();
				// mapPanel sizes
				int mapPanelWidth = mapPanel.getWidth();
				int mapPanelHeight = mapPanel.getHeight();
				// background image sizes
				float mapImageWidth = mapPanelWidth;
				float mapImageHeight = mapPanelHeight;
				if (mapImageWidth - backgroundImageScaled.getWidth(null) > mapImageHeight
						- backgroundImageScaled.getHeight(null)) {
					mapImageWidth = mapImageHeight
							* backgroundImageScaled.getWidth(null)
							/ backgroundImageScaled.getHeight(null);
				} else {
					mapImageHeight = mapImageWidth
							* backgroundImageScaled.getHeight(null)
							/ backgroundImageScaled.getWidth(null);

				}
				// border sizes
				float panelBorderWidth = (mapPanelWidth - mapImageWidth) / 2;
				float panelBorderHeigth = (mapPanelHeight - mapImageHeight) / 2;
				// calculate col and row size
				float columnWidth = mapImageWidth / NUM_COLUMN;
				float rowHeigth = mapImageHeight / NUM_ROW;

				// calculate coordinate
				float imageMouseX = mouseX - panelBorderWidth;
				float imageMouseY = mouseY - panelBorderHeigth;

				// calculate column
				for (int i = 0; i <= NUM_COLUMN; i++) {
					if (imageMouseX >= i * columnWidth
							&& imageMouseX <= (i + 1) * columnWidth) {
						col = i;
						break;
					}
				}
				// calculate row
				for (int i = 0; i <= NUM_ROW; i++) {
					if (imageMouseY >= i * rowHeigth
							&& imageMouseY <= (i + 1) * rowHeigth) {
						row = i;
						break;
					}
				}
				if(col%2!=0){
					row--;
				}
				row = (int) Math.floor((double)(row/2));
				return new Coordinate(col, row);
			}
			
			
			@Override
			public void mouseReleased(MouseEvent e) {
				Coordinate coordinate = getCoordinate(e);
				Object[] options = {"Movement", "Spotlight", "Do Fake Noise"};
				Object result = JOptionPane.showOptionDialog(null, "This is sector "+coordinate, "What would you like to do?",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[0]);
				if (result.equals(options[0])){
					connectionManager.send(new ActionMove(coordinate));
				} else if (result.equals(options[1])){
					connectionManager.send(new ActionUseCard(
							new SpotlightCard(), coordinate));
				} else if (result.equals(options[2])){
					connectionManager.send(new ActionFakeNoise(coordinate));
				}
				
			}
		});
	}

	/**
	 * When a new message is added to {@link ClientData}, the GUI is notified
	 * and displays the message on screen.
	 */
	@Override
	public void update(Observable o, Object arg) {

		if (arg.equals("Chat")) {
			ResponseChat chat = clientData.getLastChat();
			this.appendChat(chat.getPlayerName(), chat.getMessage());

			// play music
			playSound(Resource.SOUND_NOTIFICATION);
		} else if (arg.equals("Noise")) {
			ResponseNoise noise = clientData.getLastNoise();
			this.appendInfo("NOISE", noise.toString());

			// play music
			if (noise.getNoise() instanceof AttackNoise) {
				Random random = new Random();
				int n = random.nextInt(2);
				if (((AttackNoise) noise.getNoise()).isAlien()) {
					if (n == 0) {
						playSound(Resource.SOUND_ALIEN_ATTACK_1);
					} else if (n == 1) {
						playSound(Resource.SOUND_ALIEN_ATTACK_2);
					} else {
						playSound(Resource.SOUND_ALIEN_ATTACK_3);
					}
				} else {
					if (n == 0) {
						playSound(Resource.SOUND_HUMAN_ATTACK_1);
					} else if (n == 1) {
						playSound(Resource.SOUND_HUMAN_ATTACK_2);
					} else {
						playSound(Resource.SOUND_HUMAN_ATTACK_3);
					}
				}
			} else if (noise.getNoise() instanceof DefenseNoise) {
				playSound(Resource.SOUND_DEFENSE);
			} else if (noise.getNoise() instanceof EscapeSectorNoise) {
				playSound(Resource.SOUND_ESCAPE_HATCH);
			} else if (noise.getNoise() instanceof MovementNoise) {
				playSound(Resource.SOUND_MOVEMENT_NOISE);
			} else if (noise.getNoise() instanceof SpotlightNoise) {
				playSound(Resource.SOUND_SPOTLIGHT);
			} else if (noise.getNoise() instanceof TeleportNoise) {
				playSound(Resource.SOUND_TELEPORT);
			}
		} else {
			ResponsePrivate privateMessage = clientData.getLastPrivate();
			this.appendInfo("INFO", privateMessage.getMessage());
		}

	}

	private void playSound(String filePath) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(filePath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

}
