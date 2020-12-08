package game.backend.element;


import game.backend.Renderable;
import javafx.scene.paint.Color;

public abstract class NumerableCandy extends Candy implements Renderable<String> {

    int count;

    NumerableCandy(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    protected void setCount(int count) {
        this.count = count;
    }

    @Override
    public String getRenderData() {
        return String.valueOf(this.getCount());
    }

    @Override
    public String getRenderKey() {
        return "SHADOW";
    }
}
