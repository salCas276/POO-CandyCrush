package game.backend.element;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MoreTimeCandy extends NumerableCandy {

    public MoreTimeCandy(int count) {
        super(count);
    }

    @Override
    public String getRenderData() {
        return "+"+super.getRenderData();
    }

    @Override
    public String getRenderKey() {
        return Color.BLUEVIOLET+"_"+super.getRenderKey();
    }
}
