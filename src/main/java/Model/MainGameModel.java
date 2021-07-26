package Model;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class MainGameModel {
    private final MenuModel menuModel = new MenuModel(this);
    private final BoardModel boardModel = new BoardModel();
    static public int flagAmount=10;
    static IntegerProperty flagCount = new SimpleIntegerProperty(10);
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
        return flagCount.getValue();
    }
    public IntegerProperty getFlagProb(){
        return flagCount;
    }

    public void setFlagCount(int i){
        flagCount.setValue(i);
    }

    public int getFlagAmount(){
        return flagAmount;
    }

    public void setFlagAmount(int i){
        flagAmount=i;
    }

    void start() {
        // start the game
        getFlagCount();
        boardModel.initializeBoard(menuModel.boardInitializer);
    }

    void reset() {
    }

}
