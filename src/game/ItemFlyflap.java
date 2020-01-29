package game;

import java.awt.Graphics;

public class ItemFlyflap extends Item {
	public ItemFlyflap() {
		setImg(getTk().getImage("res/idle.png"));
		setW(400);
		setH(350);
	}

	public void draw(Graphics g, GameCanvas gamecanvas) {
		g.drawImage(getImg(), 450, 720, 450 + 50, 720 + 50, 0, 0, 290, 240, gamecanvas);
	}
}
