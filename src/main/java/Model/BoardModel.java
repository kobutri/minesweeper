package Model;

import backend.BoardInitializer;
import backend.CellType;
import backend.WinState;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Random;

public class BoardModel {
    private BoardInitializer boardInitializer;
    private final ObservableList<CellModel> cells = FXCollections.observableArrayList();
    private boolean boardGenerated = false;
    private boolean boardInitialized = false;

    private static ArrayList<Integer> generateBombs(BoardInitializer initializer, int xNotABomb, int yNotABomb) {
        int size = initializer.getWidth() * initializer.getHeight();
        ArrayList<Integer> chosenIndices = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            indices.add(i, i);
        }
        ArrayList<Integer> remove = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                try {
                    int index = initializer.indexFromIndex2D(xNotABomb + i, yNotABomb + j);
                    remove.add(index);
                } catch (Exception ignored) {
                }
            }
        }
        indices.removeAll(remove);
        assert initializer.getNumBombs() <= indices.size();

        Random rand = new Random();
        for (int i = 0; i < initializer.getNumBombs(); i++) {
            int chosenArrayIndex = rand.nextInt(indices.size());
            int chosenIndex = indices.remove(chosenArrayIndex);
            chosenIndices.add(chosenIndex);
        }
        return chosenIndices;
    }

    public boolean isBoardGenerated() {
        return boardGenerated;
    }

    public boolean isBoardInitialized() {
        return boardInitialized;
    }

    public ObservableList<CellModel> getCells() {
        return cells;
    }

    public BoardInitializer getBoardInitializer() {
        return boardInitializer;
    }

    public void generateBoard(int xNotABomb, int yNotABomb) {
        if (0 > xNotABomb || 0 > yNotABomb || boardInitializer.getWidth() <= xNotABomb || boardInitializer.getHeight() <= yNotABomb) {
            throw new IllegalArgumentException("initial clicked value out of board");
        }
        var bombs = generateBombs(boardInitializer, xNotABomb, yNotABomb);
        for (var bomb : bombs) {
            cells.get(bomb).setType(CellType.BOMB);
        }
        for(var cell : cells) {
            cell.setNumNeighboringBombs(neighboringBombs(cell.getX(), cell.getY()));
        }
        boardGenerated = true;

        //System.out.println((new Gson()).toJson(cells.get(0)));
    }

    public void initializeBoard(BoardInitializer boardInitializer) {
        if (!boardInitializer.isValid()) {
            throw new IllegalArgumentException("board initializer invalid");
        }
        this.boardInitializer = boardInitializer;
        cells.clear();
            for (int y = 0; y < boardInitializer.getHeight(); y++) {
            for (int x = 0; x < boardInitializer.getWidth(); x++) {
                cells.add(new CellModel(x, y, false, CellType.EMPTY, this));
            }
        }
        boardInitialized = true;
        boardGenerated = false;
    }

    public WinState hasWon() {
        boolean shouldGameContinue = false;
        for (int x = 0; x < boardInitializer.getWidth(); x++) {
            for (int y = 0; y < boardInitializer.getHeight(); y++) {
                if (isBomb(x, y) && cells.get(boardInitializer.indexFromIndex2D(x, y)).getOpen()) {
                    return WinState.LOOSE;
                } else if (!isBomb(x, y) && !cells.get(boardInitializer.indexFromIndex2D(x, y)).getOpen()) {
                    shouldGameContinue = true;
                }
            }
        }
        return shouldGameContinue ? WinState.CONTINUE : WinState.WIN;
    }

    private boolean isBomb(int x, int y) {
        try {
            var index = boardInitializer.indexFromIndex2D(x, y);
            return cells.get(index).getType() == CellType.BOMB;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public int neighboringBombs(int x, int y) {
        int numBombs = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isBomb(x + i, y + j)) {
                    numBombs++;
                }
            }
        }
        return numBombs;
    }

    public void openAll() {
        for (var cell : cells) {
            cell.open();
        }
    }
}
