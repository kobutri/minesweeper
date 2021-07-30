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

    public BoardViewModel(BoardModel boardModel) {
        //Konstruktor BoardViewModel
        this.boardModel = boardModel;
        boardModel.cellsChangedProperty().addListener((observable, oldValue, newValue) -> initialize());
        initialize();
    }

    //Getter
    public ObservableMap<Pair<Integer, Integer>, CellViewModel> getCellViewModels() {
        return cellViewModels;
    }

    public int getCellsChanged() {
        return cellsChanged.get();
    }

    public IntegerProperty cellsChangedProperty() {
        return cellsChanged;
    }

    private void initialize() {
        //Board laden mit Zellen
        cellViewModels.clear();
        boardModel.getCells().forEach(cellModel -> cellViewModels.put(new Pair<>(cellModel.getX(), cellModel.getY()), new CellViewModel(cellModel)));
        cellsChanged.set(cellsChanged.get()+1);
    }

}
