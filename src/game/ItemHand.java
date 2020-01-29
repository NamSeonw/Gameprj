package game;

import java.awt.Graphics;

public class ItemHand extends Item {
	public ItemHand() {
		setImg(getTk().getImage("res/handRender.png"));
		setW(177);
		setH(204);
	}

	public void draw(Graphics g, GameCanvas gamecanvas) {
		g.drawImage(getImg(), 400, 720, 400 + 50, 720 + 50, 0, 0, getW(), getH(), gamecanvas);
	}

}
