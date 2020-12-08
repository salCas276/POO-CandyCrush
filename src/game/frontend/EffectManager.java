package game.frontend;


import game.backend.Renderable;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.*;
import java.util.function.Function;

public class EffectManager {

    private Map<String, Function<Renderable<String>, Node>> effects = new HashMap<>();
    private Set<Color> colorPalette = new HashSet<>();

    public EffectManager() {

        colorPalette.add(Color.ORANGERED);
        colorPalette.add(Color.PINK);
        colorPalette.add(Color.BLUEVIOLET);

        for (Color c: colorPalette) {
            effects.put(c+"_SHADOW", (Renderable<String> e) -> {
                Text text = new Text(e.getRenderData());
                text.setFont(Font.font("Impact", FontWeight.BOLD, 40));
                text.setFill(Color.BLACK);
                text.setEffect(makeColorShadow(c));
                return text;
            }  );
        }

        effects.put("NO_EFFECT", (o)->null);
    }


    public Function<Renderable<String>, Node> getEffect(Renderable<String> e){
        return effects.getOrDefault(e.getRenderKey(), effects.get("NO_EFFECT"));
    }

    private DropShadow makeColorShadow(Color color) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(color);
        return dropShadow;
    }


}
