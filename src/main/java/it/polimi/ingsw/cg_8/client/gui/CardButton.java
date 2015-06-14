package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.Resource;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AdrenalineCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.AttackCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SedativesCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.SpotlightCard;
import it.polimi.ingsw.cg_8.model.cards.itemCards.TeleportCard;
import it.polimi.ingsw.cg_8.model.sectors.Coordinate;
import it.polimi.ingsw.cg_8.view.client.ActionParser;
import it.polimi.ingsw.cg_8.view.client.actions.ActionUseCard;
import it.polimi.ingsw.cg_8.view.client.actions.ClientAction;
import it.polimi.ingsw.cg_8.view.client.exceptions.NotAValidInput;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.JButton;

/**
 * JButton that allows the player to use an item card.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class CardButton extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4771580323012024149L;
	/**
	 * Button image
	 */
	private JLabel cardButtonImage;
	/**
	 * Button image overlay (alien or human)
	 */
	private JLabel cardButtonOverlay;
	/**
	 * Button text
	 */
	private JLabel text;
	/**
	 * Button card type
	 */
	private CardType cardType;
	/**
	 * Button font
	 */
	private Font fontTitilliumSemiboldUpright;
	/**
	 * Log4j logger
	 */
	private static final Logger LOGGER = LogManager.getLogger(CardButton.class);
	/**
	 * Button width, fixed
	 */
	private static final int BUTTON_WIDTH = 120;
	/**
	 * Button height, fixed
	 */
	private static final int BUTTON_HEIGHT = 120;
	/**
	 * Layered pane used to handle multiple layers: cardButtonImage,
	 * cardButtonOverlay, invisButton
	 */
	private JLayeredPane layeredPane;
	/**
	 * Semi-opaque image that overlays the button image
	 */
	private transient Image cardOverlay;
	/**
	 * Invisible button placed on top op this button
	 */
	private JButton invisButton;

	/**
	 * Constructor, creates a "No Card" button that is a place holder for future
	 * player's card
	 */
	public CardButton() {
		/**
		 * Set up size
		 */
		setPreferredSize(new Dimension(100, 120));
		setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		/**
		 * Set up default button
		 */
		this.text = new JLabel("No Card");
		this.cardType = CardType.DEFAULT;
		this.layeredPane = new JLayeredPane();
		this.cardButtonOverlay = new JLabel();
		cardButtonOverlay.setBackground(Color.CYAN);
		this.cardButtonImage = new JLabel();
		cardButtonImage.setBackground(Color.GREEN);
		/**
		 * Set up borders
		 */
		cardButtonImage.setBorder(new EmptyBorder(0, 0, 5, 0));
		cardButtonOverlay.setBorder(new EmptyBorder(0, 0, 5, 0));
		/**
		 * Load the font.
		 */
		try {
			fontTitilliumSemiboldUpright = Font.createFont(
					Font.TRUETYPE_FONT,
					new FileInputStream(
							Resource.FONT_TITILLIUM_SEMIBOLD_UPRIGHT))
					.deriveFont((float) 20);
		} catch (FontFormatException | IOException e) {
			LOGGER.error(e.getMessage());
		}

		/**
		 * Load the default overlay.
		 */
		this.setOverlay(Resource.IMG_HUMAN_OVERLAY);
		/**
		 * Set the default image.
		 */
		this.setImage(Resource.IMG_ITEM);
		cardButtonOverlay.setIcon(new ImageIcon(cardOverlay));
		cardButtonOverlay.setBounds(0, 0, 124, 106);
		cardButtonImage.setBounds(0, 0, 124, 106);

		/**
		 * Set up layout
		 */
		this.setLayout(new BorderLayout());
		add(layeredPane, BorderLayout.CENTER);
		layeredPane.add(cardButtonImage);
		layeredPane.setLayer(cardButtonImage, 1);
		layeredPane.add(cardButtonOverlay);
		layeredPane.setLayer(cardButtonOverlay, 2);

		/**
		 * Set up the invisible button
		 */
		invisButton = new JButton();
		invisButton.setVerticalAlignment(SwingConstants.BOTTOM);
		invisButton.setBounds(0, 0, 124, 106);
		layeredPane.add(invisButton);
		layeredPane.setLayer(invisButton, 3);
		invisButton.setOpaque(false);
		invisButton.setContentAreaFilled(false);
		invisButton.setBorderPainted(false);

		/**
		 * Input handling on the cardButton
		 */
		invisButton.addMouseListener(new MouseAdapter() {
			int width, height;

			@Override
			public void mouseEntered(MouseEvent event) {

				cardButtonOverlay.setVisible(true);
				cardButtonImage.repaint();
			}

			@Override
			public void mouseExited(MouseEvent event) {
				cardButtonOverlay.setVisible(false);
				cardButtonImage.repaint();

			}

			@Override
			public void mousePressed(MouseEvent event) {
				width = cardButtonImage.getWidth();
				height = cardButtonImage.getHeight();
				cardButtonImage.setSize((int) (1.05 * width),
						((int) (1.05 * height)));
				cardButtonImage.repaint();
				cardButtonOverlay.setSize((int) (1.05 * width),
						((int) (1.05 * height)));

				cardButtonOverlay.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				cardButtonImage.setSize(width, height);
				cardButtonImage.repaint();
				cardButtonOverlay.setSize(width, height);
				cardButtonOverlay.repaint();
			}

		});
		/**
		 * Set up font and text
		 */
		this.text.setFont(fontTitilliumSemiboldUpright);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(text, BorderLayout.SOUTH);

		/**
		 * Final setup
		 */
		cardButtonOverlay.setVisible(false);
		setOpaque(false);
		this.setVisible(true);

	}

	/**
	 * Used to identify the type of button according to the appropriate card.
	 * 
	 * @author Alberto Parravicini
	 *
	 */
	public enum CardType {
		DEFAULT, ADRENALINE, ATTACK, DEFENSE, SEDATIVES, SPOTLIGHT, TELEPORT
	}

	/**
	 * @return the type of the button
	 */
	public CardType getCardType() {
		return cardType;
	}

	/**
	 * Changes the type of the button
	 * 
	 * @param cardType
	 *            type of the card, changes the behaviour of the button
	 */
	public void setCardType(CardType cardType) {
		// change card type
		this.cardType = cardType;
		// change image
		// change texts
		// change tooltip text
		if (this.cardType.equals(CardType.DEFAULT)) {
			this.setImage(Resource.IMG_ITEM);
			this.setText("No Card");
			this.invisButton
					.setToolTipText("<html>This is an <b>empty slot</b> for item card</html>");
		} else if (this.cardType.equals(CardType.ADRENALINE)) {
			this.setImage(Resource.IMG_ADRENALINE);
			this.setText("Adrenaline");
			this.invisButton
					.setToolTipText("<html>This card allows you to <b>move two Sectors</b> this turn.</html>");
		} else if (this.cardType.equals(CardType.ATTACK)) {
			this.setImage(Resource.IMG_ATTACK);
			this.setText("Attack");
			this.invisButton
					.setToolTipText("<html>This card allows you to <b>attack</b>, using the same rules as the Aliens.<br>"
							+ "Note: the Human character can still move only one Sector.</html>");
		} else if (this.cardType.equals(CardType.DEFENSE)) {
			this.setImage(Resource.IMG_DEFENSE);
			this.setText("Defense");
			this.invisButton
					.setToolTipText("<html>Play this card immediately when an Alien attacks you.<br>"
							+ "You are  <b>not affected</b> by the attack.</html>");
		} else if (this.cardType.equals(CardType.SEDATIVES)) {
			this.setImage(Resource.IMG_SEDATIVES);
			this.setText("Sedatives");
			this.invisButton
					.setToolTipText("<html>If you play this card  <b>you do not draw </b> a Dangerous Sector Card this turn,<br>"
							+ "even if you move into a Dangerous Sector.</html>");
		} else if (this.cardType.equals(CardType.SPOTLIGHT)) {
			this.setImage(Resource.IMG_SPOTLIGHT);
			this.setText("Spotlight");
			this.invisButton
					.setToolTipText("<html>When you play this card, name any Sector. Any players (including you)<br>"
							+ "that are in the named Sector or in any of the six adjacent Sectors must immediately<br>"
							+ " <b>announce their exact location </b> Coordinates. This card affects both Humans and Aliens.<html>");
		} else if (this.cardType.equals(CardType.TELEPORT)) {
			this.setImage(Resource.IMG_TELEPORT);
			this.setText("Teleport");
			this.invisButton
					.setToolTipText("<html>This card allows you to  <b>move directly </b> to the Human Sector from any part of the ship.<br>"
							+ "This is in addition to your normal movement which can happen before or after you use the item.</html>");
		}
	}

	/**
	 * Used to set the card image, according to its type.
	 * 
	 * @param pathToImage
	 *            The path to the image, saved in {@link Resource the resource
	 *            file}
	 *
	 */
	private void setImage(String pathToImage) {
		try {
			Image tempImage = ImageIO.read(new File(pathToImage));
			Image cardImage = tempImage.getScaledInstance(100, -1,
					Image.SCALE_SMOOTH);
			cardButtonImage.setIcon(new ImageIcon(cardImage));

		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * Used to set the card overlay, according to the player's character.
	 * 
	 * @param pathToImage
	 *            The path to the image, saved in {@link Resource the resource
	 *            file}
	 *
	 */
	private void setOverlay(String pathToImage) {
		try {
			Image tempOverlay = ImageIO.read(new File(pathToImage));
			cardOverlay = tempOverlay.getScaledInstance(100, -1,
					Image.SCALE_SMOOTH);

		} catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	/**
	 * Used to update the player overlay.
	 */
	public void updateOverlay(String pathToImage) {
		this.setOverlay(pathToImage);
		this.cardButtonOverlay.setIcon(new ImageIcon(cardOverlay));
		this.repaint();
	}

	/**
	 * Changes the text of the button
	 * 
	 * @param text
	 *            text of the button
	 */
	private void setText(String text) {
		this.text.setText(text);
	}

	/**
	 * Used to access the cardButton and create actions when the button is
	 * pressed
	 * 
	 * @return this class jbutton
	 */
	public JButton getInvisButton() {
		return invisButton;
	}

	/**
	 * Returns the ClientAction related to this card button
	 * 
	 * @return - ClientAction instance if the card is active<br>
	 *         - null if the card is passive or the button is a placeholder
	 */
	public ClientAction createAction() {

		// if return is null the card is not usable or the input is wrong for
		// the spotlight card

		if (this.cardType.equals(CardType.DEFAULT)) {
			return null;
		} else if (this.cardType.equals(CardType.ADRENALINE)) {
			return new ActionUseCard(new AdrenalineCard());
		} else if (this.cardType.equals(CardType.ATTACK)) {
			return new ActionUseCard(new AttackCard());
		} else if (this.cardType.equals(CardType.DEFENSE)) {
			return null;
		} else if (this.cardType.equals(CardType.SEDATIVES)) {
			return new ActionUseCard(new SedativesCard());
		} else if (this.cardType.equals(CardType.SPOTLIGHT)) {
			String coordinateString = JOptionPane.showInputDialog(getParent(),
					"Insert target coordinate", "Coordinate");
			try {
				Coordinate coordinate = ActionParser
						.parseCoordinate(coordinateString);
				return new ActionUseCard(new SpotlightCard(), coordinate);
			} catch (NotAValidInput e1) {
				JOptionPane
						.showMessageDialog(getParent(), "Not a valid input!");
				return null;
			}

		} else if (this.cardType.equals(CardType.TELEPORT)) {
			return new ActionUseCard(new TeleportCard());
		}
		return null;
	}
}
