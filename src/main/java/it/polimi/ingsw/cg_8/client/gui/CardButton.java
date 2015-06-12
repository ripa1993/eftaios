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

/**
 * JButton that allows the player to use an item card.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class CardButton extends JPanel {

	private static final long serialVersionUID = -4771580323012024149L;
	private JLabel cardButtonImage;
	private JLabel text;
	private CardType cardType;
	private CardLayout cardLayout;
	private JLabel cardButtonOverlay;
	private Font fontTitilliumSemiboldUpright;
	/**
	 * Log4j logger
	 */
	private static final Logger logger = LogManager.getLogger(CardButton.class);
	private JPanel cardButton;

	public CardButton() {
		this.text = new JLabel("No Card");
		this.cardType = CardType.DEFAULT;
		this.cardButton = new JPanel();
		cardButton.setMaximumSize(new Dimension(150, 150));
		cardButton.setOpaque(false);
		this.cardButtonOverlay = new JLabel();
		cardButtonOverlay.setBorder(new EmptyBorder(5, 0, 0, 0));

		this.cardButtonImage = new JLabel();

		try {
			fontTitilliumSemiboldUpright = Font.createFont(
					Font.TRUETYPE_FONT,
					new FileInputStream(
							Resource.FONT_TITILLIUM_SEMIBOLD_UPRIGHT))
					.deriveFont((float) 20);
		} catch (FontFormatException | IOException e) {
			logger.error(e.getMessage());
		}

		try {
			Image tempOverlay = ImageIO.read(new File(
					Resource.IMG_HUMAN_OVERLAY));
			Image cardOverlay = tempOverlay.getScaledInstance(100, -1,
					Image.SCALE_SMOOTH);
			cardButtonOverlay.setIcon(new ImageIcon(cardOverlay));

		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}

		this.setImage(Resource.IMG_ITEM);

		this.setLayout(new BorderLayout());
		this.text.setFont(fontTitilliumSemiboldUpright);

		text.setHorizontalAlignment(SwingConstants.CENTER);

		add(cardButton, BorderLayout.NORTH);
		cardLayout = new CardLayout(0, 0);
		cardButton.setLayout(cardLayout);

		cardButtonOverlay.setOpaque(false);
		cardButtonImage.setOpaque(false);

		cardButton.add(cardButtonImage, "cardButtonImage");

		cardButton.add(cardButtonOverlay, "cardButtonOverlay");
	
		cardButtonImage.addMouseListener(new MouseAdapter() {
			int width, height;

			@Override
			public void mousePressed(MouseEvent event) {
				width = cardButtonImage.getWidth();
				height = cardButtonImage.getHeight();
				cardButtonImage.setSize((int) (1.05 * width),
						((int) (1.05 * height)));
				cardButtonImage.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent event) {
				cardButtonImage.setSize(width, height);
				cardButtonImage.repaint();
			}

		});
		this.add(text, BorderLayout.SOUTH);

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

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
		// change image
		// change action
		// change texts
		if (this.cardType.equals(CardType.DEFAULT)) {
			this.setImage(Resource.IMG_ITEM);
			this.setText("No Card");
			this.cardButtonImage
					.setToolTipText("<html>This is an <b>empty slot</b> for item card</html>");
		} else if (this.cardType.equals(CardType.ADRENALINE)) {
			this.setImage(Resource.IMG_ADRENALINE);
			this.setText("Adrenaline");
			this.cardButtonImage
					.setToolTipText("<html>This card allows you to <b>move two Sectors</b> this turn.</html>");
		} else if (this.cardType.equals(CardType.ATTACK)) {
			this.setImage(Resource.IMG_ATTACK);
			this.setText("Attack");
			this.cardButtonImage
					.setToolTipText("<html>This card allows you to <b>attack</b>, using the same rules as the Aliens.<br>"
							+ "Note: the Human character can still move only one Sector.</html>");
		} else if (this.cardType.equals(CardType.DEFENSE)) {
			this.setImage(Resource.IMG_DEFENSE);
			this.setText("Defense");
			this.cardButtonImage
					.setToolTipText("<html>Play this card immediately when an Alien attacks you.<br>"
							+ "You are  <b>not affected</b> by the attack.</html>");
		} else if (this.cardType.equals(CardType.SEDATIVES)) {
			this.setImage(Resource.IMG_SEDATIVES);
			this.setText("Sedatives");
			this.cardButtonImage
					.setToolTipText("<html>If you play this card  <b>you do not draw </b> a Dangerous Sector Card this turn,<br>"
							+ "even if you move into a Dangerous Sector.</html>");
		} else if (this.cardType.equals(CardType.SPOTLIGHT)) {
			this.setImage(Resource.IMG_SPOTLIGHT);
			this.setText("Spotlight");
			this.cardButtonImage
					.setToolTipText("<html>When you play this card, name any Sector. Any players (including you)<br>"
							+ "that are in the named Sector or in any of the six adjacent Sectors must immediately<br>"
							+ " <b>announce their exact location </b> Coordinates. This card affects both Humans and Aliens.<html>");
		} else if (this.cardType.equals(CardType.TELEPORT)) {
			this.setImage(Resource.IMG_TELEPORT);
			this.setText("Teleport");
			this.cardButtonImage
					.setToolTipText("<html>This card allows you to  <b>move directly </b> to the Human Sector from any part of the ship.<br>"
							+ "This is in addition to your normal movement which can happen before or after you use the item.</html>");
		}
		this.repaint();
	}

	private void setImage(String pathToImage) {
		try {
			Image tempImage = ImageIO.read(new File(pathToImage));
			Image cardImage = tempImage.getScaledInstance(100, -1,
					Image.SCALE_SMOOTH);
			cardButtonImage.setIcon(new ImageIcon(cardImage));

		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
	}

	private void setText(String text) {
		this.text.setText(text);
	}

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
