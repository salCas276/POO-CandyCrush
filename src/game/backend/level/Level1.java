package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.Wall;

public class Level1 extends GenericLevel {
	
	private static int REQUIRED_SCORE = 5000; 
	private static int MAX_MOVES = 5;

	@Override
	protected GameState newState() {
		return new Level1State(REQUIRED_SCORE, MAX_MOVES);
	}

	
	private class Level1State extends MoveCountdownGameState {

		private long requiredScore;

		public Level1State(long requiredScore, int maxMoves) {
			super(maxMoves);
			this.requiredScore = requiredScore;
		}

		public boolean playerWon() {
			return getScore() > requiredScore;
		}

		@Override
		public String toString() {
			return String.format("SCORE: %d/%d //", getScore(), requiredScore)+super.toString();
		}
	}

}
