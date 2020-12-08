package game.backend.element;

import game.backend.Renderable;
import game.backend.move.Direction;

public abstract class Element implements Renderable<String> {

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

	@Override
	public String getRenderData() {
		return "";
	}
	public String getRenderKey() {
		return "NO_EFFECT";
	}
}
