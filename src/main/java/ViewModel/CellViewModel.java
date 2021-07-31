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

    /**Konstruktor des CellViewModels
     * @param model CellModel
     */
    public CellViewModel(CellModel model) {
        this.model = model;
        this.isOpen.bind(model.cellStateProperty().isEqualTo(CellState.CLOSED).or(model.cellStateProperty().isEqualTo(CellState.FLAGGED)).not());
        this.isFlagged.bind(model.cellStateProperty().isEqualTo(CellState.FLAGGED));
        this.isBomb.bind(model.cellStateProperty().isEqualTo(CellState.BOMB));
        this.isBomb.bind(model.typeProperty().isEqualTo(CellType.BOMB));
        this.number.bind(model.numNeighboringBombsProperty().asString());
        this.showNumber.bind(model.cellStateProperty().isEqualTo(CellState.NUMBER));
    }


    /**Getter zu ShowNumber
     * @return showNumber Value
     */
    public boolean isShowNumber() {
        return showNumber.get();
    }

    /**Getter zu showNumberProperty
     * @return showNumber
     */
    public BooleanProperty showNumberProperty() {
        return showNumber;
    }

    /**Getter zu isOpen
     * @return isOpen Value
     */
    public boolean isIsOpen() {
        return isOpen.get();
    }

    /**Getter zu isOpenProperty
     * @return isOpen
     */
    public BooleanProperty isOpenProperty() {
        return isOpen;
    }

    /**Getter zu isFlagged
     * @return isFlagged Value
     */
    public boolean isIsFlagged() {
        return isFlagged.get();
    }

    /**Getter zu isFlaggedProperty
     * @return isFlagged
     */
    public BooleanProperty isFlaggedProperty() {
        return isFlagged;
    }

    /**Getter zu isBomb
     * @return isBomb Value
     */
    public boolean isIsBomb() {
        return isBomb.get();
    }

    /**Getter zu isBombProperty
     * @return isBomb
     */
    public BooleanProperty isBombProperty() {
        return isBomb;
    }

    /**Getter zu getNumber
     * @return number Value
     */
    public String getNumber() {
        return number.get();
    }

    /**Getter zu numberProperty
     * @return number
     */
    public StringProperty numberProperty() {
        return number;
    }

    /**im Falle von Sieg
     * @return WinState
     */
    public WinState open() {
        model.open();
        return model.getBoardModel().hasWon();
    }

    /**Felder aufdecken
     *
     */
    public void openAll() {
        model.getBoardModel().openAll();
    }

    /**Flaggen entfernen, hinzufügen
     *
     */
    public void flag() {
        model.flag();
    }
}