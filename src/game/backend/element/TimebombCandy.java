package game.backend.element;


import game.backend.move.Direction;
import javafx.scene.paint.Color;

public class TimebombCandy extends NumerableCandy {

    //TODO: esta bien el flag?

    public TimebombCandy(int count) {
        super(count, Color.ORANGERED);
    }

    public void decrement() {
        setCount( getCount() - (isActive()?1:0) );
    }



    public boolean hasExploded() {
        return getCount() == 0;
    }

    @Override
    public Direction[] explode() {
        turnOff();
        return super.explode();
    }

}