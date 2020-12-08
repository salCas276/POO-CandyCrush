package game.backend;

public interface Renderable<T> {

    T getRenderData();
    String getRenderKey();

}
