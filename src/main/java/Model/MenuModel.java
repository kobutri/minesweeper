package Model;

import backend.BoardInitializer;

public class MenuModel {
    BoardInitializer boardInitializer;
    private MainGameModel gameModel;

    public MenuModel(MainGameModel mainGameModel) {
        this.gameModel = mainGameModel;
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

    public int getMaxBombs() {
        return maxBombs;
    }

    int maxWidth = 10;
    int maxHeight = 10;
    int minWidth = 5;
    int minHeight = 5;
    int minBombs = 5;
    int maxBombs = 10;

    public void start() {
        gameModel.start();
    }
}
