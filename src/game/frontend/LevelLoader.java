package game.frontend;

import game.backend.CandyGame;
import game.backend.level.GenericLevel;
import game.backend.level.Level1;
import game.backend.level.Level2;
import game.backend.level.Level3;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class LevelLoader {

    Stage primaryStage;
    List<Class<? extends GenericLevel>> levels = new ArrayList();

    public LevelLoader(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void loadLevel(int levelID) {
        CandyGame game=new CandyGame(levels.get(levelID-1));
        CandyFrame frame = new CandyFrame(game, this);
        Scene scene = new Scene(frame);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addLevel(Class<? extends GenericLevel> level) {
        levels.add(level);
    }

    public int getQLevels() {
        return levels.size();
    }
}
