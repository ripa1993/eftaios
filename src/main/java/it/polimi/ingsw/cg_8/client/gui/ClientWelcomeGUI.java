package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.Resource;
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
import java.lang.reflect.InvocationTargetException;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.border.EmptyBorder;

public class ClientWelcomeGUI implements Runnable {
	private JFrame main;
	private ImageIcon westPanelImage;
	private Image westPanelImageScaled;
	private Image eastPanelImageScaled;
	private ImageIcon eastPanelImage;
	private JTextField playerTextField;
	private ButtonGroup connectionGroup, mapGroup;
	private JButton playButton;
	private Font fontTitilliumBoldUpright;
	private JRadioButton galvaniRadioButton;
	private JRadioButton galileiRadioButton;
	private JRadioButton fermiRadioButton;
	private JRadioButton rmiRadioButton;
	private JRadioButton socketRadioButton;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager
			.getLogger(ClientWelcomeGUI.class);

	public ClientWelcomeGUI() {
		main = new JFrame("Escape from the aliens in outer space");

		try {
			fontTitilliumBoldUpright = Font.createFont(Font.TRUETYPE_FONT,
					new FileInputStream(Resource.FONT_TITILLIUM_BOLD_UPRIGHT))
					.deriveFont((float) 20);
		} catch (FontFormatException | IOException e) {
			logger.error(e.getMessage());
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
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		main.getContentPane().add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		panel.add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 474, 0 };
		gbl_centerPanel.rowHeights = new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);

		JLabel logoLabel = new JLabel();
		logoLabel.setBorder(new EmptyBorder(25, 0, 35, 0));
		logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoLabel.setIcon(new ImageIcon(Resource.IMG_ART_TITLE));
		GridBagConstraints gbc_logoLabel = new GridBagConstraints();
		gbc_logoLabel.insets = new Insets(0, 0, 5, 0);
		gbc_logoLabel.anchor = GridBagConstraints.NORTH;
		gbc_logoLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_logoLabel.gridx = 0;
		gbc_logoLabel.gridy = 0;
		centerPanel.add(logoLabel, gbc_logoLabel);

		JLabel playerLabel = new JLabel("INSERT YOUR CHARACTER NAME:");
		playerLabel.setFont(fontTitilliumBoldUpright);
		playerLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 7;
		centerPanel.add(playerLabel, gbc_lblName);

		playerTextField = new JTextField();
		playerTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		playerTextField.setHorizontalAlignment(SwingConstants.CENTER);
		playerTextField.setBackground(Color.WHITE);
		playerTextField.setText("Player");
		GridBagConstraints gbc_txtAsd = new GridBagConstraints();
		gbc_txtAsd.insets = new Insets(0, 0, 5, 0);
		gbc_txtAsd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAsd.gridx = 0;
		gbc_txtAsd.gridy = 8;
		centerPanel.add(playerTextField, gbc_txtAsd);
		playerTextField.setColumns(10);

