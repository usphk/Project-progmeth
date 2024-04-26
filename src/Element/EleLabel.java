package Element;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class EleLabel extends Label {

	public EleLabel(String title, int size, double x, double y, double w, double h) {
		super(title);
		setFont(new Font(size));
		setLayoutX(x);
		setLayoutY(y);
		setPrefSize(w, h);
	}
}
