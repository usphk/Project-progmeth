package display;

import Player.Dog;
import event.Event;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import sceen.Environment;
import sceen.Wave;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game extends Pane {
	public static final int DOG_SIZE = 60;
	public static final int WAVE_HEIGHT = 50;
	private static final int BASE = 400;

	private Canvas canvas;
	private Wave wave;
	private Dog dog;

	private long lastPress = 0;

	public Game() {
		this.setPrefSize(1000, 600);
		canvas = new Canvas(1000, 600);
		getChildren().add(canvas);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(e -> keyPressed(e.getCode()));

		// Create background elements
		Environment tower = new Environment(100, 100, 1, 0);
		Environment cloud = new Environment(400, 40, 0, 0);
		Environment sky = new Environment(0, 0, 2, 0);
		Environment dir = new Environment(0, 400, 3, 0);

		sky.createAnimation(this);
		tower.createAnimation(this);
		cloud.createAnimation(this);
		dir.createAnimation(this);

		// Initialize game objects
		wave = new Wave(100, BASE - 50, 10, 50, this,canvas); // ส่ง this เพื่อระบุว่า Game คืออ็อบเจกต์ปัจจุบันที่กำลังใช้งาน
		dog = new Dog(100, BASE - 50, 60, 100,this );

		// Draw initial state
		draw();
		drawDogHealth(); // เรียกใช้เมทอด drawDogHealth ที่ถูกเพิ่มเข้ามา
	}


	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Draw background
		// TODO: Draw background elements

		// Draw dog
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.drawImage(dog.getImage(), dog.getX(), dog.getY(), 70, 70);

		//
		gc.drawImage(wave.getImage(), wave.getX(), wave.getY(), 50, 50);

		// Check hit and draw red rectangle if hit
		chon(); // เรียกเมทอด chon() เพื่อตรวจสอบการชนและลดเลือด
	}

	public void chon() {
		if (Event.checkHit(dog, wave, DOG_SIZE, WAVE_HEIGHT)) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setFill(Color.RED);
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			dog.setHealth(dog.getHealth() - 10);
			gc.setFill(Color.BLACK);
			dog.setX(dog.getX());
			dog.setY(dog.getY());
		}
	}

	private void drawDogHealth() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		try {
			// Draw heart image
			gc.drawImage(new Image(new FileInputStream("img/heart.png")), 100, 100, 50, 50);

			// Set color and draw health bar
			gc.setStroke(Color.rgb(241, 98, 69));
			gc.setLineWidth(18.0);
			gc.strokeLine(60, 30, 60 + dog.getHealth(), 30);

			// Draw rectangle border around health bar
			gc.setStroke(Color.WHITE);
			gc.setLineWidth(6.0);
			gc.strokeRect(50, 20, 200, 20);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void keyPressed(KeyCode e) {
		if (System.currentTimeMillis() - lastPress > 600) {
			if (e == KeyCode.SPACE || e == KeyCode.UP) {
				dog.jump(canvas);
				lastPress = System.currentTimeMillis();
			}
		}
	}
}
