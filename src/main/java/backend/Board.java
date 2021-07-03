package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
    private final int width;
    private final int height;
    private final int numBombs;


    private final Cell[] cells;

    public Board(int width, int height, int numBombs, int xNotABomb, int yNotABomb) {
        this.width = width;
        this.height = height;
        this.numBombs = numBombs;

        assert width*height < numBombs;

        cells = new Cell[width*height];
        Arrays.fill(cells, Cell.EMPTY);

        var bombs = generateBombs(cells.length, numBombs, indexFromIndex2D(xNotABomb, yNotABomb));
        for (var bomb : bombs) {
            cells[bomb] = Cell.BOMB;
        }
    }

    public int indexFromIndex2D(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0) throw new IndexOutOfBoundsException();
        if (y < 0) throw new IndexOutOfBoundsException();
        if (x > width) throw new IndexOutOfBoundsException();
        if (y > height) throw new IndexOutOfBoundsException();
        
        return y * width + x;
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
    
    public boolean isBomb(int x, int y) throws IndexOutOfBoundsException {
        return cells[indexFromIndex2D(x, y)] == Cell.BOMB;
    }

    private static ArrayList<Integer> generateBombs(int size, int numBombs, int notABomb) {
        ArrayList<Integer> chosenIndices = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            indices.set(i, i);
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
}
