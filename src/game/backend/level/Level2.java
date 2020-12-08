package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.cell.PriorityCandyGeneratorCell;
import game.backend.element.CandyColor;
import game.backend.element.NumerableCandy;
import game.backend.element.TimebombCandy;
import game.backend.element.Wall;

import java.sql.Time;
import java.util.*;

public class Level2 extends GenericLevel {
	
	private static int MAX_MOVES = 100;
	private static int TIMEBOMBS = 3;
	private static int TIMEBOMB_INIT_COUNTER = 10;

	Set<TimebombCandy> timebombs = new HashSet<>();
	private int tbRemaining = TIMEBOMBS;

	private PriorityCandyGeneratorCell candyGenCell = new PriorityCandyGeneratorCell(this);



	@Override
	protected GameState newState() {
		return new Level2State(MAX_MOVES);
	}

	@Override
	protected CandyGeneratorCell getLevelGeneratorCell() {
		return candyGenCell;
	}

	//Como el PriorityCandyGeneratorCell agrega a la cola, es el próximo en salir
	//Pero no sale inmediatamente. Por eso tengo que esperar hasta el próximo turno para agregarlo a la colección
	private TimebombCandy adder;

	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean isMoveValid = super.tryMove(i1, j1, i2, j2);
		if (isMoveValid) {
			for(TimebombCandy tb: timebombs) {
				tb.decrement();
			}
			if (adder != null) {
				tbRemaining --;
				timebombs.add(adder);
				adder = null;
			}
			if ( tbRemaining > 0 && state().getMoves() % 3 == 0) {
				TimebombCandy newTB = new TimebombCandy(TIMEBOMB_INIT_COUNTER);
				candyGenCell.addToQueue(newTB);
				adder = newTB;
			}
			timebombs.removeIf((t)->!t.isActive());
		}
		return isMoveValid;
	}

	
	private class Level2State extends MoveCountdownGameState {


		public Level2State(int maxMoves) {
			super(maxMoves);
		}

		public boolean gameOver() {
			for (TimebombCandy tb: timebombs) {
				if (tb.hasExploded()) return true;
			}
			return super.gameOver();
		}

		public boolean playerWon() {
			return tbRemaining == 0 && timebombs.isEmpty();
		}

		private int lowestTimebombCount () {
			int i = TIMEBOMB_INIT_COUNTER;
			for (TimebombCandy tb: timebombs) {
				if (tb.getCount() < i) i = tb.getCount();
			}
			return i;
		}

		@Override
		public String toString() {
			return super.toString()+" // BOMBS LEFT: "+(tbRemaining+timebombs.size())+"// BOOM IN: "+(timebombs.isEmpty()?"-":lowestTimebombCount());
		}
	}

}
