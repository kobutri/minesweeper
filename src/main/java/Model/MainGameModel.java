package Model;

import javafx.animation.Animation;
import javafx.animation.Timeline;

public class MainGameModel {
    private final MenuModel menuModel = new MenuModel(this);
    private final BoardModel boardModel = new BoardModel();
    int flagCount = 0;
    private final Timeline timeline;


    public MainGameModel() {
        timeline = new Timeline(60);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public int getFlagCount() {
        return flagCount;
    }

    void start() {
        // start the game
        flagCount = 0;
        boardModel.initializeBoard(menuModel.boardInitializer);
    }

    void reset() {
    }

}
