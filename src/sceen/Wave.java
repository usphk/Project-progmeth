package sceen;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Wave {
	public int speed;
	private double x;
	private double y;
	private Image image;
	public int wavesize = 50;

	public Wave(double x, double y, int speed, Canvas canvas) {
		this.x = x;
		this.y = y;
		this.speed = speed;

		try {
			this.image = new Image(new FileInputStream("img/tree.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		move(canvas);
	}

	public void move(Canvas canvas) {
		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				GraphicsContext gc = canvas.getGraphicsContext2D();
				clear(gc); // เคลียรูปร่างคลื่นก่อนที่จะวาดใหม่

				if (x <= 0) {
					x = 1000 + (300 + Math.random() * 1000);
				}

				x -= 30;
				if (x + 50 <= 0) {
					stop();
				}
				render(gc); // วาดคลื่นใหม่
			}
		};
		animationTimer.start();
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, x, y, 50, 50);
	}

	public void clear(GraphicsContext gc) {
		gc.clearRect(x, y, 50, 50);
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

	public double getWaveSize() {
		return wavesize;
	}
}
