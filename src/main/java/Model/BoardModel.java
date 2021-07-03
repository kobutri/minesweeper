package Model;

import backend.Board;
import backend.CellState;
import backend.WinState;
import javafx.collections.ObservableList;

public class BoardModel {
    public Board board;
    public ObservableList<CellState> cells;
    public int width;
    public int height;

    public BoardModel(int width, int height, int numBombs, int xNotABomb, int yNotABomb) {
        this.board = new Board(width, height, numBombs, xNotABomb, yNotABomb);
        this.height = height;
        this.width = width;
        assert width > 0;
        assert height > 0;
        // make cells
    }

    public void flag(int x, int y) {
        if (!cells.get(board.indexFromIndex2D(x, y)).isOpen()) {
            cells.get(board.indexFromIndex2D(x, y)).setFlagged(true);
        }
    }

    public WinState open(int x, int y) {
        if (board.isBomb(x, y)){
            return WinState.LOOSE;
        } else {
            cells.get(board.indexFromIndex2D(x, y)).setFlagged(false);
            int numNeighboringBombs = board.neighboringBombs(x, y);
            cells.get(board.indexFromIndex2D(x, y)).setOpen(true); // TODO what if is flagged?
            cells.get(board.indexFromIndex2D(x, y)).setNumber(numNeighboringBombs);
            if (numNeighboringBombs == 0){
                for (int i = -1; i < 1; i++) {
                    for (int j = -1; j < 1; j++) {
                        open(x+i, y+j);
                    }
                }
            }
            if (hasWon()) {
                return WinState.WIN;
            } else {
                return WinState.CONTINUE;
            }

        }
    }

    public boolean hasWon() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!board.isBomb(x, y) && !cells.get(board.indexFromIndex2D(x, y)).isOpen()) {
                    return false;
                }
            }
        }
        return true;
    }
}
