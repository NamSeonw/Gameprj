package game;

import java.awt.Image;
import java.awt.Toolkit;

public class Item {
	private Image img;
	private int w;
	private int h;
	private static Toolkit tk;
	static {
		tk = Toolkit.getDefaultToolkit();
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int w) {
		this.h = w;
	}

	public static Toolkit getTk() {
		return tk;
	}
}
