package game.backend.element;

public class Fruit extends Element {

    @Override
    public boolean isBoomable() {
        return false;
    }

    private FruitType fruitType;

    public  boolean isMovable() {
        return true;
    }

    public Fruit () {
        int i = (int)(Math.random() * FruitType.values().length);
        fruitType = FruitType.values()[i];
    }

    @Override
    public String getKey(){
        return "FRUIT";
    }

    @Override
    public String getFullKey() {
        return fruitType.toString()+"-"+getKey();
    }
    //No implementa equals porque cada fruta es unica en lo ojos de la funcionalidad

}
