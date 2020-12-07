package game.frontend;

import game.backend.CandyGame;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
import game.backend.level.Level4;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Image fondo=new Image("images/CANDY-CRUSH-SAGA.jpg");
		ImageView imageView =new ImageView();
		imageView.setImage(fondo);
		StackPane container = new StackPane();

		Button button1= new Button("Level 1");
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CandyGame game=new CandyGame(Level1.class);
				CandyFrame frame = new CandyFrame(game);
				Scene scene = new Scene(frame);
				primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.show();
			}

		});
		Button button2= new Button("Level 2");
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CandyGame game=new CandyGame(Level2.class);
				CandyFrame frame = new CandyFrame(game);
				Scene scene = new Scene(frame);
				primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.show();
			}
		});
		Button button3= new Button("Level 3");
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CandyGame game=new CandyGame(Level4.class);
				CandyFrame frame = new CandyFrame(game);
				Scene scene = new Scene(frame);
				primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.show();
			}
		});
		container.setAlignment(button1, Pos.BOTTOM_LEFT);
		container.setAlignment(button2,Pos.BOTTOM_CENTER);
		container.setAlignment(button3,Pos.BOTTOM_RIGHT);
		container.getChildren().addAll(imageView,button1,button2,button3);
		Scene primeMenu = new Scene(container);
		primaryStage.setScene(primeMenu);
		primaryStage.show();

	}

}
