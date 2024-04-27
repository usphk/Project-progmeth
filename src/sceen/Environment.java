package sceen;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Environment {

	private double x;
	private double y;
	private double startX;
	private int speed;
	private int eType;
	public static final int CLOUD = 0;
	public static final int BUILDING = 1;
	private TranslateTransition transition;
	private static ImageView imageView;

	public Environment(double x, double y, int eType, int speed) {
		this.x = x;
		this.y = y;
		this.startX = x;
		this.speed = speed;
		this.eType = eType;
	}

	public void createAnimation(Pane pane) {
		imageView = new ImageView();
		try {
			imageView.setImage(new Image(new FileInputStream("img/" + getEvType(this.eType))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		imageView.setX(x);
		imageView.setY(y);
		pane.getChildren().add(imageView);

		transition = new TranslateTransition(Duration.millis(10), imageView);
		transition.setByX(-speed);
		transition.setCycleCount(Animation.INDEFINITE);
		transition.play();
	}

	public void stop() {
		transition.stop();
	}

	private String getEvType(int eType) {
		String[] name = new String[]{"cloud.png", "building.png","sky.png","dir.png"};
		return name[eType];
	}

	public Node getImageView() {
		return imageView;
	}

	public static Image getImage() {
		return imageView.getImage();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
