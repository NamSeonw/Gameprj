package game;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameFrame extends Frame implements ActionListener {
	// 화면을 그릴 캔버스
	Canvas canvas;
	JButton gameStart;
	JButton gameMethod;
	JButton gameExit;
	JButton back;

	public static GameFrame frame;

	public static GameFrame getInstance() {
		if (frame == null)
			frame = new GameFrame();
		return frame;
	}

	private GameFrame() {
		setBounds(0, 0, 850, 862);
		// 게임시작 버튼
		gameStart = new JButton(new ImageIcon("res/buttonStart.png"));
		gameStart.setLocation(575, 568);
		gameStart.setSize(270, 77); // 270 77
		gameStart.setRolloverIcon(new ImageIcon("res/changeStart.PNG"));
		add(gameStart);
		gameStart.addActionListener(this);

		// 게임방법 버튼
		gameMethod = new JButton(new ImageIcon("res/buttonMethod.PNG"));
		gameMethod.setLocation(575, 665);
		gameMethod.setSize(270, 77);
		add(gameMethod);
		gameMethod.setRolloverIcon(new ImageIcon("res/changeMethod.png"));
		gameMethod.addActionListener(this);

		// 나가기 버튼
		gameExit = new JButton(new ImageIcon("res/buttonExit.PNG"));
		gameExit.setLocation(575, 762);
		gameExit.setSize(270, 77);
		gameExit.setRolloverIcon(new ImageIcon("res/changeExit.png"));
		add(gameExit);
		gameExit.addActionListener(this);

		// 게임설명에서 뒤로가서 인트로로 가는 버튼
		ImageIcon backIcon = new ImageIcon("res/backIcon.png");
		back = new JButton(backIcon);
		back.setLocation(50, 50);
		back.setSize(100, 80);

		// 게임 시작 화면 생성
		canvas = new IntroCanvas();
		add(canvas);
		canvas.setFocusable(true);
		canvas.requestFocus();

		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 게임시작 버튼 클릭
		if (e.getSource() == gameStart) {
			// GameCanvas로 전환
			((IntroCanvas) canvas).sound.soundStop();
			switchCanvas(new GameCanvas(), false);
			// 게임방법 버튼 클릭
		} else if (e.getSource() == gameMethod) {
			add(back);
			back.setVisible(true);
			back.addActionListener(this);
			// 게임 설명 캔버스 전환
			switchCanvas(new GameExplainCanvas(), false);
			// 뒤로가기 버튼 클릭
		} else if (e.getSource() == back) {
			// 게임 시작 화면으로 전환
			switchCanvas(new IntroCanvas(), true);
			back.setVisible(false);
			// 나가기 버튼 클릭
		} else if (e.getSource() == gameExit) {
			System.exit(0);
		}
	}

	// 캔버스 전환
	public void switchCanvas(Canvas tocanvas) {
		System.out.println("Switching");
		gameStart.setVisible(false);
		gameMethod.setVisible(false);
		gameExit.setVisible(false);
		remove(canvas);
		canvas = tocanvas;
		add(canvas);
		canvas.setFocusable(true);
		canvas.requestFocus();
		revalidate();
	}

	// 캔버스 전환(버튼 컨트롤)
	public void switchCanvas(Canvas tocanvas, boolean buttonOnoff) {
		System.out.println("Switching");
		gameStart.setVisible(buttonOnoff);
		gameMethod.setVisible(buttonOnoff);
		gameExit.setVisible(buttonOnoff);
		remove(canvas);
		canvas = tocanvas;
		add(canvas);
		canvas.setFocusable(true);
		canvas.requestFocus();
		revalidate();
	}

}