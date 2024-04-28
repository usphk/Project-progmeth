package display;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Dimension2D;

import java.io.FileNotFoundException;

public class Display extends Application {

	private Dimension2D size = new Dimension2D(1000, 600);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		primaryStage.setTitle("My Game");
		primaryStage.setWidth(size.getWidth());
		primaryStage.setHeight(size.getHeight());

		StackPane root = new StackPane();
		primaryStage.setScene(new Scene(root));

		Game game = new Game();
		root.getChildren().add(game);

		primaryStage.show();
	}

	public static void endGame(long point) {
		Platform.runLater(() -> {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Game Over");
			alert.setHeaderText(null);
			alert.setContentText("Game Over! Your Score: " + point);
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					System.exit(0); // กดokออกเกมนะจะ
				}
			});
		});
	}
}
