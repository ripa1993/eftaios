package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.client.ConnectionManager;
import it.polimi.ingsw.cg_8.client.ConnectionManagerRMI;
import it.polimi.ingsw.cg_8.client.ConnectionManagerSocket;
import it.polimi.ingsw.cg_8.model.map.GameMapName;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.jtattoo.plaf.acryl.*;

/**
 * Main class for client graphic user interface. It uses a swing frame to
 * receive user inputs. The user can choose a username, a connection method
 * between rmi and socket, a vote for the map choiche (fermi, galvani and
 * galilei) and if the background music is enabled
 * 
 * @author Simone
 * @version 1.0
 */
public class MainGUI implements Runnable {
	/**
	 * Handles game start
	 * 
	 * @author Simone
	 * @version 1.0
	 */
	private class PlayActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LOGGER.debug("Pressed play");
			ConnectionManager connectionManager;
			String playerName = playerTextField.getText();
			if (rmiRadioButton.isSelected() && fermiRadioButton.isSelected()) {
				connectionManager = new ConnectionManagerRMI(playerName,
						GameMapName.FERMI);
			} else if (rmiRadioButton.isSelected()
					&& galvaniRadioButton.isSelected()) {
				connectionManager = new ConnectionManagerRMI(playerName,
						GameMapName.GALVANI);
			} else if (rmiRadioButton.isSelected()
					&& galileiRadioButton.isSelected()) {
				connectionManager = new ConnectionManagerRMI(playerName,
						GameMapName.GALILEI);
			} else if (socketRadioButton.isSelected()
					&& fermiRadioButton.isSelected()) {
				connectionManager = new ConnectionManagerSocket(playerName,
						GameMapName.FERMI);
			} else if (socketRadioButton.isSelected()
					&& galvaniRadioButton.isSelected()) {
				connectionManager = new ConnectionManagerSocket(playerName,
						GameMapName.GALVANI);
			} else {
				// socket and galilei is the default if some weird bug
				// happens
				connectionManager = new ConnectionManagerSocket(playerName,
						GameMapName.GALILEI);
			}
			LOGGER.debug("Connection manager created");

