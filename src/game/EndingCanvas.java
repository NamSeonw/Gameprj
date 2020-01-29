package game;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class EndingCanvas extends Canvas implements ActionListener {
	GameFrame frame;
	Sounds sound;
	Image myImage;
//	= new ImageIcon("res/gameClear.png").getImage();
	Timer timer;
//	= new Timer(100, this);
	private float alpha;
//	= 0f;

	public EndingCanvas() {
		frame = GameFrame.getInstance();
		myImage = new ImageIcon("res/gameover.png").getImage();
		timer = new Timer(100, this);
		alpha = 0f;

		sound = new Sounds();
		sound.soundPlay("res/ending.wav");

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				sound.soundStop();
				frame.switchCanvas(new IntroCanvas(), true);
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sound.soundStop();
				frame.switchCanvas(new IntroCanvas(), true);
			}
		});

		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		alpha += +0.15f;
		if (alpha >= 1) {
			alpha = 1;
			timer.stop();
		}
		repaint();
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.drawImage(myImage, 0, 0, 850, 862, 0, 0, 850, 862, this);

		Font font = new Font("궁서체", Font.BOLD, 32);
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString("Press any key", 300, 400);
	}

}
