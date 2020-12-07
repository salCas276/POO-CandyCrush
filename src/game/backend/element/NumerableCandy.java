package game.backend.element;


import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//Un candy Numerable es aquel que tiene la capacidad de renderizarse con un numero enfrente suyo
public abstract class NumerableCandy extends Candy{

    int count;
    Color textColor;
    private boolean active;

    NumerableCandy(int count, Color textColor) {
        this.count = count;
        this.textColor = textColor;
        this.active = true;
    }

    public int getCount() {
        return count;
    }

    protected void setCount(int count) {
        this.count = count;
    }

    protected String displayText() {
        return String.valueOf(count);
    }

    private DropShadow displayShadow(Color color) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(3.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(color);
        return dropShadow;
    }

    public boolean isActive() {
        return active;
    }
    public void turnOff() {
        this.active  = false;
    }

    @Override
    public Node render() {
        Text text = new Text(displayText());
        text.setFont(Font.font("Impact", FontWeight.BOLD, 40));
        text.setFill(Color.BLACK);
        text.setEffect(displayShadow(textColor));
        return text;
    }

}
