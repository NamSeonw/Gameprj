package game;

import java.awt.Image;
import java.awt.Toolkit;

public class Weapon {
	private int damage;
	private int w;
	private int h;
	private int offsetX, offsetY;

	private boolean isHand;
	private Image img;
	private Image smashImg;

	private static Toolkit tk;
	static {
		tk = Toolkit.getDefaultToolkit();
	}

	public Weapon() {
		setHand();
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public Image getImg() {
		return img;
	}

	public void setFlyflap() {
		img = tk.getImage("res/idle.png");
		w = 290;
		h = 240;
		isHand = false;
		damage = 2;
		offsetX = 30;
		offsetY = 29;
	}

	public void setHand() {
		img = tk.getImage("res/handRender.png");
		w = 177;
		h = 204;
		isHand = true;
		damage = 1;
		offsetX = 0;
		offsetY = 0;
	}

	public void smash() {
		if (isHand) {
			smashImg = tk.getImage("res/handRender.png");
		} else {
			smashImg = tk.getImage("res/smash.png");
		}
	}

	public boolean isHand() {
		return isHand;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public Image getSmashImg() {
		return smashImg;
	}

	public int getDamage() {
		return damage;
	}

}
