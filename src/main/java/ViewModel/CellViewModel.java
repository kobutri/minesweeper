package ViewModel;

import Model.CellModel;
import backend.CellState;
import backend.CellType;
import backend.WinState;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CellViewModel {
    private final StringProperty number = new SimpleStringProperty();
    private final BooleanProperty isOpen = new SimpleBooleanProperty();
    private final BooleanProperty isFlagged = new SimpleBooleanProperty();
    private final BooleanProperty isBomb = new SimpleBooleanProperty();
    private final BooleanProperty showNumber = new SimpleBooleanProperty();
    private final CellModel model;

    public CellViewModel(CellModel model) {
        this.model = model;
        this.isOpen.bind(model.cellStateProperty().isEqualTo(CellState.CLOSED).or(model.cellStateProperty().isEqualTo(CellState.FLAGGED)).not());
        this.isFlagged.bind(model.cellStateProperty().isEqualTo(CellState.FLAGGED));
        this.isBomb.bind(model.cellStateProperty().isEqualTo(CellState.BOMB));
        this.isBomb.bind(model.typeProperty().isEqualTo(CellType.BOMB));
        this.number.bind(model.numNeighboringBombsProperty().asString());
        this.showNumber.bind(model.cellStateProperty().isEqualTo(CellState.NUMBER));
    }

    public boolean isShowNumber() {
        return showNumber.get();
    }

    public BooleanProperty showNumberProperty() {
        return showNumber;
    }

    public boolean isIsOpen() {
        return isOpen.get();
    }

    public BooleanProperty isOpenProperty() {
        return isOpen;
    }

    public boolean isIsFlagged() {
        return isFlagged.get();
    }

    public BooleanProperty isFlaggedProperty() {
        return isFlagged;
    }

    public boolean isIsBomb() {
        return isBomb.get();
    }

    public BooleanProperty isBombProperty() {
        return isBomb;
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public WinState open() {
        model.open();
        return model.getBoardModel().hasWon();
    }

    public void openAll() {
        model.getBoardModel().openAll();
    }

    public void flag() {
        model.flag();
    }
}