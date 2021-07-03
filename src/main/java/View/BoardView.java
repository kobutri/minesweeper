package View;

import ViewModel.BoardViewModel;
import ViewModel.CellViewModel;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class BoardView {
    @FXML
    GridPane grid;

    private BoardViewModel boardViewModel;

    void initialize() {
        boardViewModel.cellViewModels.forEach((integerIntegerPair, cellViewModel) -> {
            grid.add(new CellView(cellViewModel), integerIntegerPair.getKey(), integerIntegerPair.getValue());
            // somehow
        });

    }
}
