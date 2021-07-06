package Model;

import backend.CellType;
import backend.WinState;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CellModel {
    private final int x;
    private final int y;

    public BoardModel getBoardModel() {
        return boardModel;
    }

    private final BoardModel boardModel;
    private BooleanProperty open;
    private CellType type;
    private int numNeighboringBombs = 0;

    public CellModel(int x, int y, boolean open, CellType type, BoardModel boardModel) {
        this.x = x;
        this.y = y;
        this.open = new SimpleBooleanProperty(open);
        this.type = type;
        this.boardModel = boardModel;
        numNeighboringBombs = boardModel.getBoard().neighboringBombs(x, y);
    }

    public BooleanProperty getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open.set(open);
    }

    public CellType getType() {
        return type;
    }

    public int getNumNeighboringBombs() {
        return numNeighboringBombs;
    }

    public void open() {
        open.set(true);
        if (numNeighboringBombs == 0) {
            for (int i = -1; i < 1; i++) {
                for (int j = -1; j < 1; j++) {
                    boardModel.getCells().get(boardModel.getBoardInitializer().indexFromIndex2D(x + i, y + j)).open();
                }
            }
        }
    }
}
