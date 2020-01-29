package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

public class IntroCanvas extends Canvas {
	StartBackGround bg;
	Sounds sound;

	public IntroCanvas() {
		bg = new StartBackGround();
		sound = new Sounds();
		sound.soundPlay("res/DragonDream.wav");
	}

	@Override
	public void paint(Graphics g) {
		Image bufImage = createImage(getWidth(), getHeight());
		Graphics bufGraphic = bufImage.getGraphics();

		bg.draw(bufGraphic, this);

		g.drawImage(bufImage, 0, 0, this);
	}

	public void soundStop() {
		sound.soundStop();
	}
}
