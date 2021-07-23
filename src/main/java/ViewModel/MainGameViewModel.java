package ViewModel;

import Model.MainGameModel;

public class MainGameViewModel {
    public MainGameModel getGameModel() {
        return gameModel;
    }

    private MainGameModel gameModel = new MainGameModel();
}
