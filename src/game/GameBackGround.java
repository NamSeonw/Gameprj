package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameBackGround {
	private int x;
	private int y;
	private Image img;

	Toolkit tk = Toolkit.getDefaultToolkit();

	public GameBackGround() {
		x = 0;
		y = 0;
		img = tk.getImage("res/gameBackground.png");
	}

	public void draw(Graphics g, GameCanvas gamecanvas) {
		g.drawImage(img, x, y, x + 834, y + 824, x, y, x + 900, y + 879, gamecanvas);
	}
}