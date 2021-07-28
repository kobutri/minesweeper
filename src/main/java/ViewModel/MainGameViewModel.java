package ViewModel;

import Model.MainGameModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainGameViewModel {
    private MainGameModel gameModel;
    private final StringProperty timer = new SimpleStringProperty();
    private final StringProperty remainingBombs = new SimpleStringProperty();

    public MainGameViewModel() {
        gameModel = new MainGameModel();
        initialize();
    }

    public MainGameViewModel(MainGameModel mainGameModel) {
        this.gameModel = mainGameModel;
        initialize();
    }

    private void initialize() {
        gameModel.getTimeline().currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            int minutes = (int) newValue.toMinutes();
            int seconds = (int) newValue.toSeconds();
            timer.set(String.format("Timer: %02d:%02d", minutes, seconds));
        });
        remainingBombs.set("Remaining Bombs: " + (gameModel.getMenuModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount()));
        gameModel.getBoardModel().flagCountProperty().addListener((observable, oldValue, newValue) -> remainingBombs.set("Remaining Bombs: " + (gameModel.getBoardModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount())));
    }

    public String getRemainingBombs() {
        return remainingBombs.get();
    }

    public StringProperty remainingBombsProperty() {
        return remainingBombs;
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

    public void startBlank() {
        remainingBombs.set("Remaining Bombs: " + (gameModel.getMenuModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount()));
        gameModel.startBlank();
    }

    public void restart() {
        remainingBombs.set("Remaining Bombs: " + (gameModel.getMenuModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount()));
        gameModel.restart();
    }

    public void start() {
        remainingBombs.set("Remaining Bombs: " + (gameModel.getMenuModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount()));
        gameModel.start();
    }
}
