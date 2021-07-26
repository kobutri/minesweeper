package Model;

import ViewModel.BoardViewModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

import java.io.IOException;
import java.security.Key;
import java.sql.Time;

public class MainGameModel {
    private final MenuModel menuModel = new MenuModel();
    private final BoardModel boardModel = new BoardModel();
    private Timeline timeline;



    public MainGameModel() {
        startTimer();

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

    private void startTimer() {
        timeline = new Timeline(new KeyFrame(Duration.INDEFINITE));
        timeline.setCycleCount(1);
        timeline.play();
    }

    public void start(){
        startTimer();
        boardModel.initializeBoard(menuModel.getBoardInitializer());
    }

}
