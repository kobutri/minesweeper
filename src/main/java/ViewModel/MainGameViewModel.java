package ViewModel;

import Model.MainGameModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainGameViewModel {
    private MainGameModel gameModel;
    private final StringProperty timer = new SimpleStringProperty();
    private final StringProperty remainingBombs = new SimpleStringProperty();

    /**Konstruktor des MainGameViewModel
     *
     */
    public MainGameViewModel() {
        gameModel = new MainGameModel();
        initialize();
    }

    /**Konstruktor des MainGameViewModel mit übergebenem MainGameModel
     * @param mainGameModel MainGameModel
     */
    public MainGameViewModel(MainGameModel mainGameModel) {
        this.gameModel = mainGameModel;
        initialize();
    }

    /**Board aus GameModel erstellen
     *
     */
    private void initialize() {
        gameModel.getTimeline().currentTimeProperty().addListener((observable, oldValue, newValue) -> {
             int minutes = (int) newValue.toMinutes();
             int seconds = (int) newValue.toSeconds()%60;
            timer.set(String.format("Timer: %02d:%02d", minutes, seconds));
        });
        remainingBombs.set("Remaining Bombs: " + (gameModel.getMenuModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount()));
        gameModel.getBoardModel().flagCountProperty().addListener((observable, oldValue, newValue) -> remainingBombs.set("Remaining Bombs: " + (gameModel.getBoardModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount())));
    }

    /**Getter zu RemainingBombs
     * @return remainingBombs Value
     */
    public String getRemainingBombs() {
        return remainingBombs.get();
    }

    /**Getter zu remainingBombsProperty
     * @return remainingBombs;
     */
    public StringProperty remainingBombsProperty() {
        return remainingBombs;
    }

    /**Getter zu getGameModel
     * @return gameModel MainGameModel
     */
    public MainGameModel getGameModel() {
        return gameModel;
    }

    /**Getter zu getTimer
     * @return timer Value
     */
    public String getTimer() {
        return timer.get();
    }

    /**Getter zu timerProperty
     * @return timer Stringproperty
     */
    public StringProperty timerProperty() {
        return timer;
    }

    /**Leerer Feld Start aus GameModel und verbleibende Bomben anzeigen
     *
     */
    public void startBlank() {
        remainingBombs.set("Remaining Bombs: " + (gameModel.getMenuModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount()));
        gameModel.startBlank();
    }

    /**Feld neu Laden und Bombencounter anzeigen
     *
     */
    public void restart() {
        remainingBombs.set("Remaining Bombs: " + (gameModel.getMenuModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount()));
        gameModel.restart();
    }

    /**Feld Starten und Bombencounter anpassen
     *
     */
    public void start() {
        remainingBombs.set("Remaining Bombs: " + (gameModel.getMenuModel().getBoardInitializer().getNumBombs() - gameModel.getBoardModel().getFlagCount()));
        gameModel.start();
    }
}
