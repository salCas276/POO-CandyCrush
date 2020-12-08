package game.frontend;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class LevelFrame extends StackPane {

    public LevelFrame(LevelLoader levelLoader) {

        //Sonido
        Media sound = new Media( new File("./resources/sounds/menu_music.mp3").toURI().toString() );
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(0.15);
        mediaPlayer.play();

        //Fondo del menu
        Image fondo=new Image("images/CANDY-CRUSH-SAGA.jpg");
        ImageView imageView =new ImageView();
        imageView.setImage(fondo);

        //Boton de silencio
        Button silence = new Button();
        silence.setOnAction(event -> {
            if(mediaPlayer.getVolume()==0)
                mediaPlayer.setVolume(0.15);
            else
                mediaPlayer.setVolume(0);
        });
        silence.setStyle("-fx-pref-height: 50;-fx-pref-width:50;-fx-background-color: transparent");
        Image silence_back=new Image("images/silence_button.jpg");
        ImageView silence_back_view= new ImageView(silence_back);
        silence_back_view.setFitHeight(50);
        silence_back_view.setFitWidth(50);
        silence.setGraphic(silence_back_view);

        //Boton1
        Button button1= new Button("Level 1");
        button1.setOnAction(event -> {
            mediaPlayer.stop();
            levelLoader.loadLevel(1);
        });

        //Boton2
        Button button2= new Button("Level 2");
        button2.setOnAction(event -> {
            mediaPlayer.stop();
            levelLoader.loadLevel(2);
        });

        //Boton3
        Button button3= new Button("Level 3");
        button3.setOnAction(event -> {
            mediaPlayer.stop();
            levelLoader.loadLevel(3);
        });

        //Acomoda y da estilo a los botones
        setAlignment(button1, Pos.BOTTOM_LEFT);
        setAlignment(button2,Pos.BOTTOM_CENTER);
        setAlignment(button3,Pos.BOTTOM_RIGHT);
        setAlignment(silence,Pos.TOP_LEFT);

        button1.setStyle("-fx-background-color:#f9e08e;-fx-padding:0;-fx-background-size:0; -fx-pref-width: 200px;-fx-pref-height: 50px;-fx-text-fill: #51060b;-fx-font-size: 25;-fx-font-family: fantasy");
        button2.setStyle("-fx-background-color:#f9e08e;-fx-padding:0;-fx-background-size:0; -fx-pref-width: 200px;-fx-pref-height: 50px;-fx-text-fill: #51060b;-fx-font-size: 25;-fx-font-family: fantasy");
        button3.setStyle("-fx-background-color:#f9e08e;-fx-padding:0;-fx-background-size:0; -fx-pref-width: 200px;-fx-pref-height: 50px;-fx-text-fill: #51060b;-fx-font-size: 25;-fx-font-family: fantasy");

        getChildren().addAll(imageView,button1,button2,button3,silence);
    }

}
