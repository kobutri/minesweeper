package ViewModel;

import Model.BoardModel;
import backend.BoardInitializer;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.util.*;

public class BoardViewModel {
    public Map<Pair<Integer, Integer>, CellViewModel> cellViewModels = new HashMap<>();
    private BoardModel boardModel;

    public BoardViewModel() {
        boardModel = new BoardModel(new BoardInitializer(8, 8, 5, 0, 0));
        boardModel.getCells().forEach(cellModel -> {
            cellViewModels.put(new Pair<>(cellModel.getX(), cellModel.getY()), new CellViewModel(cellModel));
        });
    }

}
