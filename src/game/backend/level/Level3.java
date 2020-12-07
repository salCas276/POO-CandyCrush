package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.Cell;
import game.backend.cell.PriorityCandyGeneratorCell;
import game.backend.element.TimeCandy;
import game.backend.element.TimebombCandy;
import game.backend.element.Wall;

import java.util.ArrayList;
import java.util.List;

public class Level3 extends Grid {

    private static int REQUIRED_SCORE = 5000;
    private static int MAX_MOVES = 1000;
    private static int TIMEBONUS = 3;
    private static int INIT_BONUS = 10;
    private static int INIT_TIME = 600;

    List<TimeCandy> timeCandies = new ArrayList<>();
    private int tcRemaining = TIMEBONUS;
    private Cell wallCell;
    private PriorityCandyGeneratorCell candyGenCell;

    @Override
    protected GameState newState() {
        return new Level3State(REQUIRED_SCORE, MAX_MOVES, INIT_TIME);
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


    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
            for (TimeCandy tc: timeCandies) {
                if (! tc.isActive()) {

                }
            }
            if ( tcRemaining > 0 && state().getMoves() % 3 == 0) {
                TimeCandy newTC = new TimeCandy(INIT_BONUS);
                candyGenCell.addToQueue(newTC);
                timeCandies.add(newTC);
                tcRemaining --;
            }
        }
        return ret;
    }

    private class Level3State extends GameState {

        private long requiredScore;
        private int maxMoves;
        private int time;

        public Level3State(long requiredScore, int maxMoves, int time) {
            this.requiredScore = requiredScore;
            this.maxMoves = maxMoves;
            this.time = time;
        }

        public void updateTime() {
            time --;
        }

        public void addTime(int timeAdder) {
            time += timeAdder;
        }
        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        //TODO: se puede mejorar?
        public boolean playerWon() {
            if (getScore() > requiredScore) {
                return true;
            }
            return false;
        }

    }

}
