package ileinterdite.model.adventurers;

import ileinterdite.model.Grid;
import ileinterdite.util.Utils.Pawn;

public class Navigator extends Adventurer {

    public static final Pawn PAWN = Pawn.YELLOW;
    public static final String CLASS_NAME = "Navigateur";

    public Navigator() {
        super();
    }

    public Navigator(Grid grid) {
        super(grid);
    }

    public Navigator(int x, int y) {
        super(x, y);
    }

    public Navigator(Grid grid, int x, int y) {
        super(grid, x, y);
    }

    @Override
    public Pawn getPawn() {
        return PAWN;
    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }
}