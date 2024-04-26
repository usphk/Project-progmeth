package Element;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class EleButton extends Button {

	public EleButton(String title, int size, double x, double y, double width, double height) {
		super(title);
		this.setStyle("-fx-background-color: #0275D8; -fx-text-fill: white;");
		this.setFont(new Font(size));
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setPrefSize(width, height);
	}
}