		JLabel connectionTypeLabel = new JLabel(
				"SELECT YOUR PREFERRED CONNECTION METHOD:");
		connectionTypeLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		connectionTypeLabel.setFont(fontTitilliumBoldUpright);
		connectionTypeLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConnectionType = new GridBagConstraints();
		gbc_lblConnectionType.insets = new Insets(0, 0, 5, 0);
		gbc_lblConnectionType.gridx = 0;
		gbc_lblConnectionType.gridy = 10;
		centerPanel.add(connectionTypeLabel, gbc_lblConnectionType);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 11;
		centerPanel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));

		rmiRadioButton = new JRadioButton("RMI");
		rmiRadioButton.setBackground(Color.WHITE);
		rmiRadioButton.setSelected(true);
		panel_1.add(rmiRadioButton);

		socketRadioButton = new JRadioButton("Socket");
		socketRadioButton.setBackground(Color.WHITE);
		panel_1.add(socketRadioButton);

		JLabel mapSelectLabel = new JLabel("SELECT YOUR PREFERRED MAP:");
		mapSelectLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
		mapSelectLabel.setFont(fontTitilliumBoldUpright);
		mapSelectLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblPreferredMap = new GridBagConstraints();
		gbc_lblPreferredMap.insets = new Insets(0, 0, 5, 0);
		gbc_lblPreferredMap.gridx = 0;
		gbc_lblPreferredMap.gridy = 13;
		centerPanel.add(mapSelectLabel, gbc_lblPreferredMap);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 14;
		centerPanel.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));

		fermiRadioButton = new JRadioButton("Fermi");
		fermiRadioButton.setBackground(Color.WHITE);
		fermiRadioButton.setSelected(true);
		panel_2.add(fermiRadioButton);

		galileiRadioButton = new JRadioButton("Galilei");
		galileiRadioButton.setBackground(Color.WHITE);
		panel_2.add(galileiRadioButton);

		galvaniRadioButton = new JRadioButton("Galvani");
		galvaniRadioButton.setBackground(Color.WHITE);
		panel_2.add(galvaniRadioButton);

		connectionGroup = new ButtonGroup();
		mapGroup = new ButtonGroup();
		connectionGroup.add(rmiRadioButton);
		connectionGroup.add(socketRadioButton);
		mapGroup.add(fermiRadioButton);
		mapGroup.add(galileiRadioButton);
		mapGroup.add(galvaniRadioButton);
		
				playButton = new JButton("PLAY");
				playButton.setBackground(Color.WHITE);
				GridBagConstraints gbc_btnPlay = new GridBagConstraints();
				gbc_btnPlay.insets = new Insets(0, 0, 5, 0);
				gbc_btnPlay.gridx = 0;
				gbc_btnPlay.gridy = 16;
				centerPanel.add(playButton, gbc_btnPlay);

		westPanelImage = new ImageIcon(Resource.IMG_ART_HUMAN);
		westPanelImageScaled = new ImageIcon(westPanelImage.getImage()
				.getScaledInstance(5000, -1, Image.SCALE_SMOOTH)).getImage();
		JPanel westPanel = new JPanel() {
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
		};
		westPanel.setPreferredSize(new Dimension(400, 10));
		westPanel.setOpaque(false);
		panel.add(westPanel, BorderLayout.WEST);
		eastPanelImage = new ImageIcon(Resource.IMG_ART_ALIEN);
		eastPanelImageScaled = new ImageIcon(eastPanelImage.getImage()
				.getScaledInstance(5000, -1, Image.SCALE_SMOOTH)).getImage();
		JPanel eastPanel = new JPanel() {
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
		};
		eastPanel.setPreferredSize(new Dimension(400, 10));
		eastPanel.setOpaque(false);
		panel.add(eastPanel, BorderLayout.EAST);

		// main.pack();
		main.setVisible(true);
		main.setSize(1280, 720);
	}

	@Override
	public void run() {

		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logger.debug("Pressed play");
				ConnectionManager connectionManager;
				String playerName = playerTextField.getText();
				if (rmiRadioButton.isSelected()
						&& fermiRadioButton.isSelected()) {
					connectionManager = new ConnectionManagerRMI(playerName, GameMapName.FERMI);
				} else if (rmiRadioButton.isSelected()
						&& galvaniRadioButton.isSelected()) {
					connectionManager = new ConnectionManagerRMI(playerName, GameMapName.GALVANI);
				} else if (rmiRadioButton.isSelected()
						&& galileiRadioButton.isSelected()) {
					connectionManager = new ConnectionManagerRMI(playerName, GameMapName.GALILEI);
				} else if (socketRadioButton.isSelected()
						&& fermiRadioButton.isSelected()) {
					connectionManager = new ConnectionManagerSocket(playerName, GameMapName.FERMI);
				} else if (socketRadioButton.isSelected()
						&& galvaniRadioButton.isSelected()) {
					connectionManager = new ConnectionManagerSocket(playerName, GameMapName.GALVANI);
				} else {
					// socket and galilei is the default if some weird bug
					// happens
					connectionManager = new ConnectionManagerSocket(playerName, GameMapName.GALILEI);
				}
				ExecutorService exec = Executors.newCachedThreadPool();
				BackgroundMusicThread bmt = new BackgroundMusicThread();
				logger.debug("Background music loaded");
				exec.submit(bmt);
				logger.debug("Connection manager created");
				ClientGUIThread guiThread = new ClientGUIThread();
				logger.debug("Gui thread created");
				guiThread.setConnectionManager(connectionManager);
				guiThread.getConnectionManager().setup();
				logger.debug("Connection manager setup started");
				SwingUtilities.invokeLater(guiThread);
				logger.debug("Gui thread started, killing welcome frame");
				main.dispose();
				logger.debug("Gui welcome killed");
				
				
				

			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new ClientWelcomeGUI());
	}
}
