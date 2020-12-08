package game.frontend;


import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		LevelLoader levelLoader = new LevelLoader(primaryStage);

		levelLoader.addLevel(Level1.class);
		levelLoader.addLevel(Level2.class);
		levelLoader.addLevel(Level3.class);

		Scene primeMenu = new Scene(new LevelFrame(levelLoader));
		primaryStage.setResizable(false);
		primaryStage.setScene(primeMenu);
		primaryStage.show();
	}

}
