package Model;

import backend.BoardInitializer;
import backend.CellType;
import backend.WinState;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.Random;

public class BoardModel {
    private final ObservableList<CellModel> cells = FXCollections.observableArrayList();
    private final IntegerProperty cellsChanged = new SimpleIntegerProperty(0);
    private final IntegerProperty flagCount = new SimpleIntegerProperty(0);

    private BoardInitializer boardInitializer;
    private boolean boardGenerated = false;
    private boolean boardInitialized = false;

    private static ArrayList<Integer> generateBombs(BoardInitializer initializer, int xNotABomb, int yNotABomb) {
        //Bomben auf dem Feld verteilen
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

    //einige Getter
    public int getFlagCount() {
        return flagCount.get();
    }

    public IntegerProperty flagCountProperty() {
        return flagCount;
    }

    public int getCellsChanged() {
        return cellsChanged.get();
    }

    public IntegerProperty cellsChangedProperty() {
        return cellsChanged;
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
        // Board erstellen mit Bomben
        if (0 > xNotABomb || 0 > yNotABomb || boardInitializer.getWidth() <= xNotABomb || boardInitializer.getHeight() <= yNotABomb) {
            throw new IllegalArgumentException("initial clicked value out of board");
        }
        var bombs = generateBombs(boardInitializer, xNotABomb, yNotABomb);
        for (var bomb : bombs) {
            cells.get(bomb).setType(CellType.BOMB);
        }
        for (var cell : cells) {
            cell.setNumNeighboringBombs(neighboringBombs(cell.getX(), cell.getY()));
        }
        boardGenerated = true;
    }

    public void initializeBlankBoard(BoardInitializer boardInitializer) {
        // Leeres Board erstellen
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
        flagCount.set(0);
        cellsChanged.set(cellsChanged.get() + 1);
    }

    public WinState hasWon() {
        //erkennt ob Spiel gewonnen wurde oder ob es weiter gehen kann
        boolean shouldGameContinue = false;
        for (int x = 0; x < getBoardInitializer().getWidth(); x++) {
            for (int y = 0; y < getBoardInitializer().getHeight(); y++) {
                if (isBomb(x, y) && cells.get(getBoardInitializer().indexFromIndex2D(x, y)).getOpen()) {
                    return WinState.LOOSE;
                } else if (!isBomb(x, y) && !cells.get(getBoardInitializer().indexFromIndex2D(x, y)).getOpen()) {
                    shouldGameContinue = true;
                }
            }
        }
        return shouldGameContinue ? WinState.CONTINUE : WinState.WIN;
    }

    private boolean isBomb(int x, int y) {
        //prüft ob das angeklickte Feld eine Bombe ist oder nicht
        try {
            var index = getBoardInitializer().indexFromIndex2D(x, y);
            return cells.get(index).getType() == CellType.BOMB;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }


    public int neighboringBombs(int x, int y) {
        //Zählt die angrenzenden Bomben an eine Zelle
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
        //Deckt graue Felder auf
        for (var cell : cells) {
            cell.open();
        }
    }

    public void initializeBoard() {
        //Feld laden
        boardInitialized = true;
        cellsChanged.set(cellsChanged.get() + 1);
    }
}
