package display;

import Charactor.Dog;
import Charactor.Environment;
import Charactor.Wave;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.sun.webkit.graphics.WCImage.getImage;

public class Game extends Pane {
	private static final int SPEED = 50;
	private static final int DOG_SIZE = 60;
	private static final int WAVE_HEIGHT = 50;
	private static final int BASE = 400;
	private static final int X_START = 1000;
	public Canvas canvas;

	private long point = 0;
	private long lastPress = 0;
	private Dog dog = new Dog(100, BASE - 50);
	private Wave[] waves;
	private Environment background;

	public Game() {
		this.setPrefSize(1000, 600);
		canvas = new Canvas(1000, 600);
		getChildren().add(canvas);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(e -> keyPressed(e.getCode()));

		// สร้างอาร์เรย์ของ Wave ด้วย Canvas และตำแหน่งเริ่มต้น
		waves = makeWave(3); // สร้าง Wave 3 ตัวเพื่อต่อเนื่อง

		// สร้างพื้นหลัง
		background = new Environment(100, 100, 1, 0);
		//Environment kot = new Environment(0,0,0,0);

		background.createAnimation(this);
		//kot.createAnimation(this);

		draw(); // วาดสถานะเริ่มต้น
	}

	private void draw() {
		// วาดพื้นหลัง
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image backgroundImage = Environment.getImage();



		// วาดทุกอย่างบน Canvas
		for (Wave wave : waves) {
			wave.render(gc);
		}

		// วาดหมา
		gc.drawImage(dog.getImage(), dog.getX(), dog.getY(), 70, 70);
	}

	private Wave[] makeWave(int size) {
		Wave[] waveSet = new Wave[size];
		int far = 500;
		for (int i = 0; i < size; i++) {
			waveSet[i] = new Wave(800 + far * i, 300, 20, canvas);
		}
		return waveSet;
	}

	private void keyPressed(KeyCode e) {
		// จัดการกับการกดคีย์
		if (System.currentTimeMillis() - lastPress > 600) {
			if (e == KeyCode.SPACE || e == KeyCode.UP) {
				dog.jump(canvas);
				lastPress = System.currentTimeMillis();
			}
		}
	}
}
