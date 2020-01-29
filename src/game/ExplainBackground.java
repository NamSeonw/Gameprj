package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ExplainBackground {
	protected int x;
	protected int y;
	protected Image img;

	static Toolkit tk;
	static {
		tk = Toolkit.getDefaultToolkit();
	}

	public ExplainBackground() {
		x = 0;
		y = 0;
		img = tk.getImage("res/explainImage.png");
	}

	public void draw(Graphics g, GameExplainCanvas explainCanvas) {
		g.drawImage(img, x, y, x + 850, y + 862, x, y, x + 850, y + 900, explainCanvas);
	}

}
