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
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * JButton that allows the player to use an item card.
 * 
 * @author Alberto Parravicini
 * @version 1.0
 */
public class CardButton extends JPanel {

	private static final long serialVersionUID = -4771580323012024149L;
	private JLabel cardButton;
	private JLabel text;
	private CardType cardType;

	public CardButton() {
		setOpaque(false);
		this.cardType = CardType.DEFAULT;
		this.setLayout(new BorderLayout());

		this.text = new JLabel("No Card");
		text.setFont(new Font("Tahoma", Font.BOLD, 14));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		this.cardButton = new JLabel();

		this.add(cardButton, BorderLayout.CENTER);
		this.add(text, BorderLayout.SOUTH);

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
		} else if (this.cardType.equals(CardType.ADRENALINE)) {
			this.setImage(Resource.IMG_ADRENALINE);
			this.setText("Adrenaline");
		} else if (this.cardType.equals(CardType.ATTACK)) {
			this.setImage(Resource.IMG_ATTACK);
			this.setText("Attack");
		} else if (this.cardType.equals(CardType.DEFENSE)) {
			this.setImage(Resource.IMG_DEFENSE);
			this.setText("Defense");
		} else if (this.cardType.equals(CardType.SEDATIVES)) {
			this.setImage(Resource.IMG_SEDATIVES);
			this.setText("Sedatives");
		} else if (this.cardType.equals(CardType.SPOTLIGHT)) {
			this.setImage(Resource.IMG_SPOTLIGHT);
			this.setText("Spotlight");
		} else if (this.cardType.equals(CardType.TELEPORT)) {
			this.setImage(Resource.IMG_TELEPORT);
			this.setText("Teleport");
		}

	}

	private void setImage(String pathToImage) {
		try {
			Image tempImage = ImageIO.read(new File(pathToImage));
			Image cardImage = tempImage.getScaledInstance(100, -1,
					Image.SCALE_SMOOTH);
			cardButton.setIcon(new ImageIcon(cardImage));

		} catch (IOException ex) {
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
