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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Level2 extends Grid {

	private static int REQUIRED_SCORE = 5000;
	private static int MAX_MOVES = 10;
	private static int TIMEBOMBS = 3;
	private static int TIMEBOMB_INIT_COUNTER = 10;

	List<TimebombCandy> timebombs = new ArrayList<>();
	private int tbRemaining = TIMEBOMBS;
	private Cell wallCell;
	private PriorityCandyGeneratorCell candyGenCell;


	@Override
	protected GameState newState() {
		return new Level2State(REQUIRED_SCORE, MAX_MOVES);
	}

	@Override
	protected void fillCells() {

		wallCell = new Cell(this);
		wallCell.setContent(new Wall());

		candyGenCell = new PriorityCandyGeneratorCell(this);

		//corners
		g()[0][0].setAround(candyGenCell, g()[1][0], wallCell, g()[0][1]);
		g()[0][SIZE-1].setAround(candyGenCell, g()[1][SIZE-1], g()[0][SIZE-2], wallCell);
		g()[SIZE-1][0].setAround(g()[SIZE-2][0], wallCell, wallCell, g()[SIZE-1][1]);
		g()[SIZE-1][SIZE-1].setAround(g()[SIZE-2][SIZE-1], wallCell, g()[SIZE-1][SIZE-2], wallCell);

		//upper line cells
		for (int j = 1; j < SIZE-1; j++) {
			g()[0][j].setAround(candyGenCell,g()[1][j],g()[0][j-1],g()[0][j+1]);
		}
		//bottom line cells
		for (int j = 1; j < SIZE-1; j++) {
			g()[SIZE-1][j].setAround(g()[SIZE-2][j], wallCell, g()[SIZE-1][j-1],g()[SIZE-1][j+1]);
		}
		//left line cells
		for (int i = 1; i < SIZE-1; i++) {
			g()[i][0].setAround(g()[i-1][0],g()[i+1][0], wallCell ,g()[i][1]);
		}
		//right line cells
		for (int i = 1; i < SIZE-1; i++) {
			g()[i][SIZE-1].setAround(g()[i-1][SIZE-1],g()[i+1][SIZE-1], g()[i][SIZE-2], wallCell);
		}
		//central cells
		for (int i = 1; i < SIZE-1; i++) {
			for (int j = 1; j < SIZE-1; j++) {
				g()[i][j].setAround(g()[i-1][j],g()[i+1][j],g()[i][j-1],g()[i][j+1]);
			}
		}
	}

	//TODO: ADDER NO ESTA PARA NADA CHEQUEADO
	private TimebombCandy adder;

	@Override
	public boolean tryMove(int i1, int j1, int i2, int j2) {
		boolean ret;
		if (ret = super.tryMove(i1, j1, i2, j2)) {
			state().addMove();
			//TODO: Por que no eliminamos los inactivos?
			for(TimebombCandy tb: timebombs) {
				tb.decrement();
			}
			if (adder != null) {
				timebombs.add(adder);
				adder = null;
			}
			if ( tbRemaining > 0 && state().getMoves() % 3 == 0) {
				TimebombCandy newTB = new TimebombCandy(TIMEBOMB_INIT_COUNTER);
				candyGenCell.addToQueue(newTB);
				adder = newTB;
				tbRemaining --;
			}
		}
		return ret;
	}

	private class Level2State extends GameState {

		private long requiredScore;
		private long maxMoves;
		private int timebombsRemaining = TIMEBOMB_INIT_COUNTER;

		public Level2State(long requiredScore, int maxMoves) {
			this.requiredScore = requiredScore;
			this.maxMoves = maxMoves;
		}

		public boolean gameOver() {
			for (TimebombCandy tb: timebombs) {
				if (tb.hasExploded()) return true;
			}
			return playerWon() || getMoves() >= maxMoves;
		}

		//TODO: se puede mejorar?
		public boolean playerWon() {
			if (getScore() > requiredScore) {
				for (TimebombCandy tc: timebombs) {
					if (tc.isActive())
						return false;
				}
				return true;
			}
			return false;
		}

		private int lowestTimebombCount () {
			int i = TIMEBOMB_INIT_COUNTER;
			for (TimebombCandy tb: timebombs) {
				if (tb.isActive() && tb.getCount() < i) i = tb.getCount();
			}
			return i;
		}

		@Override
		public String toString() {
			return super.toString()+" // MOVES: "+(maxMoves-getMoves())+" // BOMB IN: "+(timebombs.isEmpty()?"-":lowestTimebombCount());
		}
	}

}
