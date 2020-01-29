package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Princess {
	private int x;
	private int y;
	private int w;
	private int h;
	private Toolkit tk;
	private int moveCount;

	Image img;
	Random rand;
	GameFrame frame;
	Sounds sound;

	public Princess() {
		x = 130;
		y = 540;
		w = 250;
		h = 400;
		moveCount = 0;

		tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/princess.png");
		frame = GameFrame.getInstance();
		rand = new Random();
	}

	public void gameOverMove() {
		if (moveCount == 0) {
			sound = new Sounds();
			sound.soundPlay("res/공주비명.wav");
		}
		x = rand.nextInt(300) + 100;
		y = rand.nextInt(300) + 50;

		moveCount++;
		if (moveCount == 100) {
			sound.soundStop();
			frame.switchCanvas(new EndingCanvas());
		}
	}

	public void draw(Graphics g, GameCanvas gamecanvas) {
		g.drawImage(img, x, y, x + 130, y + 250, 0, 0, w, h, gamecanvas);
	}

}
