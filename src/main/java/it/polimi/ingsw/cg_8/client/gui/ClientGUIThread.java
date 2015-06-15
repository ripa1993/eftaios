package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.client.ClientData;
import it.polimi.ingsw.cg_8.client.ConnectionManager;
import it.polimi.ingsw.cg_8.client.gui.CardButton.CardType;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.map.GameMapName;
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
import it.polimi.ingsw.cg_8.view.client.actions.ActionDisconnect;
import it.polimi.ingsw.cg_8.view.client.actions.ActionDrawCard;
import it.polimi.ingsw.cg_8.view.client.actions.ActionEndTurn;
import it.polimi.ingsw.cg_8.view.client.actions.ActionFakeNoise;
import it.polimi.ingsw.cg_8.view.client.actions.ActionMove;
import it.polimi.ingsw.cg_8.view.client.actions.ActionUseCard;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;
import it.polimi.ingsw.cg_8.view.server.ResponseCard;
import it.polimi.ingsw.cg_8.view.server.ResponseChat;
import it.polimi.ingsw.cg_8.view.server.ResponseMap;
import it.polimi.ingsw.cg_8.view.server.ResponseNoise;
import it.polimi.ingsw.cg_8.view.server.ResponsePrivate;
import it.polimi.ingsw.cg_8.view.server.ResponseState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.imageio.ImageIO;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.DefaultCaret;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that defines the GUI.
 * 
 * @author Alberto Parravicini
 * @version 1.1
 */
public class ClientGUIThread implements Runnable, Observer {
	/**
	 * Attack text
	 */
	private static final String ATTACK_TEXT = "Attack";
	/**
	 * Adrenaline text
	 */
	private static final String ADRENALINE_TEXT = "Adrenaline";
	/**
	 * Sedatives text
	 */
	private static final String SEDATIVES_TEXT = "Sedatives";
	/**
	 * Teleport text
	 */
	private static final String TELEPORT_TEXT = "Teleport";
	/**
	 * Coordinate text
	 */
	private static final String COORDINATE_TEXT = "Coordinate";
	/**
	 * Not a valid input text
	 */
	private static final String NOT_VALID_INPUT_TEXT = "Not a valid input!";
	/**
	 * Spotlight text
	 */
	private static final String SPOTLIGHT_TEXT = "Spotlight";
	/**
	 * The server messages are stored here.
	 */
	private ClientData clientData;
	/**
	 * Connection manager used to handle communication with the server
	 */
	private ConnectionManager connectionManager;
	/**
	 * Main frame
	 */
	private JFrame mainFrame;
	/**
	 * JPanels used in the gui
	 */
	private JPanel chatPanel, chatPanel2, rightPanel, infoPanel, commandsPanel,
			chatInfoPanel, statePanel, panel1, panel2, panel3, cardPanel;
	/**
	 * JLayered pane used in the map panel
	 */
	private MapPanel mapPanel;
	/**
	 * JButton used in the gui
	 */
	private JButton moveButton, attackButton, drawButton, endTurnButton,
			fakeNoiseButton, useItemCardButton, chatButton;
	/**
	 * JTextPane used in the gui
	 */
	private JTextPane chatTextPane, infoTextPane;
	/**
	 * JTextField used to get player chat messages
	 */
	private JTextField chatTextField;
	/**
	 * JLabel used in the gui
	 */
	private JLabel infoTextTitle, chatTextTitle, lblPlayerState, lblItemCards,
			labelCurrentState, stateImage, turnNumberLabel;
	/**
	 * JScrollPane used to add a scroll to the chat and info text pane
	 */
	private JScrollPane chatScroll, infoScroll;
	/**
	 * CardButton used to display player item cards
	 */
	private CardButton cardButton1, cardButton2, cardButton3;
	/**
	 * GUI's custom fonts
	 */
	private Font fontTitilliumBoldUpright, fontTitilliumSemiboldUpright;
	/**
	 * Flag that show if the match has started
	 */
	private boolean matchStarted;
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager
			.getLogger(ClientGUIThread.class);
	/**
	 * Shows if the player image has been set or not.
	 */
	private boolean playerImageSet;

