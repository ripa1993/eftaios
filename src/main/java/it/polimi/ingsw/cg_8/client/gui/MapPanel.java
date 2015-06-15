package it.polimi.ingsw.cg_8.client.gui;

import it.polimi.ingsw.cg_8.model.sectors.Coordinate;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

public class MapPanel extends JLayeredPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6773174313895718360L;
	public static final int NUM_COLUMN = 23;
	public static final int NUM_ROW = 29;
	/**
	 * Map image, scalable
	 */
	private Image backgroundImageScaled;
	/**
	 * Map image
	 */
	private ImageIcon backgroundImage;

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int updatedWidth = this.getWidth();
		int updatedHeight = this.getHeight();

		if (this.getWidth() - backgroundImageScaled.getWidth(null) > this
				.getHeight() - backgroundImageScaled.getHeight(null)) {
			updatedWidth = updatedHeight * backgroundImageScaled.getWidth(null)
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
		g.drawImage(backgroundImageScaled, x, y, updatedWidth, updatedHeight,
				null);
	}

	/**
	 * Changes the map image, used when the server sends a ResponseMap message
	 * 
	 * @param path
	 *            path to image file
	 */
	void setMapImage(String path) {
		backgroundImage = new ImageIcon(path);
		backgroundImageScaled = new ImageIcon(backgroundImage.getImage()
				.getScaledInstance(1000, -1, Image.SCALE_SMOOTH)).getImage();
	}

	/**
	 * Given a coordinate, gives the position inside map panel
	 * 
	 * @param coordinate
	 *            sector coordinate
	 * @return (X, Y) inside the map panel
	 */
	private Point getPoint(Coordinate coordinate) {
		// acquire coords
		int row = coordinate.getY();
		int col = coordinate.getX();
		// get map panel size
		int mapPanelWidth = this.getWidth();
		int mapPanelHeight = this.getHeight();
		// get image size, resized
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
		// get border sizes
		float panelBorderWidth = (mapPanelWidth - mapImageWidth) / 2;
		float panelBorderHeigth = (mapPanelHeight - mapImageHeight) / 2;
		// calculate col and row size
		float columnWidth = mapImageWidth / 23;
		float rowHeigth = mapImageHeight / 29;
		// calculate x,y position inside the map panel
		int x, y;
		x = (int) (panelBorderWidth + col * columnWidth);
		y = (int) (panelBorderHeigth + 2 * row * rowHeigth);
		if (col % 2 == 1) {
			y = (int) (y + rowHeigth);
		}

		return new Point(x, y);
	}

	Coordinate getCoordinate(MouseEvent e) {
		// result coordinates
		int col = -1;
		int row = -1;

		// click coordinates
		int mouseX = e.getX();
		int mouseY = e.getY();
		// mapPanel sizes
		int mapPanelWidth = this.getWidth();
		int mapPanelHeight = this.getHeight();
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
		if (col % 2 != 0) {
			row--;
		}
		if (row < 0 || col < 0 || row >= (NUM_ROW - 1) || col >= NUM_COLUMN) {
			return new Coordinate();
		}
		row = (int) Math.floor((double) (row / 2));
		return new Coordinate(col, row);
	}
}
