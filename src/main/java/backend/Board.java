package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    private final BoardInitializer boardInitializer;


    private final CellType[] cellTypes;

    public Board(BoardInitializer boardInitializer) throws IllegalArgumentException {
        if(!boardInitializer.isValid()){
            throw new IllegalArgumentException("invalid board initializer");
        }
        this.boardInitializer = boardInitializer;

        assert boardInitializer.getWidth()*boardInitializer.getHeight() < boardInitializer.getNumBombs();

        cellTypes = new CellType[boardInitializer.getWidth()*boardInitializer.getHeight()];
        Arrays.fill(cellTypes, CellType.EMPTY);

        var bombs = generateBombs(cellTypes.length, boardInitializer.getNumBombs(), boardInitializer.indexFromIndex2D(boardInitializer.getxNotABomb(), boardInitializer.getyNotABomb()));
        for (var bomb : bombs) {
            cellTypes[bomb] = CellType.BOMB;
        }
    }
    
    public int neighboringBombs(int x, int y) {
        int numBombs = 0;
        for (int i = -1; i < 1; i++) {
            for (int j = -1; j < 1; j++) {
                try {
                    if (isBomb(x+i, y+j)){
                        numBombs++;
                    }
                } catch (Exception ignored) {}
            }
        }
        return numBombs;
    }
    
    public boolean isBomb(int x, int y) {
        try {
            return cellTypes[boardInitializer.indexFromIndex2D(x, y)] == CellType.BOMB;
        } catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    private static ArrayList<Integer> generateBombs(int size, int numBombs, int notABomb) {
        ArrayList<Integer> chosenIndices = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            indices.add(i, i);
        }
        indices.removeIf(integer -> integer == notABomb);

        Random rand = new Random();
        for (int i = 0; i < numBombs; i++) {
            int chosenArrayIndex = rand.nextInt(indices.size());
            int chosenIndex = indices.remove(chosenArrayIndex);
            chosenIndices.add(chosenIndex);
        }
        return chosenIndices;
    }

    public BoardInitializer getBoardInitializer() {
        return boardInitializer;
    }
}