	/**
	 * Constructor, create the main frame for the gui. Instead of the map shows
	 * a temp image that changes when the game starts and the server
	 * communicates the game map. Player can click on the sectors on the left
	 * and a popup show the available sector specific actions (move, spotlight,
	 * fake noise). On the right, the player can see its state and round number,
	 * the item cards in his hand (clickable), a text pane where he can see
	 * infos about the match, a text pane where he can see chat message and
	 * write his own. On the right bottom there are the clickable action button,
	 * that in case show a popup when clicked
	 */
	public ClientGUIThread() {
		matchStarted = false;
		playerImageSet = false;
		try {
			fontTitilliumBoldUpright = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream(Resource.FONT_TITILLIUM_BOLD_UPRIGHT))
					.deriveFont((float) 30);
		} catch (FontFormatException | IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		try {
			fontTitilliumSemiboldUpright = Font.createFont(
					Font.TRUETYPE_FONT,
					new FileInputStream(
							Resource.FONT_TITILLIUM_SEMIBOLD_UPRIGHT))
					.deriveFont((float) 20);
		} catch (FontFormatException | IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		mainFrame = new JFrame("Escape From The Aliens In Outer Space");
		mainFrame.setMinimumSize(new Dimension(1280, 720));
		mainFrame.setResizable(true);
		BufferedImage myImage;
		try {
			myImage = ImageIO.read(new File(Resource.IMG_BACKGROUND_PATTERN));

			mainFrame.setContentPane(new BackgroundPanel(myImage));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		mainFrame.getContentPane().setBackground(Color.PINK);
		mainFrame.setBackground(new Color(255, 255, 255));
		chatPanel = new JPanel();
		chatPanel.setOpaque(false);
		chatPanel.setBackground(Color.WHITE);
		rightPanel = new JPanel();
		rightPanel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(64,
				64, 64, 150)));
		rightPanel.setOpaque(false);
		infoPanel = new JPanel();
		infoPanel.setMaximumSize(new Dimension(600, 32767));
		infoPanel.setOpaque(false);
		infoPanel.setBackground(Color.WHITE);
		chatInfoPanel = new JPanel();
		chatInfoPanel.setOpaque(false);
		chatInfoPanel.setBackground(Color.WHITE);
		commandsPanel = new JPanel();
		commandsPanel.setBackground(Color.WHITE);
		commandsPanel.setOpaque(false);
		moveButton = new JButton("Movement");
		attackButton = new JButton(ATTACK_TEXT);
		drawButton = new JButton("Draw");
		endTurnButton = new JButton("End Turn");
		fakeNoiseButton = new JButton("Do Fake Noise");
		useItemCardButton = new JButton("Use Item Card");
		chatButton = new JButton("Send");
		chatTextPane = new JTextPane();
		infoTextPane = new JTextPane();
		chatTextField = new JTextField();
		chatTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chatTextField.setForeground(Color.WHITE);
		chatTextField.setBackground(Color.DARK_GRAY);
		chatPanel2 = new JPanel();
		chatPanel2.setMaximumSize(new Dimension(600, 32767));
		chatPanel2.setOpaque(false);
		chatPanel2.setBackground(Color.WHITE);
		infoScroll = new JScrollPane(infoTextPane);
		chatScroll = new JScrollPane(chatTextPane);

		/**
		 * Which map is loaded.
		 */

		// add exit behaviour
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// setup map panel
		mapPanel = new MapPanel();
		mapPanel.setBackground(Color.WHITE);
		mapPanel.setVisible(true);
		mapPanel.setMapImage(Resource.IMG_MAP_BG);
		// set layouts
		mainFrame.getContentPane().setLayout(new BorderLayout());

		mainFrame.getContentPane().add(mapPanel, BorderLayout.CENTER);
		mainFrame.getContentPane().add(rightPanel, BorderLayout.EAST);
		chatPanel.setLayout(new BorderLayout());
		infoPanel.setLayout(new BorderLayout());
		mapPanel.setLayout(new BorderLayout());
		chatPanel2.setLayout(new BorderLayout());
		rightPanel.setLayout(new BorderLayout());

		// set borders
		chatPanel2.setBorder(new EmptyBorder(10, 10, 10, 10));
		infoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		// mapPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
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

		statePanel = new JPanel();
		statePanel.setMaximumSize(new Dimension(600, 32767));
		statePanel.setBorder(new MatteBorder(10, 0, 0, 0, new Color(100, 100,
				100, 100)));
		statePanel.setOpaque(false);
		rightPanel.add(statePanel, BorderLayout.NORTH);
		statePanel.setLayout(new BorderLayout(0, 0));

		panel3 = new JPanel();
		panel3.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(100,
				100, 100, 100)));
		panel3.setBackground(new Color(100, 100, 100, 100));
		panel3.setOpaque(true);
		statePanel.add(panel3, BorderLayout.CENTER);
		panel3.setLayout(new BorderLayout(0, 0));

		stateImage = new JLabel("");
		panel3.add(stateImage, BorderLayout.WEST);
		stateImage.setBorder(new EmptyBorder(0, 60, 5, 0));

		/**
		 * Set the default image for the player, changed as soon as he gets an
		 * in-game character.
		 */
		setStateImage(Resource.IMG_UNKNOWN_CHAR);

		panel1 = new JPanel();
		panel3.add(panel1, BorderLayout.CENTER);
		panel1.setOpaque(false);
		panel1.setBackground(Color.WHITE);
		panel1.setLayout(new BorderLayout(0, 0));
		panel1.setBorder(new EmptyBorder(0, 0, 0, 0));

		lblPlayerState = new JLabel();
		lblPlayerState.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel1.add(lblPlayerState, BorderLayout.NORTH);
		lblPlayerState.setFont(fontTitilliumBoldUpright);
		lblPlayerState.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerState.setText("PLAYER STATE");
		lblPlayerState.setForeground(Color.BLACK);

		labelCurrentState = new JLabel();
		labelCurrentState.setBorder(new EmptyBorder(0, 0, 5, 0));
		panel1.add(labelCurrentState, BorderLayout.CENTER);
		labelCurrentState.setText("The game hasn't started yet");
		labelCurrentState.setFont(fontTitilliumSemiboldUpright);
		labelCurrentState.setHorizontalAlignment(SwingConstants.CENTER);
		labelCurrentState.setForeground(Color.BLACK);

		turnNumberLabel = new JLabel("0");
		turnNumberLabel.setFont(fontTitilliumBoldUpright);
		try {
			Image tempImage = ImageIO.read(new File(Resource.IMG_TURN_BG));
			Image roundImage = tempImage.getScaledInstance(60, -1,
					Image.SCALE_SMOOTH);
			turnNumberLabel.setIcon(new ImageIcon(roundImage));
			rightPanel.repaint();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		turnNumberLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		turnNumberLabel.setBorder(new EmptyBorder(0, 0, 5, 60));
		panel3.add(turnNumberLabel, BorderLayout.EAST);
		turnNumberLabel.setVisible(true);

		panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setBackground(Color.WHITE);
		panel2.setBorder(new EmptyBorder(10, 0, 0, 0));
		statePanel.add(panel2, BorderLayout.SOUTH);
		panel2.setLayout(new BorderLayout(0, 0));

		lblItemCards = new JLabel();
		lblItemCards.setFont(fontTitilliumBoldUpright);
		lblItemCards.setBackground(Color.WHITE);
		lblItemCards.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemCards.setText("ITEM CARDS");
		lblItemCards.setForeground(Color.BLACK);
		panel2.add(lblItemCards, BorderLayout.CENTER);

		cardPanel = new JPanel();
		cardPanel.setOpaque(false);
		cardPanel.setBackground(Color.WHITE);
		panel2.add(cardPanel, BorderLayout.SOUTH);
		cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		cardButton1 = new CardButton();
		cardButton1.setBackground(Color.WHITE);
		cardButton1.setCardType(CardType.DEFAULT);
		cardButton1.setFocusable(true);
		cardPanel.add(cardButton1);

		cardButton2 = new CardButton();
		cardButton2.setBackground(Color.WHITE);
		cardButton2.setCardType(CardType.DEFAULT);
		cardButton2.setFocusable(true);
		cardPanel.add(cardButton2);

		cardButton3 = new CardButton();
		cardButton3.setBackground(Color.WHITE);
		cardButton3.setCardType(CardType.DEFAULT);
		cardButton3.setFocusable(true);
		cardPanel.add(cardButton3);

		// set up info panel
		infoTextTitle = new JLabel();
		infoTextTitle.setFont(fontTitilliumBoldUpright);
		infoTextTitle.setText("INFORMATION");
		infoTextTitle.setForeground(Color.BLACK);
		infoPanel.add(infoTextTitle, BorderLayout.NORTH);
		infoPanel.add(infoScroll, BorderLayout.CENTER);
		infoTextPane.setEditable(false);

		DefaultCaret caretInfo = (DefaultCaret) infoTextPane.getCaret();
		caretInfo.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		// set up chat panel
		chatTextTitle = new JLabel();
		chatTextTitle.setFont(fontTitilliumBoldUpright);
		chatTextTitle.setBackground(Color.WHITE);
		chatTextTitle.setText("CHAT");
		chatTextTitle.setForeground(Color.BLACK);

		JPanel chatTextAndButton;
		chatTextAndButton = new JPanel();
		chatTextAndButton.setLayout(new BorderLayout());
		chatTextAndButton.add(chatTextField, BorderLayout.CENTER);
		chatTextAndButton.add(chatButton, BorderLayout.EAST);

		chatPanel.add(chatTextTitle, BorderLayout.NORTH);
		chatPanel.add(chatScroll, BorderLayout.CENTER);
		chatPanel.add(chatTextAndButton, BorderLayout.SOUTH);
		chatTextPane.setEditable(false);

		DefaultCaret caretChat = (DefaultCaret) chatTextPane.getCaret();
		caretChat.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		chatInfoPanel.setLayout(new GridLayout(2, 1));
		chatInfoPanel.add(infoPanel);
		chatInfoPanel.add(chatPanel2);
		chatPanel2.add(chatPanel, BorderLayout.CENTER);
		rightPanel.add(chatInfoPanel, BorderLayout.CENTER);
		rightPanel.add(commandsPanel, BorderLayout.SOUTH);

		chatTextPane.setFont(fontTitilliumSemiboldUpright);
		infoTextPane.setFont(fontTitilliumSemiboldUpright);
		chatTextPane.setText("Say hi to the other players!");
		infoTextPane.setText("Welcome to a new EFTAIOS game!"
				+ "\nThe map will appear when the game starts.");

		chatPanel2.setVisible(true);
		chatPanel.setVisible(true);
		infoPanel.setVisible(true);
		mapPanel.setVisible(true);
		mainFrame.setVisible(true);
		mainFrame.setSize(1280, 720);

	}

	/**
	 * Changes the player image, when the game is started
	 * 
	 * @param source
	 *            path to the image
	 */
	private void setStateImage(String source) {
		try {
			Image tempImage = ImageIO.read(new File(source));
			Image cardImage = tempImage.getScaledInstance(60, -1,
					Image.SCALE_SMOOTH);
			stateImage.setIcon(new ImageIcon(cardImage));
			rightPanel.repaint();
		} catch (IOException ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

	}

	/**
	 * Adds a new line to the chat text pane. It saves the previous content and
	 * append the new line
	 * 
	 * @param player
	 *            sender
	 * @param msg
	 *            message
	 */
	public void appendChat(String player, String msg) {
		String newMsg = chatTextPane.getText();
		newMsg += "\n" + player + ": " + msg;
		chatTextPane.setText(newMsg);
	}

	/**
	 * Adds a new line to the info text pane. It saves the previous content and
	 * append the new line
	 * 
	 * @param type
	 *            type of the message
	 * @param msg
	 *            message
	 */
	public void appendInfo(String type, String msg) {
		String newMsg = infoTextPane.getText();
		newMsg += "\n[" + type + "] " + msg;
		infoTextPane.setText(newMsg);
	}

	/**
	 * Adds a connection manager (previously created) to the gui thread
	 * 
	 * @param connectionManager
	 *            rmi or socket connection manager
	 */
	public void setConnectionManager(ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		this.clientData = connectionManager.getClientData();
		clientData.addObserver(this);

	}

	/**
	 * @return this gui connection manager
	 */
	public ConnectionManager getConnectionManager() {
		return this.connectionManager;
	}

	@Override
	public void run() {

		LOGGER.debug("Info text pane is" + infoPanel.getSize());
		cardButton1.getInvisButton().addMouseListener(new MouseInputAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				if (matchStarted) {
					ClientAction action = cardButton1.createAction();
					if (action != null) {
						connectionManager.send(action);
					} else {
						LOGGER.debug("THIS DOES NOTHING - STOP DOING IT!");
					}
				}
			}

		});

		cardButton2.getInvisButton().addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (matchStarted) {
					ClientAction action = cardButton2.createAction();
					if (action != null) {
						connectionManager.send(action);
					} else {
						LOGGER.debug("Action is passive or not an action");
					}
				}
			}
		});
		cardButton3.getInvisButton().addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (matchStarted) {
					ClientAction action = cardButton3.createAction();
					if (action != null) {
						connectionManager.send(action);
					} else {
						LOGGER.debug("Action is passive or not an action");
					}
				}
			}
		});

		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null,
						"Do you really want to quit the game?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					try {
						connectionManager.send(new ActionDisconnect());
					} catch (NullPointerException ex) {
						// if server is down
						LOGGER.error("Server is down", ex);
					}
					System.exit(0);
				}
			}
		});

		moveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchStarted) {
					String coordinateString = JOptionPane.showInputDialog(
							"Insert destination coordinate", COORDINATE_TEXT);
					if (coordinateString != null) {
						try {

							Coordinate coordinate = ActionParser
									.parseCoordinate(coordinateString);
							connectionManager.send(new ActionMove(coordinate));

						} catch (NotAValidInput e1) {
							LOGGER.error(e1.getMessage(), e1);
							JOptionPane.showMessageDialog(mainFrame,
									NOT_VALID_INPUT_TEXT);
						}
					}
				}
			}

		});

		attackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchStarted) {
					JOptionPane optionPane = new JOptionPane(
							"Do you want to attack?",
							JOptionPane.QUESTION_MESSAGE,
							JOptionPane.YES_NO_OPTION);
					JDialog dialog = optionPane.createDialog(ATTACK_TEXT);
					dialog.setVisible(true);
					int selection = ((Integer) optionPane.getValue())
							.intValue();
					if (selection == JOptionPane.YES_OPTION) {
						connectionManager.send(new ActionAttack());
					} else {
						// do nothing
					}
				}
			}

		});

		drawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchStarted) {
					JOptionPane optionPane = new JOptionPane(
							"Do you want to draw a dangerous sector card?",
							JOptionPane.QUESTION_MESSAGE,
							JOptionPane.YES_NO_OPTION);
					JDialog dialog = optionPane
							.createDialog("Draw a dangerous sector card");
					dialog.setVisible(true);
					int selection = ((Integer) optionPane.getValue())
							.intValue();
					if (selection == JOptionPane.YES_OPTION) {
						connectionManager.send(new ActionDrawCard());
					} else {
						// do nothing
					}
				}
			}

		});

		fakeNoiseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchStarted) {
					String coordinateString = JOptionPane.showInputDialog(
							"Insert target coordinate", COORDINATE_TEXT);
					if (coordinateString != null) {
						try {
							Coordinate coordinate = ActionParser
									.parseCoordinate(coordinateString);
							connectionManager.send(new ActionFakeNoise(
									coordinate));

						} catch (NotAValidInput e1) {
							LOGGER.error(e1.getMessage(), e1);
							JOptionPane.showMessageDialog(mainFrame,
									NOT_VALID_INPUT_TEXT);
						}
					}
				}
			}

		});

		endTurnButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchStarted) {
					JOptionPane optionPane = new JOptionPane(
							"Do you want to end your turn?",
							JOptionPane.QUESTION_MESSAGE,
							JOptionPane.YES_NO_OPTION);
					JDialog dialog = optionPane.createDialog("End turn");
					dialog.setVisible(true);
					int selection = ((Integer) optionPane.getValue())
							.intValue();
					if (selection == JOptionPane.YES_OPTION) {
						connectionManager.send(new ActionEndTurn());
					} else {
						// do nothing
					}
				}
			}

		});

		useItemCardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchStarted) {
					String[] cardList = { ATTACK_TEXT, ADRENALINE_TEXT,
							SEDATIVES_TEXT, SPOTLIGHT_TEXT, TELEPORT_TEXT };
					String output = (String) JOptionPane.showInputDialog(
							mainFrame, "Pick a card", "Input",
							JOptionPane.QUESTION_MESSAGE, null, cardList,
							ATTACK_TEXT);
					if (ATTACK_TEXT.equals(output)) {
						connectionManager.send(new ActionUseCard(
								new AttackCard()));
					} else if (ADRENALINE_TEXT.equals(output)) {
						connectionManager.send(new ActionUseCard(
								new AdrenalineCard()));

					} else if (SEDATIVES_TEXT.equals(output)) {
						connectionManager.send(new ActionUseCard(
								new SedativesCard()));

					} else if (SPOTLIGHT_TEXT.equals(output)) {
						String coordinateString = JOptionPane.showInputDialog(
								"Insert target coordinate", COORDINATE_TEXT);
						try {
							Coordinate coordinate = ActionParser
									.parseCoordinate(coordinateString);
							connectionManager.send(new ActionUseCard(
									new SpotlightCard(), coordinate));
						} catch (NotAValidInput e1) {
							LOGGER.error(e1.getMessage(), e1);
							JOptionPane.showMessageDialog(mainFrame,
									NOT_VALID_INPUT_TEXT);
						}
					} else if (TELEPORT_TEXT.equals(output)) {
						connectionManager.send(new ActionUseCard(
								new TeleportCard()));

					}

				}
			}

		});

		// send chat message when send button is pressed
		chatButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (matchStarted) {
					String message = chatTextField.getText();
					chatTextField.setText("");
					connectionManager.send(new ActionChat(message));
				}
			}

		});
		// send chat message when enter is pressed
		chatTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (matchStarted && KeyEvent.VK_ENTER == e.getKeyCode()) {
					String message = chatTextField.getText();
					chatTextField.setText("");
					connectionManager.send(new ActionChat(message));
				}

			}

			@Override
			public void keyTyped(KeyEvent e) {
				return;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				return;
			}

		});

		mapPanel.addMouseListener(new MouseInputAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (matchStarted) {
					Coordinate coordinate = mapPanel.getCoordinate(e);
					if (coordinate.getX() >= 0 && coordinate.getY() >= 0
							&& coordinate.getX() < MapPanel.NUM_COLUMN
							&& coordinate.getY() < MapPanel.NUM_ROW) {
						Object[] options = { "Movement", SPOTLIGHT_TEXT,
								"Do Fake Noise" };
						int result = JOptionPane.showOptionDialog(null,
								"This is sector " + coordinate,
								"What would you like to do?",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
						LOGGER.debug("Result: " + result);
						LOGGER.debug("Options: " + options);
						if (result == 0) {
							LOGGER.debug("Choose: move");
							connectionManager.send(new ActionMove(coordinate));
						} else if (result == 1) {
							LOGGER.debug("Choose: spotlight");
							connectionManager.send(new ActionUseCard(
									new SpotlightCard(), coordinate));
						} else if (result == 2) {
							LOGGER.debug("Choose: fake noise");
							connectionManager.send(new ActionFakeNoise(
									coordinate));
						}
					}

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

		if ("Chat".equals(arg)) {
			ResponseChat chat = clientData.getLastChat();
			this.appendChat(chat.getPlayerName(), chat.getMessage());

			// play music
			playSound(Resource.SOUND_NOTIFICATION);
		} else if ("Noise".equals(arg)) {
			ResponseNoise noise = clientData.getLastNoise();
			this.appendInfo("NOISE", noise.toString());
			try {
				mapPanel.createArtifact(noise.getNoise().getCoordinate(),
						Resource.IMG_YELLOW_OVER, 1000, 10);
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			}
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
		} else if ("Private".equals(arg)) {
			ResponsePrivate privateMessage = clientData.getLastPrivate();
			this.appendInfo("INFO", privateMessage.getMessage());
		}

		/**
		 * Update related to card messages.
		 */
		else if ("Cards".equals(arg)) {
			ResponseCard cardMessage = clientData.getCards();

			cardButton1
					.setCardType(this.analyzeCardType(cardMessage.getCard1()));
			LOGGER.debug("Card1 Type"
					+ this.analyzeCardType(cardMessage.getCard1()));
			cardButton1.repaint();

			cardButton2
					.setCardType(this.analyzeCardType(cardMessage.getCard2()));
			LOGGER.debug("Card2 Type"
					+ this.analyzeCardType(cardMessage.getCard2()));
			cardButton2.repaint();

			cardButton3
					.setCardType(this.analyzeCardType(cardMessage.getCard3()));
			LOGGER.debug("Card3 Type"
					+ this.analyzeCardType(cardMessage.getCard3()));
			cardButton3.repaint();

			/**
			 * Update related to state messages
			 */
		} else if ("State".equals(arg)) {
			ResponseState stateMessage = clientData.getState();
			// change
			mapPanel.createPlayerPosition(stateMessage.getPosition());
			if (!playerImageSet) {
				double random = Math.random();
				LOGGER.debug(stateMessage.getCharacter());
				if ("Human".equals(stateMessage.getCharacter())) {
					LOGGER.debug("I'm a human, so i set my img");
					if (random < 0.25) {
						setStateImage(Resource.IMG_HUMAN_1);
						mapPanel.setPath(Resource.IMG_HUMAN_1);
					} else if (random < 0.5) {
						setStateImage(Resource.IMG_HUMAN_2);
						mapPanel.setPath(Resource.IMG_HUMAN_2);

					} else if (random < 0.75) {
						setStateImage(Resource.IMG_HUMAN_3);
						mapPanel.setPath(Resource.IMG_HUMAN_3);

					} else {
						setStateImage(Resource.IMG_HUMAN_4);
						mapPanel.setPath(Resource.IMG_HUMAN_4);

					}
				} else if ("Alien".equals(stateMessage.getCharacter())) {
					LOGGER.debug("I'm an alien, so i set my img");
					cardButton1.updateOverlay(Resource.IMG_ALIEN_OVERLAY);
					cardButton2.updateOverlay(Resource.IMG_ALIEN_OVERLAY);
					cardButton3.updateOverlay(Resource.IMG_ALIEN_OVERLAY);

					if (random < 0.25) {
						setStateImage(Resource.IMG_ALIEN_1);
						mapPanel.setPath(Resource.IMG_ALIEN_1);

					} else if (random < 0.5) {
						setStateImage(Resource.IMG_ALIEN_2);
						mapPanel.setPath(Resource.IMG_ALIEN_2);

					} else if (random < 0.75) {
						setStateImage(Resource.IMG_ALIEN_3);
						mapPanel.setPath(Resource.IMG_ALIEN_3);

					} else {
						setStateImage(Resource.IMG_ALIEN_4);
						mapPanel.setPath(Resource.IMG_ALIEN_4);

					}
				}
				playerImageSet = true;
			}
			turnNumberLabel.setText(stateMessage.getRoundNumber());
			String state = stateMessage.getPlayerName() + ", "
					+ stateMessage.getCharacter() + ", State:"
					+ stateMessage.getState() + ", Position: "
					+ stateMessage.getPosition();
			labelCurrentState.setText(state);
			rightPanel.repaint();

		} else if ("Map".equals(arg)) {
			ResponseMap response = clientData.getMap();
			GameMapName mapName = response.getMapName();
			if (mapName.equals(GameMapName.FERMI)) {
				mapPanel.setMapImage(Resource.IMG_FERMI_MAP);
				LOGGER.debug("Map changed to fermi");
			} else if (mapName.equals(GameMapName.GALILEI)) {
				mapPanel.setMapImage(Resource.IMG_GALILEI_MAP);
				LOGGER.debug("Map changed to galilei");
			} else if (mapName.equals(GameMapName.GALVANI)) {
				mapPanel.setMapImage(Resource.IMG_GALVANI_MAP);
				LOGGER.debug("Map changed to galvani");
			}
			mapPanel.repaint();
			matchStarted = true;
		} else if ("Ack".equals(arg)) {
			appendInfo("ACK", clientData.getAck());
		}
	}

	/**
	 * Plays a .wav sound, used by chat messages and noises
	 * 
	 * @param filePath
	 *            path to the .wav file
	 */
	private void playSound(String filePath) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(filePath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	/**
	 * Reads a string and returns a value in the CardType enumeration
	 * 
	 * @param card
	 *            string that identifies a card type
	 * @return the correnct CardType value
	 */
	private CardType analyzeCardType(String card) {
		if ("AdrenalineCard".equals(card)) {
			return CardType.ADRENALINE;
		} else if ("AttackCard".equals(card)) {
			return CardType.ATTACK;
		} else if ("DefenseCard".equals(card)) {
			return CardType.DEFENSE;
		} else if ("SedativesCard".equals(card)) {
			return CardType.SEDATIVES;
		} else if ("SpotlightCard".equals(card)) {
			return CardType.SPOTLIGHT;
		} else if ("TeleportCard".equals(card)) {
			return CardType.TELEPORT;
		} else
			return CardType.DEFAULT;
	}

}
