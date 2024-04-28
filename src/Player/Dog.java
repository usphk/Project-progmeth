package Player;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Dog {
	private int x;
	public int dogsize;
	private int y;
	public int health ;
	private static int speed = 90;
	private Image image;

	public Dog(int x, int y,int dogsize,int health) {
		this.x = x;
		this.y = y;
		this.dogsize =dogsize;
		this.health= health;
		try {
			this.image = new Image(new FileInputStream("img/dog.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void jump(Canvas canvas) {
		this.y -= speed;
		draw(canvas.getGraphicsContext2D(), 70); // ส่งขนาดเข้าไปในเมธอด draw
		// Fall
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(450), event -> {
					y += speed;
					draw(canvas.getGraphicsContext2D(), 70); // ส่งขนาดเข้าไปในเมธอด draw
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

	public int getHealth() {
		return health;
	}

	public Image getImage() {
		return image;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		Dog.speed = speed;
	}

	private void draw(GraphicsContext gc, double size) {
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.drawImage(image, x, y, size, size); // ใช้ขนาดที่ส่งเข้ามาในการวาดรูปภาพ
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}