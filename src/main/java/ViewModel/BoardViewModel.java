package ViewModel;

import Model.BoardModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.util.Pair;

public class BoardViewModel {
    private final BoardModel boardModel;
    private final ObservableMap<Pair<Integer, Integer>, CellViewModel> cellViewModels = FXCollections.observableHashMap();
    private final IntegerProperty cellsChanged = new SimpleIntegerProperty(0);

    /** Konstruktor BoardViewModel
     * @param boardModel BoardModel
     */
    public BoardViewModel(BoardModel boardModel) {
        this.boardModel = boardModel;
        boardModel.cellsChangedProperty().addListener((observable, oldValue, newValue) -> initialize());
        initialize();
    }

    /** Getter zu CellViewModels
     * @return cellViewModels CellViewModel
     */
    public ObservableMap<Pair<Integer, Integer>, CellViewModel> getCellViewModels() {
        return cellViewModels;
    }

    /**Getter zu cellsChanged
     * @return cellsChanged Value
     */
    public int getCellsChanged() {
        return cellsChanged.get();
    }

    /**Getter zu cellsChangedProperty
     * @return cellsChanged
     */
    public IntegerProperty cellsChangedProperty() {
        return cellsChanged;
    }

    /**Board laden mit Zellen
     *
     */
    private void initialize() {
        cellViewModels.clear();
        boardModel.getCells().forEach(cellModel -> cellViewModels.put(new Pair<>(cellModel.getX(), cellModel.getY()), new CellViewModel(cellModel)));
        cellsChanged.set(cellsChanged.get()+1);
    }

}
