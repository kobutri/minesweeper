package ViewModel;

import Model.MainGameModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainGameViewModel {
    private final MainGameModel gameModel = new MainGameModel();
    private StringProperty timer = new SimpleStringProperty();

    public MainGameViewModel() {
        gameModel.getTimeline().currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            timer.setValue(newValue.toString());
        });
    }

    public MainGameModel getGameModel() {
        return gameModel;
    }

    public String getTimer() {
        return timer.get();
    }

    public StringProperty timerProperty() {
        return timer;
    }
}
