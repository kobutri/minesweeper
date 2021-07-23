package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class MainGameModel {
    private MenuModel menuModel = new MenuModel(this);

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public double getTimer() {
        return timer;
    }

    public int getFlagCount() {
        return flagCount;
    }

    private BoardModel boardModel = new BoardModel();
    double timer = 0;
    int flagCount = 0;

    void start() {
        // start the game
        flagCount = 0;
        timer = 0;
        boardModel.initializeBoard(menuModel.boardInitializer);
    }

}
