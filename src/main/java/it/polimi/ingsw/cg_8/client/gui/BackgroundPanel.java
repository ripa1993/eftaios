package it.polimi.ingsw.cg_8.client.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

/**
 * Panel whom background is filled with a repeated image (pattern)
 * 
 * @author Simone
 * @version 1.0
 */
public class BackgroundPanel extends JComponent {
	/**
	 * Background image
	 */
	private Image image;
	/**
	 * Constructor
	 * @param image background image
	 */
	public BackgroundPanel(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// fills background repeating the image
		int iw = image.getWidth(this);
		int ih = image.getHeight(this);
		if (iw > 0 && ih > 0) {
			for (int x = 0; x < getWidth(); x += iw) {
				for (int y = 0; y < getHeight(); y += ih) {
					g.drawImage(image, x, y, iw, ih, this);
				}
			}
		}
	}
}
