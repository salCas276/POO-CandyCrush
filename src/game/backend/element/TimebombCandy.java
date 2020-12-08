package game.backend.element;


import game.backend.move.Direction;
import javafx.scene.paint.Color;

public class TimebombCandy extends NumerableCandy {

    private boolean active;

    public TimebombCandy(int count) {
        super(count);
        this.active = true;

    }

    public void decrement() {
        if (isActive()) setCount( getCount() - 1 );
    }

    public boolean hasExploded() {
        return getCount() == 0;
    }

    public boolean isActive() {
        return active;
    }
    public void turnOff() {
        this.active  = false;
    }

    @Override
    public Direction[] explode() {
        turnOff();
        return super.explode();
    }

    @Override
    public String getRenderKey() {
        return Color.ORANGERED+"_"+super.getRenderKey();
    }

}