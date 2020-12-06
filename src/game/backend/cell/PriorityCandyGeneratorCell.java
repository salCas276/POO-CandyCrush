package game.backend.cell;

import game.backend.Grid;
import game.backend.element.Element;
import game.backend.element.NumerableCandy;

import java.util.*;

public class PriorityCandyGeneratorCell extends CandyGeneratorCell {

    Deque<Element> priorities = new LinkedList<>();

    public PriorityCandyGeneratorCell(Grid grid) {
        super(grid);
    }

    @Override
    public Element getContent() {
        if (priorities.isEmpty())
            return super.getContent();
        else
            return priorities.pop();
    }

    public void addToQueue(Element adder) {
        priorities.push(adder);
    }
}
