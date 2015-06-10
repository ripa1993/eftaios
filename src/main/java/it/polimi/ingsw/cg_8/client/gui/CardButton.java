package it.polimi.ingsw.cg_8.client.gui;

import javax.swing.JButton;

/**
 * JButton that allows the player to use an item card.
 * @author Alberto Parravicini
 * @version 1.0
 */
public class CardButton extends JButton {

	private static final long serialVersionUID = -4771580323012024149L;

	private CardType cardType;

	public CardButton() {
		this.cardType = CardType.DEFAULT;
		this.setVisible(true);
		this.setBorderPainted(false); 
		this.setContentAreaFilled(false); 
		this.setFocusPainted(false); 
		
	}

	/**
	 * Used to identify the type of button according to the appropriate card.
	 * 
	 * @author Alberto Parravicini
	 *
	 */
	private enum CardType {
		DEFAULT, ADRENALINE, ATTACK, DEFENSE, SEDATIVES, SPOTLIGHT, TELEPORT
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

}
