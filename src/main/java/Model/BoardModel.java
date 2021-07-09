package Model;

import backend.Board;
import backend.BoardInitializer;
import backend.CellType;
import backend.WinState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.List;

public class BoardModel {
    private BoardInitializer boardInitializer;
    private Board board;
    private ObservableList<CellModel> cells = FXCollections.observableArrayList();

    public BoardModel(BoardInitializer boardInitializer) {
        assert boardInitializer.isValid();
        this.boardInitializer = boardInitializer;
        this.board = new Board(boardInitializer);
        for (int y = 0; y < boardInitializer.getWidth(); y++) {
            for (int x = 0; x < boardInitializer.getHeight(); x++) {
                cells.add(new CellModel(x, y, false, board.isBomb(x, y) ? CellType.BOMB : CellType.EMPTY, this));
            }
        }
    }

    public BoardModel(BoardInitializer boardInitializer, List<Pair<Integer, Integer>> bombs) {
        assert boardInitializer.isValid();
        this.boardInitializer = boardInitializer;
        this.board = new Board(boardInitializer, bombs);
        for (int y = 0; y < boardInitializer.getWidth(); y++) {
            for (int x = 0; x < boardInitializer.getHeight(); x++) {
                cells.add(new CellModel(x, y, false, board.isBomb(x, y) ? CellType.BOMB : CellType.EMPTY, this));
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public ObservableList<CellModel> getCells() {
        return cells;
    }

    public BoardInitializer getBoardInitializer() {
        return boardInitializer;
    }

    public void setBoardInitializer(BoardInitializer boardInitializer) {
        this.boardInitializer = boardInitializer;
        board = new Board(boardInitializer);

    }

    public WinState hasWon() {
        for (int x = 0; x < boardInitializer.getWidth(); x++) {
            for (int y = 0; y < boardInitializer.getHeight(); y++) {
                if (board.isBomb(x, y) && cells.get(boardInitializer.indexFromIndex2D(x, y)).getOpen().get()){
                    return WinState.LOOSE;
                } else if (!board.isBomb(x, y) && !cells.get(boardInitializer.indexFromIndex2D(x, y)).getOpen().get()) {
                    return WinState.CONTINUE;
                }
            }
        }
        return WinState.WIN;
    }


}
