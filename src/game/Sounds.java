package game;

import java.awt.Frame;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds extends Frame {
	AudioInputStream stream;
	Clip clip;

	// 노래 삽입
	public void soundPlay(String fileName) {
		try {
			stream = AudioSystem.getAudioInputStream(new File(fileName));
			clip = AudioSystem.getClip();
			clip.open(stream);
			clip.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 인트로 노래 정지
	public void soundStop() {
		clip.stop();// 음악재생 메서드 호출. 파라미터로 파일의 위치와 이름을 써줘야 한다.
	}
}
