package sceen;

import display.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Wave {
	private double x;
	private double y;
	private Image image;
	private int WaveSize;
	private Game game; // เก็บอ็อบเจกต์ Game เพื่อใช้ในการตรวจสอบการชน

	public Wave(double x, double y, int speed,int waveSize, Game game,Canvas canvas) {
		this.x = x;
		this.y = y;
		this.WaveSize = waveSize;
		this.game = game; // เก็บอ็อบเจกต์ Game

		try {
			this.image = new Image(new FileInputStream("img/tree.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		move(game);
	}



	public void move(Game canvas) {
		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {


				if (x <= 0) {
					x = 1000 + (300 + Math.random() * 1000);
				}

				x -= 30;
				if (x + 50 <= 0) {
					stop();
				}

				game.draw();
			}
		};
		animationTimer.start();
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, x, y, 50, 50);
	}

	public void clear(GraphicsContext gc) {
		if (x == 100) {
			// ใส่โค้ดที่ต้องการเคลียร์
		} else {
			gc.clearRect(x, y, 50, 50);
		}
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
		return WaveSize;
	}
}
