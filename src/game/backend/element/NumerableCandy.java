package game.backend.element;

public abstract class NumerableCandy extends Candy{

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

}
