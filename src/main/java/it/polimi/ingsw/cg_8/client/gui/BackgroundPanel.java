package it.polimi.ingsw.cg_8.client.gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

public class BackgroundPanel extends JComponent {
    
	private Image image;
    public BackgroundPanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
