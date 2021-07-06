package ViewModel;

import Model.BoardModel;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class BoardViewModel {
    public Map<Pair<Integer, Integer>, CellViewModel> cellViewModels;
    private BoardModel boardModel;

    public BoardViewModel() {
//        boardModel.cells.addListener((ListChangeListener<? super CellState>) c -> {
//        });
    }

}
