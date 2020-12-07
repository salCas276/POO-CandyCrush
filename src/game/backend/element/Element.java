package game.backend.element;

import game.backend.move.Direction;
import javafx.scene.Node;


public abstract class Element {

	public boolean isBoomable() {
		return true;
	}

	public abstract boolean isMovable();

	public abstract String getKey();

	public String getFullKey() {
		return getKey();
	}

	public boolean isSolid() {
		return true;
	}

	public Direction[] explode() {
		return null;
	}

	public long getScore() {
		return 0;
	}

	public Node render() {
		return null;
	}

}
