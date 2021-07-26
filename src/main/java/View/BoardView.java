package View;

import Model.BoardModel;
import ViewModel.BoardViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class BoardView {

    @FXML GridPane grid;

    private BoardViewModel boardViewModel;

    public void initialize(BoardModel boardModel) throws IOException {
        boardViewModel = new BoardViewModel(boardModel);
        boardViewModel.cellsChangedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        initialize();
    }

    private void initialize() throws IOException {
        var fxml = getClass().getClassLoader().getResources("cell.fxml").nextElement();
        grid.getChildren().clear();
        boardViewModel.getCellViewModels().forEach((integerIntegerPair, cellViewModel) -> {
            try {
                FXMLLoader loader = new FXMLLoader(fxml);
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
