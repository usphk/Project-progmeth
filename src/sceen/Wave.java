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
	int speed;
	private Game game; // เก็บอ็อบเจกต์ Game เพื่อใช้ในการตรวจสอบการชน

	public Wave(double x, double y, int speed,int waveSize, Game game) {
		this.x = x;
		this.y = y;
		this.speed = speed;
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
				x -= speed;
				game.draw();
			}
		};
		animationTimer.start();
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

	public void setImage(Image image) {
		this.image = image;
	}

	public void setWaveSize(int waveSize) {
		WaveSize = waveSize;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public double getWaveSize() {
		return WaveSize;
	}

	public void setX(double x) {
		this.x =x;
	}

	public void setY(double y) {
		this.y=y;
	}
}
