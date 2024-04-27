package display;

import Player.Dog;
import sceen.Environment;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import sceen.Wave;

public class Game extends Pane {
	private static final int SPEED = 50;
	private static final int DOG_SIZE = 60;
	private static final int WAVE_HEIGHT = 50;
	private static final int BASE = 400;
	private static final int X_START = 1000;
	public Canvas canvas;
	Wave wave = new Wave(100, BASE-50, 10, canvas);

	private long point = 0;
	private long lastPress = 0;
	private Dog dog = new Dog(100, BASE -50);

	private Environment background;

	public Game() {
		this.setPrefSize(1000, 600);
		canvas = new Canvas(1000, 600);
		getChildren().add(canvas);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(e -> keyPressed(e.getCode()));

		// สร้างพื้นหลัง
		Environment tower= new Environment(100, 100, 1, 0);
		Environment cloud = new Environment(400, 40, 0, 0);
		Environment sky = new Environment(0,0,2,0);
		Environment dir = new Environment(0,400,3,0);

		sky.createAnimation(this);
		tower.createAnimation(this);
		cloud.createAnimation(this);
		dir.createAnimation(this);


		// เรียกเมธอด move() เพื่อเริ่มการเคลื่อนที่ของ Wave

		wave.move(canvas);


		draw(); // วาดสถานะเริ่มต้น

	}

	private void draw() {
		// วาดพื้นหลัง
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Image backgroundImage = Environment.getImage();

		// วาด wave
		wave.render(gc);

		// วาดหมา
		gc.drawImage(dog.getImage(), dog.getX(), dog.getY(), 70, 70);
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
