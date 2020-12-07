package game.backend.element;

import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TimeCandy extends NumerableCandy {

    public TimeCandy(int count) {
        super(count, Color.BLUEVIOLET);
    }

    @Override
    public Node render() {
        Node text = super.render();
        return text;
    }

    @Override
    protected String displayText() {
        return "+"+super.displayText();
    }
}
