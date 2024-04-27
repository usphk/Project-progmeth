package sceen;

import javafx.animation.AnimationTimer;
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

	public void move(Canvas canvas) {
		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				GraphicsContext gc = canvas.getGraphicsContext2D();
				if (x <= 0) {
					x = 1000 + (300 + Math.random() * 1000);
				}
				x -= 30;
				//gc.clearRect(1, 1, canvas.getWidth(), canvas.getHeight());
				render(gc);
			}
		};
		animationTimer.start();
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, x, y, 50, 50);
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
