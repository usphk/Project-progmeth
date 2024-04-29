package Player;

import display.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Dog {
	private int x;
	private int dogSize;
	private int y;
	private double health;
	private static int speed = 90;
	private Image image;
	private Game game;

	public Dog(int x, int y, int dogSize, int health, Game game) {
		this.x = x;
		this.y = y;
		this.dogSize = dogSize;
		this.health = health;
		this.game = game;
		try {
			this.image = new Image(new FileInputStream("img/dog.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Dog() {

	}

	public void jump(Canvas canvas) {
		this.y -= speed;


		// Fall
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(450), event -> {
					y += speed;
					game.draw(); // เรียกเมทอด draw() ในคลาส Game เพื่อวาดภาพหมาใหม่หลังจากกระโดด
				})
		);
		timeline.play();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getHealth() {
		return health;
	}

	public Image getImage() {
		return image;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Dog.speed = speed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void resetHealth(int initialHealth) {

			this.health = initialHealth;
	}
}
