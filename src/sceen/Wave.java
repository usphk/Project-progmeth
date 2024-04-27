package sceen;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Wave {
	private int speed;
	private double x;
	private double y;
	private Image image;

	public Wave(double x, double y, int speed, Canvas canvas) {
		this.x = x;
		this.y = y;
		this.speed = speed;

		try {
			this.image = new Image(new FileInputStream("img/tree.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void move() {
		x -= speed;
		if (x <= -image.getWidth()) {
			x = 1000 + (300 + Math.random() * 1000);
		}
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, x, y);
	}

	public Image getImage() {
		return image;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
