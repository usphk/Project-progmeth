package display;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Dimension2D;

public class Display extends Application {

	private Dimension2D size = new Dimension2D(1000, 600);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("My Game");
		primaryStage.setWidth(size.getWidth());
		primaryStage.setHeight(size.getHeight());

		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root));

		Game game = new Game();
		root.getChildren().add(game);

		primaryStage.show();
	}
}
