package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.Cell;
import game.backend.cell.PriorityCandyGeneratorCell;
import game.backend.element.Fruit;
import game.backend.element.TimebombCandy;
import game.backend.element.Wall;

import java.util.*;


public class Level4 extends Grid {

    private static int REQUIRED_SCORE = 5000;
    private static int MAX_MOVES = 100;
    private static int FRUITS = 3;

    Set<Fruit> fruits = new HashSet<>();
    private int fruitsRemaining = FRUITS;
    private int fruitGap = 3;
    private Cell wallCell;
    private PriorityCandyGeneratorCell candyGenCell;


    @Override
    protected GameState newState() {
        return new Level4State(REQUIRED_SCORE, MAX_MOVES);
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
            if ( fruitsRemaining > 0 && state().getMoves() % fruitGap == 0) {
                Fruit newFruit = new Fruit();
                candyGenCell.addToQueue(newFruit);
                fruits.add(newFruit);
                fruitsRemaining --;
            }
            state().addMove();
            checkLastRow();
        }
        return ret;
    }

    private void checkLastRow() {
        for (Cell cell : g()[SIZE - 1]) {
            if (fruits.contains(cell.getContent())) {
                fruits.remove(cell.getContent());
                cell.clearContent();
                fallElements();
                checkLastRow();
                return;
            }
        }
    }
    private class Level4State extends GameState {

        private long requiredScore;
        private long maxMoves;

        public Level4State(long requiredScore, int maxMoves) {
            this.requiredScore = requiredScore;
            this.maxMoves = maxMoves;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        //TODO: se puede mejorar?
        public boolean playerWon() {
            if (getScore() > requiredScore) {
                return fruits.isEmpty();
            }
            return false;
        }


        @Override
        public String toString() {
            System.out.println(fruitsRemaining);
            System.out.println(fruits.size());
            return super.toString()+" // MOVES: "+(maxMoves-getMoves())+" // Fruits: "+(fruitsRemaining+fruits.size());
        }
    }

}
