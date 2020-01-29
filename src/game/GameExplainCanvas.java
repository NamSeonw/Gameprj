package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

public class GameExplainCanvas extends Canvas {
	ExplainBackground bg;

	public GameExplainCanvas() {
		bg = new ExplainBackground();
	}

	@Override
	public void paint(Graphics g) {
		Image bufImage = createImage(getWidth(), getHeight());
		Graphics bufGraphic = bufImage.getGraphics();
		bg.draw(bufGraphic, this);
		g.drawImage(bufImage, 0, 0, this);

	}
}
