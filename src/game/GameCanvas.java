package game;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameCanvas extends Canvas {
	GameBackGround bg;
	Hand hand;
	Princess pc;
	Sounds sound;

	ItemHand itemhand;
	ItemFlyflap itemflyflap;
	GameFrame frame;

	List<Bug> bugs;
	float genTime;
	int countBug;
	int countDieBug;
	boolean clear;

	int bugX;
	int bugY;

	Random random;

	public GameCanvas() {
		random = new Random();
		frame = GameFrame.getInstance();
		sound = new Sounds();

		sound.soundPlay("res/mogisound.wav");
		sound.clip.loop(10);

		bg = new GameBackGround();
		bugs = new ArrayList<Bug>();
		countBug = 30;
		genTime = 1f;
		countDieBug = 0;
		clear = false;

		for (int i = 0; i < countBug; i++) {
			bugX = random.nextInt(900);
			bugY = random.nextInt(860) - 100;
			if (bugX < 860 && bugY > -50) {
				i--;
				continue;
			} else
				bugs.add(new Bug(bugX, bugY));
		}

		hand = new Hand();
		pc = new Princess();

		itemflyflap = new ItemFlyflap();
		itemhand = new ItemHand();

		// 윈도우 마우스 포인터 숨김.
		this.setCursor(this.getToolkit().createCustomCursor(/**/
				new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), /**/
				new Point(0, 0), "null")/**/
		);

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 49)
					hand.changeHand();
				if (e.getKeyCode() == 50 && countDieBug >= 3)
					hand.changeFlaflap();
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// 현재 무기 이미지가 마우스를 따라감.
				hand.move(e.getX(), e.getY());
				repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// 마우스를 누른 상태에서도 무기 이미지가 따라감
				hand.move(e.getX(), e.getY());
				hand.setPressed(true);
				// 마우스를 누른 상태에서 벌레에 닿으면 그 벌레는 죽음.
				catchBug(e);
				repaint();
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 마우스가 눌려져 있는 동안 공격이 활성화.
				hand.setPressed(true);
				hand.move(e.getX(), e.getY());
				// 벌레에서 자리 판정
				catchBug(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				hand.setPressed(false);
			}
		});

		new Thread(() -> {
			while (!clear) {
				try {
					// 매 프레임마다 마우스의 위치를 업데이트
					hand.update();
					bugMovement();
					Thread.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}).start();
	} // 생성자 종료

	@Override
	public void update(Graphics g) {
		// 벌레가 하나씩 생성되도록 하기 위해
		// 일정 간격의 시간을 추가함.
		genTimeUpdate();
		gameClear();
		this.paint(g);
	}

	@Override
	public void paint(Graphics g) {
		if (!clear) {
			Image bufImage = createImage(getWidth(), getHeight());
			Graphics bufGraphic = bufImage.getGraphics();

			bg.draw(bufGraphic, this);
			hand.draw(bufGraphic, this);
			pc.draw(bufGraphic, this);

			for (Bug bug : bugs) {
				if (bug != null)
					bug.draw(bufGraphic, this);
			}

			itemhand.draw(bufGraphic, this);
			if (countDieBug >= 3)
				itemflyflap.draw(bufGraphic, this);

			g.drawImage(bufImage, 0, 0, this);
		}
	}

	// 벌레들이 하나씩 등장하기 위한 시간
	public void genTimeUpdate() {
		if (genTime > 0)
			genTime += -0.02f;
	}

	// 잡은 벌레 수가 기존에 생성한 벌레수에 도달하면 게임 클리어
	private void gameClear() {
		if (countDieBug == countBug) {
			clear = true;
			sound.soundStop();
			sound.clip.stop();
			frame.switchCanvas(new ClearCanvas());
		}
	}

	// 벌레들의 움직임 제어
	private void bugMovement() {
		for (Bug bug : bugs) {
			if (bug != null) {
				if (bug.isGen()) {
					bug.move();
				} else if (genTime <= 0) {
					bug.gen();
					genTime = random.nextFloat() * 7;
				}
				// 게임 실패 여부
				if (bug.theEnd()) {
					bug.setHp(1000);
					countDieBug = 0;
					sound.soundStop();
					sound.clip.stop();
					pc.gameOverMove();
				}
			}
		}
	}

	// 벌레가 잡혔는가 아닌가 판별
	private void catchBug(MouseEvent e) {
		synchronized (bugs) {
			for (Bug bug : bugs) {
				if (bug.contains(e.getX(), e.getY())/**/
						&& bug.isGen() && bug.isAlive()) {
					hand.attack(bug);
					if (!bug.isAlive())
						countDieBug++;
					System.out.println("attack");
				}
			}
		}
	}
}