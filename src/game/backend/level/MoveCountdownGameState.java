package game.backend.level;

import game.backend.GameState;

public abstract class MoveCountdownGameState extends GameState {

    int maxMoves;

    public MoveCountdownGameState(int maxMoves) {
        this.maxMoves = maxMoves;
    }

    public boolean gameOver() {
        return playerWon() || getMoves() >= maxMoves;
    }

    @Override
    public String toString() {
        return "MOVES: "+(maxMoves-getMoves());
    }

}
