package Model;

import backend.BoardInitializer;

public class MenuModel {
    private final BoardInitializer boardInitializer = new BoardInitializer(8, 8, 10);
    int maxWidth = 100;
    int maxHeight = 100;
    int minWidth = 5;
    int minHeight = 5;
    int minBombs = 5;
    //Getter
    public BoardInitializer getBoardInitializer() {
        return boardInitializer;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMinBombs() {
        return minBombs;
    }

    public int getMaxBombs(int width, int height) {
        return Math.max(width*height/5, 10);
    }
}
