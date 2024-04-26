package display;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends StackPane {

	private long point;

	public Menu(long point) {
		this.point = point;
		this.setBackground(new javafx.scene.layout.Background(new javafx.scene.layout.BackgroundFill(Color.rgb(241, 98, 69), null, null)));

		Text status = new Text("You Died!");
		status.setFont(Font.font("Arial", 40));
		status.setFill(Color.WHITE);
		status.setTranslateY(-100);

		Text showPoint = new Text("Total : " + this.point);
		showPoint.setFont(Font.font("Arial", 30));
		showPoint.setFill(Color.WHITE);

		Button restart = new Button("Restart");
		restart.setFont(Font.font("Arial", 15));
		restart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (onRestart != null) {
					onRestart.handle(event);
				}
			}
		});

		this.getChildren().addAll(status, showPoint, restart);
		StackPane.setAlignment(status, Pos.TOP_CENTER);
		StackPane.setAlignment(showPoint, Pos.CENTER);
		StackPane.setAlignment(restart, Pos.BOTTOM_CENTER);
	}

	public long getPoint() {
		return point;
	}

	public void setPoint(long point) {
		this.point = point;
	}

	private EventHandler<ActionEvent> onRestart;

	public void setOnRestart(EventHandler<ActionEvent> handler) {
		this.onRestart = handler;
	}
}
