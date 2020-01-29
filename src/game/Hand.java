package game;

import java.awt.Graphics;

public class Hand {
	private int x;
	private int y;

	private int mx;
	private int my;

	private int w, h;
	private int imageW, imageH;
	private int imageOffsetX, imageOffsetY;
	private boolean pressed;
	private Weapon weapon;

	Sounds sound;

	public Hand() {
		pressed = false;
		x = 425;
		y = 391;
		mx = 425;
		my = 391;
		w = 35;
		h = 35;
		weapon = new Weapon();

	}

	public void draw(Graphics g, GameCanvas gameCanvas) {
		imageW = weapon.getW();
		imageH = weapon.getH();
		if (pressed) {
			weapon.smash();
			g.drawImage(weapon.getSmashImg(), x - imageOffsetX, y - imageOffsetY, x + w - 10, y + h - 10, /**/
					0, 0, imageW, imageH, gameCanvas);
		} else
			g.drawImage(weapon.getImg(), x - imageOffsetX, y - imageOffsetY, x + w, y + h, /**/
					0, 0, imageW, imageH, gameCanvas);
	}

	public void attack(Bug bug) {
		if (pressed) {
			sound = new Sounds();
			bug.setHp(bug.getHp() - weapon.getDamage());
			if (weapon.isHand())
				sound.soundPlay("res/killbyhand.wav");
			else
				sound.soundPlay("res/flyflap.wav");
			if (bug.getHp() <= 0)
				bug.die();
			pressed = false;
		}
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public void update() {
		x = mx;
		y = my;
	}

	public void move(int x, int y) {
		mx = x;
		my = y;
	}

	public void changeHand() {
		if (!weapon.isHand()) {
			weapon.setHand();
			w = 35;
			h = 35;
			imageOffsetX = weapon.getOffsetX();
			imageOffsetY = weapon.getOffsetY();
		}
	}

	public void changeFlaflap() {
		if (weapon.isHand()) {
			weapon.setFlyflap();
			w = 50;
			h = 50;
			imageOffsetX = weapon.getOffsetX();
			imageOffsetY = weapon.getOffsetY();
		}
	}
}
