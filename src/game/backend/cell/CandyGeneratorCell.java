package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Candy;
import game.backend.element.CandyColor;
import game.backend.element.Element;

public class CandyGeneratorCell extends Cell {

	public CandyGeneratorCell(Grid grid) {
		super(grid);
	}

	@Override
	public boolean isMovable(){
		return true;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Element getContent() {
		return new Candy();
	}

	@Override
	public Element getAndClearContent() {
		return getContent();
	}

	@Override
	public boolean fallUpperContent() {
		throw new IllegalStateException();
	}

	@Override
	public void setContent(Element content) {
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}

}
