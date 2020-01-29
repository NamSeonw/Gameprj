package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Bug {
	private int x, y; // 벌레의 좌표
	private int w, h; // 벌레의 크기
	private int hp; // 벌레의 hp
	private int vx, vy;

	private int offsetX, offsetY;

	private Random random;
	private boolean isAlive;
	private boolean isGen;

	private Image img;
	private static Toolkit tk;
	static {
		tk = Toolkit.getDefaultToolkit();
	}

	public boolean isGen() {
		return isGen;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public Bug(int x, int y) {
		random = new Random();
		this.x = x;
		this.y = y;
		w = 40;
		h = 35;
		offsetX = w / 2;
		offsetY = h / 2;
		setHp(2);
		img = tk.createImage("res/mogiDark.png");
		isAlive = true;
		isGen = false;
	}

	public void draw(Graphics g, GameCanvas gamecanvas) {
		if (isAlive)
			g.drawImage(img, x - offsetX, y - offsetY, gamecanvas);
		else
			g.drawImage(img, x - offsetX, y - offsetY, x - offsetX + w, y - offsetY + h, /**/
					0, 0, 1023, 752, gamecanvas);
	}

	public void move() {
		if (isAlive) {
			// 벡터를 구하기
			float dx = x - 30;
			float dy = y - 804;
			float dis = (float) Math.sqrt(dx * dx + dy * dy);

			vx = (int) (dx / dis * random.nextFloat() * (random.nextInt(8) + 3));
			vy = (int) (dy / dis * random.nextFloat() * (random.nextInt(8) + 3));

			// 벌레의 위치 업데이트
			x += vx * (-1);
			y += vy * (-1);
		}
	}

	public void die() {
		vx = 0;
		vy = 0;
		isAlive = false;
		img = tk.getImage("res/diemogiBig.png");
	}

	public boolean contains(int mx, int my) {
		int dis = (int) Math.round(Math.sqrt((x - mx) * (x - mx) + (y - my) * (y - my)));
		if (dis <= 17) {
			return true;
		} else
			return false;
	}

	public void gen() {
		isGen = true;
	}

	public boolean theEnd() {
		if (x < 261 && y > 511)
			return true;
		else
			return false;
	}
}