			LOGGER.debug("Checking if the user wants some music");
			if (yesMusicRadioButton.isSelected()) {
				LOGGER.debug("Yes");
				ExecutorService exec = Executors.newCachedThreadPool();
				BackgroundMusicThread bmt = new BackgroundMusicThread();
				LOGGER.debug("Background music loaded");
				exec.submit(bmt);

			} else {
				LOGGER.debug("No");
			}
			ClientGUIThread guiThread = new ClientGUIThread();
			LOGGER.debug("Gui thread created");
			guiThread.setConnectionManager(connectionManager);
			guiThread.getConnectionManager().setup();
			LOGGER.debug("Connection manager setup started");
			SwingUtilities.invokeLater(guiThread);
			LOGGER.debug("Gui thread started, killing welcome frame");
			main.dispose();
			LOGGER.debug("Gui welcome killed");

		}
	}

	/**
	 * JPanel showing an image on the right side
	 * 
	 * @author Simone
	 * @version 1.0
	 */
	private class EastJPanel extends JPanel {

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			int updatedWidth = this.getWidth();
			int updatedHeight = this.getHeight();

			if (this.getWidth() - eastPanelImageScaled.getWidth(null) > this
					.getHeight() - eastPanelImageScaled.getHeight(null)) {
				updatedWidth = updatedHeight
						* eastPanelImageScaled.getWidth(null)
						/ eastPanelImageScaled.getHeight(null);
			}
			if (this.getWidth() - eastPanelImageScaled.getWidth(null) < this
					.getHeight() - eastPanelImageScaled.getHeight(null)) {
				updatedHeight = updatedWidth
						* eastPanelImageScaled.getHeight(null)
						/ eastPanelImageScaled.getWidth(null);
			}

			int x = (this.getWidth() - updatedWidth) / 2;
			int y = (this.getHeight() - updatedHeight) / 2;
			g.drawImage(eastPanelImageScaled, x, y, updatedWidth,
					updatedHeight, null);
		}
	}

	/**
	 * Jpanel showing an image on the left side
	 * 
	 * @author Simone
	 * @version 1.0
	 */
	private class WestJPanel extends JPanel {

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			int updatedWidth = this.getWidth();
			int updatedHeight = this.getHeight();

			if (this.getWidth() - westPanelImageScaled.getWidth(null) > this
					.getHeight() - westPanelImageScaled.getHeight(null)) {
				updatedWidth = updatedHeight
						* westPanelImageScaled.getWidth(null)
						/ westPanelImageScaled.getHeight(null);
			}
			if (this.getWidth() - westPanelImageScaled.getWidth(null) < this
					.getHeight() - westPanelImageScaled.getHeight(null)) {
				updatedHeight = updatedWidth
						* westPanelImageScaled.getHeight(null)
						/ westPanelImageScaled.getWidth(null);
			}

			int x = (this.getWidth() - updatedWidth) / 2;
			int y = (this.getHeight() - updatedHeight) / 2;
			g.drawImage(westPanelImageScaled, x, y, updatedWidth,
					updatedHeight, null);
		}
	}

	/**
	 * Main JFrame
	 */
	private JFrame main;
	/**
	 * Left ImageIcon
	 */
	private ImageIcon westPanelImage;
	/**
	 * Left Image, scalable
	 */
	private Image westPanelImageScaled;
	/**
	 * Right Image, scalable
	 */
	private Image eastPanelImageScaled;
	/**
	 * Right ImageIcon
	 */
	private ImageIcon eastPanelImage;
	/**
	 * Player name text field
	 */
	private JTextField playerTextField;
	/**
	 * ButtonGroup, used to distinguish groups of radio buttons
	 */
	private ButtonGroup connectionGroup, mapGroup, musicGroup;
	/**
	 * Play button, starts the game
	 */
	private JButton playButton;
	/**
	 * Font used by the JLabels
	 */
	private Font fontTitilliumBoldUpright;
	/**
	 * RadioButtons used during the setup phase
	 */
	private JRadioButton galvaniRadioButton, galileiRadioButton,
			fermiRadioButton, socketRadioButton, rmiRadioButton,
			yesMusicRadioButton, noMusicRadioButton;
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(MainGUI.class);

	/**
	 * Constructors, create a frame with images on the left and right side. Game
	 * logo is placed in the top center. Player can choose username, connection
	 * method, favourite map and background music. Then can press play to start
	 * the game
	 */
	public MainGUI() {
		main = new JFrame("Escape from the aliens in outer space");

		try {
			fontTitilliumBoldUpright = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream(Resource.FONT_TITILLIUM_BOLD_UPRIGHT))
					.deriveFont((float) 20);
		} catch (FontFormatException | IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		main.setBackground(Color.BLACK);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		main.getContentPane().setLayout(gridBagLayout);

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		main.getContentPane().add(panel, gbcPanel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		panel.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gblCenterPanel = new GridBagLayout();
		gblCenterPanel.columnWidths = new int[] { 474, 0 };
		gblCenterPanel.rowHeights = new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gblCenterPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gblCenterPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		centerPanel.setLayout(gblCenterPanel);

		JLabel logoLabel = new JLabel();
		logoLabel.setBorder(new EmptyBorder(25, 0, 35, 0));
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setIcon(new ImageIcon(Resource.IMG_ART_TITLE));
		GridBagConstraints gbcLogoLabel = new GridBagConstraints();
		gbcLogoLabel.insets = new Insets(0, 0, 5, 0);
		gbcLogoLabel.anchor = GridBagConstraints.NORTH;
		gbcLogoLabel.fill = GridBagConstraints.HORIZONTAL;
		gbcLogoLabel.gridx = 0;
		gbcLogoLabel.gridy = 0;
		centerPanel.add(logoLabel, gbcLogoLabel);

		JLabel playerLabel = new JLabel("INSERT YOUR CHARACTER NAME:");
		playerLabel.setFont(fontTitilliumBoldUpright);
		playerLabel.setForeground(Color.WHITE);
		GridBagConstraints gbcLblName = new GridBagConstraints();
		gbcLblName.insets = new Insets(0, 0, 5, 0);
		gbcLblName.gridx = 0;
		gbcLblName.gridy = 7;
		centerPanel.add(playerLabel, gbcLblName);

		playerTextField = new JTextField();
		playerTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		playerTextField.setHorizontalAlignment(SwingConstants.CENTER);
		playerTextField.setBackground(new Color(214, 217, 223));
		playerTextField.setText("Player");
		GridBagConstraints gbcTxtAsd = new GridBagConstraints();
		gbcTxtAsd.insets = new Insets(0, 0, 5, 0);
		gbcTxtAsd.fill = GridBagConstraints.HORIZONTAL;
		gbcTxtAsd.gridx = 0;
		gbcTxtAsd.gridy = 8;
		centerPanel.add(playerTextField, gbcTxtAsd);
		playerTextField.setColumns(10);

		JLabel connectionTypeLabel = new JLabel(
				"SELECT YOUR PREFERRED CONNECTION METHOD:");
		connectionTypeLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		connectionTypeLabel.setFont(fontTitilliumBoldUpright);
		connectionTypeLabel.setForeground(Color.WHITE);
		GridBagConstraints gbcLblConnectionType = new GridBagConstraints();
		gbcLblConnectionType.insets = new Insets(0, 0, 5, 0);
		gbcLblConnectionType.gridx = 0;
		gbcLblConnectionType.gridy = 10;
		centerPanel.add(connectionTypeLabel, gbcLblConnectionType);

		JPanel panel1 = new JPanel();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		gbcPanel1.anchor = GridBagConstraints.NORTH;
		gbcPanel1.insets = new Insets(0, 0, 5, 0);
		gbcPanel1.fill = GridBagConstraints.HORIZONTAL;
		gbcPanel1.gridx = 0;
		gbcPanel1.gridy = 11;
		centerPanel.add(panel1, gbcPanel1);
		panel1.setLayout(new GridLayout(1, 2, 0, 0));

		rmiRadioButton = new JRadioButton("RMI");
		rmiRadioButton.setBackground(new Color(214, 217, 223));
		rmiRadioButton.setSelected(true);
		panel1.add(rmiRadioButton);

		socketRadioButton = new JRadioButton("Socket");
		socketRadioButton.setBackground(new Color(214, 217, 223));
		panel1.add(socketRadioButton);

		JLabel mapSelectLabel = new JLabel("SELECT YOUR PREFERRED MAP:");
		mapSelectLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		mapSelectLabel.setFont(fontTitilliumBoldUpright);
		mapSelectLabel.setForeground(Color.WHITE);
		GridBagConstraints gbcLblPreferredMap = new GridBagConstraints();
		gbcLblPreferredMap.insets = new Insets(0, 0, 5, 0);
		gbcLblPreferredMap.gridx = 0;
		gbcLblPreferredMap.gridy = 13;
		centerPanel.add(mapSelectLabel, gbcLblPreferredMap);

		JPanel panel2 = new JPanel();
		GridBagConstraints gbcPanel2 = new GridBagConstraints();
		gbcPanel2.insets = new Insets(0, 0, 5, 0);
		gbcPanel2.anchor = GridBagConstraints.NORTH;
		gbcPanel2.fill = GridBagConstraints.HORIZONTAL;
		gbcPanel2.gridx = 0;
		gbcPanel2.gridy = 14;
		centerPanel.add(panel2, gbcPanel2);
		panel2.setLayout(new GridLayout(0, 3, 0, 0));

		fermiRadioButton = new JRadioButton("Fermi");
		fermiRadioButton.setBackground(new Color(214, 217, 223));
		fermiRadioButton.setSelected(true);
		panel2.add(fermiRadioButton);

		galileiRadioButton = new JRadioButton("Galilei");
		galileiRadioButton.setBackground(new Color(214, 217, 223));
		panel2.add(galileiRadioButton);

		galvaniRadioButton = new JRadioButton("Galvani");
		galvaniRadioButton.setBackground(new Color(214, 217, 223));
		panel2.add(galvaniRadioButton);

		JLabel lblEnjoySomeCreepy = new JLabel("ENJOY SOME CREEPY ALIEN MUSIC:");
		lblEnjoySomeCreepy.setFont(fontTitilliumBoldUpright);
		lblEnjoySomeCreepy.setForeground(Color.WHITE);
		lblEnjoySomeCreepy.setBorder(new EmptyBorder(10, 0, 0, 0));
		GridBagConstraints gbcLblEnjoySomeCreepy = new GridBagConstraints();
		gbcLblEnjoySomeCreepy.insets = new Insets(0, 0, 5, 0);
		gbcLblEnjoySomeCreepy.gridx = 0;
		gbcLblEnjoySomeCreepy.gridy = 16;
		centerPanel.add(lblEnjoySomeCreepy, gbcLblEnjoySomeCreepy);

		JPanel panel3 = new JPanel();
		GridBagConstraints gbcPanel3 = new GridBagConstraints();
		gbcPanel3.anchor = GridBagConstraints.NORTH;
		gbcPanel3.insets = new Insets(0, 0, 5, 0);
		gbcPanel3.fill = GridBagConstraints.HORIZONTAL;
		gbcPanel3.gridx = 0;
		gbcPanel3.gridy = 17;
		centerPanel.add(panel3, gbcPanel3);
		panel3.setLayout(new GridLayout(1, 2, 0, 0));

		yesMusicRadioButton = new JRadioButton("Sure");
		yesMusicRadioButton.setBackground(new Color(214, 217, 223));
		yesMusicRadioButton.setSelected(true);
		panel3.add(yesMusicRadioButton);

		noMusicRadioButton = new JRadioButton("Nope");
		noMusicRadioButton.setBackground(new Color(214, 217, 223));
		panel3.add(noMusicRadioButton);

		connectionGroup = new ButtonGroup();
		mapGroup = new ButtonGroup();
		musicGroup = new ButtonGroup();
		connectionGroup.add(rmiRadioButton);
		connectionGroup.add(socketRadioButton);
		mapGroup.add(fermiRadioButton);
		mapGroup.add(galileiRadioButton);
		mapGroup.add(galvaniRadioButton);
		musicGroup.add(yesMusicRadioButton);
		musicGroup.add(noMusicRadioButton);

		playButton = new JButton("PLAY");
		GridBagConstraints gbcBtnPlay = new GridBagConstraints();
		gbcBtnPlay.insets = new Insets(0, 0, 5, 0);
		gbcBtnPlay.gridx = 0;
		gbcBtnPlay.gridy = 19;
		centerPanel.add(playButton, gbcBtnPlay);

		westPanelImage = new ImageIcon(Resource.IMG_ART_HUMAN);
		westPanelImageScaled = new ImageIcon(westPanelImage.getImage()
				.getScaledInstance(5000, -1, Image.SCALE_SMOOTH)).getImage();

		JPanel westPanel = new WestJPanel();
		westPanel.setPreferredSize(new Dimension(400, 10));
		westPanel.setOpaque(false);
		panel.add(westPanel, BorderLayout.WEST);
		eastPanelImage = new ImageIcon(Resource.IMG_ART_ALIEN);
		eastPanelImageScaled = new ImageIcon(eastPanelImage.getImage()
				.getScaledInstance(5000, -1, Image.SCALE_SMOOTH)).getImage();
		JPanel eastPanel = new EastJPanel();
		eastPanel.setPreferredSize(new Dimension(400, 10));
		eastPanel.setOpaque(false);
		panel.add(eastPanel, BorderLayout.EAST);
		ImageIcon img = new ImageIcon(Resource.LOGO);
		main.setIconImage(img.getImage());
		main.setVisible(true);
		main.setSize(1280, 720);
	}

	@Override
	public void run() {

		playButton.addActionListener(new PlayActionListener());
	}

	/**
	 * GUI launcher
	 * 
	 * @param args
	 *            parameters
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		SwingUtilities.invokeLater(new MainGUI());
	}
}
