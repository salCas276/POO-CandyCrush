package game.backend.element;


import game.backend.move.Direction;

public class TimebombCandy extends NumerableCandy {

    //TODO: esta bien el flag?
    private boolean active;

    public TimebombCandy(int count) {
        super(count);
        this.active = true;
    }

    public void decrement() {
        setCount( getCount() - (isActive()?1:0) );
    }

    public boolean isActive() {
        return active;
    }

    public boolean hasExploded() {
        return getCount() == 0;
    }
    public void turnOff() {
        this.active  = false;
    }

    @Override
    public Direction[] explode() {
        turnOff();
        return super.explode();
    }

}