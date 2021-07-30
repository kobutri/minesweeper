package Model;

import backend.CellState;
import backend.CellType;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CellModel {
    private final int x;
    private final int y;
    private final SimpleObjectProperty<CellState> cellState;
    private transient BoardModel boardModel;
    private final SimpleObjectProperty<CellType> type = new SimpleObjectProperty<>();
    private final SimpleIntegerProperty numNeighboringBombs = new SimpleIntegerProperty();

    public CellModel(int x, int y, boolean open, CellType type, BoardModel boardModel) {
        //Konstruktor CellModel
        this.x = x;
        this.y = y;
        this.cellState = new SimpleObjectProperty<>(CellState.CLOSED);
        this.type.set(type);
        this.boardModel = boardModel;
    }

    //einige Getter und Setter
    public CellType getType() {
        return type.get();
    }

    public void setType(CellType type) {
        this.type.set(type);
        if (type == CellType.BOMB && getOpen()) {
            cellState.set(CellState.BOMB);
        }
    }

    public SimpleObjectProperty<CellType> typeProperty() {
        return type;
    }

    public int getNumNeighboringBombs() {
        return numNeighboringBombs.get();
    }

    public void setNumNeighboringBombs(int numNeighboringBombs) {
        this.numNeighboringBombs.set(numNeighboringBombs);
        if (getOpen() && type.get() != CellType.BOMB) {
            if (numNeighboringBombs == 0) {
                cellState.set(CellState.BLANK);
            } else {
                cellState.set(CellState.NUMBER);
            }
        }
    }

    public SimpleIntegerProperty numNeighboringBombsProperty() {
        return numNeighboringBombs;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void setBoardModel(BoardModel boardModel) {
        this.boardModel = boardModel;
    }

    public void open() {
        //Methode um angrenzende graue Felder aufzudecken
        if (!boardModel.isBoardGenerated()) {
            boardModel.generateBoard(x, y);
        }
        if (cellState.get() == CellState.FLAGGED) {
            flag();
        }
        if (type.get() == CellType.BOMB) {
            cellState.set(CellState.BOMB);
        } else if (numNeighboringBombs.get() == 0) {
            cellState.set(CellState.BLANK);
        } else {
            cellState.set(CellState.NUMBER);
        }

        if (numNeighboringBombs.get() == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    try {
                        var index = boardModel.getBoardInitializer().indexFromIndex2D(x + i, y + j);
                        if (!boardModel.getCells().get(index).getOpen() && boardModel.getCells().get(index).getType() != CellType.BOMB) {
                            boardModel.getCells().get(index).open();
                        }
                    } catch (IndexOutOfBoundsException ignored) {
                    }
                }
            }
        }
    }

    public void flag() {
        //setzt und entfernt Flaggen und zählt

        if (cellState.get() == CellState.FLAGGED) {
            cellState.set(CellState.CLOSED);
            boardModel.flagCountProperty().set(boardModel.getFlagCount() - 1);
        } else if (cellState.get() == CellState.CLOSED && boardModel.getFlagCount() < boardModel.getBoardInitializer().getNumBombs()) {
            cellState.set(CellState.FLAGGED);
            boardModel.flagCountProperty().set(boardModel.getFlagCount() + 1);
        }
    }

    //mehr Getter
    public CellState getCellState() {
        return cellState.get();
    }

    public SimpleObjectProperty<CellState> cellStateProperty() {
        return cellState;
    }

    public boolean getOpen() {
        return !(cellState.get() == CellState.FLAGGED || cellState.get() == CellState.CLOSED);
    }
}
