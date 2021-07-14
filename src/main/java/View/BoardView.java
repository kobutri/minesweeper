package View;

import ViewModel.BoardViewModel;
import ViewModel.CellViewModel;
import backend.Board;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import java.io.IOException;

public class BoardView {
    @FXML
    GridPane grid;

    private BoardViewModel boardViewModel;

    public void initialize() throws IOException {
        boardViewModel = new BoardViewModel();
        var fxml2 = getClass().getClassLoader().getResources("cell.fxml").nextElement();
        boardViewModel.cellViewModels.forEach((integerIntegerPair, cellViewModel) -> {
            try {
                FXMLLoader loader = new FXMLLoader(fxml2);
                Node cell = loader.load();
                CellView controller = loader.getController();
                controller.setViewModel(cellViewModel);
                grid.add(cell, integerIntegerPair.getKey(), integerIntegerPair.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
