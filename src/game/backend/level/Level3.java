package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.cell.PriorityCandyGeneratorCell;
import game.backend.element.Fruit;
import game.backend.element.Nothing;
import game.backend.element.TimebombCandy;
import game.backend.element.Wall;

import java.util.*;


public class Level3 extends GenericLevel {
	
	private static int MAX_MOVES = 100;
	private static int FRUITS = 3;

	Set<Fruit> fruits = new HashSet<>();
	private int fruitsRemaining = FRUITS;
	private int fruitGap = 3;
	private PriorityCandyGeneratorCell candyGenCell = new PriorityCandyGeneratorCell(this);


	@Override
	protected GameState newState() {
		return new Level3State(MAX_MOVES);
	}

	protected CandyGeneratorCell getLevelGeneratorCell() {
		return candyGenCell;
	}


	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean isMoveValid = super.tryMove(i1, j1, i2, j2);
		if (isMoveValid) {
			if ( fruitsRemaining > 0 && state().getMoves() % fruitGap == 0) {
				Fruit newFruit = new Fruit();
				candyGenCell.addToStack(newFruit);
				fruits.add(newFruit);
				fruitsRemaining --;
			}
			checkLastRow();
		}
		return isMoveValid;
	}

	private void checkLastRow() {
		for (Cell cell : g()[SIZE - 1]) {
			if (fruits.contains(cell.getContent())) {
				fruits.remove(cell.getAndClearContent());
				fallElements();
				checkLastRow();
				return;
			}
		}
	}

	private class Level3State extends MoveCountdownGameState {

		public Level3State(int maxMoves) {
			super(maxMoves);
		}

		public boolean playerWon() {
			return fruits.isEmpty() && fruitsRemaining == 0;
		}

		@Override
		public String toString() {
			return super.toString()+ " // Fruits: "+(fruitsRemaining+fruits.size());
		}
	}

}
