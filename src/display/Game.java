package display;

import Player.Dog;
import event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sceen.Environment;
import sceen.Wave;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static display.Display.endGame;

public class Game extends Pane {
	public static final int DOG_SIZE = 60;
	public static final int WAVE_HEIGHT = 50;
	private static final int BASE = 400;

	private Canvas canvas;
	private Dog dog;
	private Wave[] waveSet;

	private long lastPress = 0;
	public long point = 0;
	private boolean gameOver = false;

	public Game() {
		this.setPrefSize(1000, 600);
		canvas = new Canvas(1000, 600);
		getChildren().add(canvas);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(e -> keyPressed(e.getCode()));

		waveSet = makeWave(4);

		// Create background elements
		Environment tower = new Environment(100, 100, 1, 0);
		Environment cloud = new Environment(530, 20, 0, 2);
		Environment sky = new Environment(0, 0, 2, 0);
		Environment dir = new Environment(0, 400, 3, 0);

		cloud.createAnimation(this);
		dir.createAnimation(this);
		//dog
		dog = new Dog(100, BASE - 50, 60, 200, this);

		// Initialize game objects
		for (Wave item : waveSet) {

			item.setX(item.getX());
			item.setY(item.getY());
			draw();

		}
	}

	private Wave[] makeWave(int size) {
		Wave[] waveSet = new Wave[size];
		int far = 500; // ระยะห่างระหว่างคลื่นแต่ละคลื่น

		for (int i = 0; i < size; i++) {
			waveSet[i] = new Wave(1000 + far, BASE-50, 30, 50, this, canvas);
		}
		return waveSet;
	}

	public void draw() {
		if (gameOver) {
			return; // ถ้าเกมจบแล้ว ให้หยุดการทำงานของ method นี้
		}

		GraphicsContext gc = canvas.getGraphicsContext2D();

		// Draw background
		// TODO: Draw background elements

		// Draw dog
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.drawImage(dog.getImage(), dog.getX(), dog.getY(), 70, 70);

		// Draw waves
		for (Wave item : waveSet) {
			gc.drawImage(item.getImage(), item.getX(), item.getY(), 50, 50);
			chon(item);
		}

		// Draw points
		gc.setFont(Font.font("Arial", 30));
		gc.setFill(Color.BLACK);
		gc.fillText("Point: " + point, 750, 40);

		// Check hit and draw red rectangle if hit
		drawDogHealth();

		this.point += 1;
	}

	private void chon(Wave wave) {
		if (gameOver) {
			return; // ถ้าเกมจบแล้ว ให้หยุดการทำงานของ method นี้
		}

		if (Event.checkHit(dog, wave, DOG_SIZE, WAVE_HEIGHT)) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setFill(Color.RED);
			gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
			dog.setHealth(dog.getHealth() - 1.2);
			gc.setFill(Color.BLUE);

			if (dog.getHealth() <= 0) {
				endGame(this.point);
				dog.resetHealth(0);
				gameOver = true; // กำหนดว่าเกมจบแล้ว
			}
		}
	}

	private void drawDogHealth() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		try {
			// Draw heart image
			gc.drawImage(new Image(new FileInputStream("img/heart.png")), 40, 20, 30, 30);

			// Set color and draw health bar หลอดเลือด
			gc.setStroke(Color.rgb(241, 98, 69));
			gc.setLineWidth(16.0);
			gc.strokeLine(110, 30, 60 + dog.getHealth() + 40, 30);

			// Draw rectangle border around health bar กรอบหลอดเลือด
			gc.setStroke(Color.GREEN);
			gc.setLineWidth(6.0);
			gc.strokeRect(100, 20, 300, 20);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void keyPressed(KeyCode e) {
		if (gameOver) {
			return; // เกมจบ
		}

		if (System.currentTimeMillis() - lastPress > 600) {
			if (e == KeyCode.SPACE || e == KeyCode.UP) {
				dog.jump(canvas);
				lastPress = System.currentTimeMillis();
			}
		}
	}
}
