package display;

import Player.Dog;
import event.Event;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import sceen.Environment;
import sceen.Wave;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Game extends Pane {
	private static final int DOG_SIZE = 60;
	private static final int WAVE_HEIGHT = 50;
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
		wave = new Wave(100, BASE - 50, 10, canvas);
		dog = new Dog(100, BASE - 50, 60, 100);

		// Start wave animation
		//wave.move(canvas);


		// Draw initial state
		draw();
	}

	private void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Draw background
		// TODO: Draw background elements

		// Draw dog
		gc.drawImage(dog.getImage(), dog.getX(), dog.getY(), 70, 70);

		gc.setFill(Color.BLACK); // กำหนดสีของข้อความเป็นสีดำ
		gc.fillText("Health: " + dog.getHealth() + "%", 200, 200); // แสดงข้อความ




		// Check hit and draw red rectangle if hit
//		if (Event.checkHit(dog, wave, DOG_SIZE, WAVE_HEIGHT)) {
//			gc.setFill(Color.RED);
//			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//			dog.setHealth(dog.getHealth() - 10);
//			gc.setFill(Color.BLACK);
//			dog.setX(dog.getX()); dog.setY(dog.getY());
//		}
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
