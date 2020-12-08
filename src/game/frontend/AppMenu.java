package game.frontend;

import game.backend.level.GenericLevel;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.util.Optional;

public class AppMenu extends MenuBar {



    public AppMenu(LevelLoader levelLoader) {

        Menu file = new Menu("Archivo");
        MenuItem exitMenuItem = new MenuItem("Salir");
        exitMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setHeaderText("Salir de la aplicación");
            alert.setContentText("¿Está seguro que desea salir de la aplicación?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent()) {
                if (result.get() == ButtonType.OK) {
                    Platform.exit();
                }
            }
        });
        file.getItems().add(exitMenuItem);

        Menu help = new Menu("Ayuda");
        MenuItem aboutMenuItem = new MenuItem("Acerca De");
        aboutMenuItem.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca De");
            alert.setHeaderText("Candy TPE");
            alert.setContentText("Cátedra POO 2018.\n" +
                    "Implementación Original: Laura Zabaleta (POO 2013).");
            alert.showAndWait();
        });
        help.getItems().add(aboutMenuItem);

        Menu levels = new Menu("Niveles");
        for (int i=1; i<=levelLoader.getQLevels(); i++) {
            MenuItem nivel = new MenuItem(String.format("Nivel %d", i));
            int finalI = i;
            nivel.setOnAction(event -> levelLoader.loadLevel(finalI));
            levels.getItems().add(nivel);
        }
        getMenus().addAll(file, help, levels);


    }

}
