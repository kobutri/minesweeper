package View;

import ViewModel.CellViewModel;
import backend.WinState;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class CellView {
    @FXML
    Node closed;

    @FXML
    Node open;

    @FXML
    Node flag;

    @FXML
    Node bomb;

    @FXML
    Label number;

    public void setViewModel(CellViewModel viewModel) {
        this.viewModel = viewModel;
        this.bomb.visibleProperty().bind(viewModel.isBombProperty());
        this.flag.visibleProperty().bind(viewModel.isFlaggedProperty());
        this.closed.visibleProperty().bind(viewModel.isOpenProperty().not());
        this.number.visibleProperty().bind(viewModel.showNumberProperty());
        number.textProperty().bind(viewModel.numberProperty());
    }

    private CellViewModel viewModel;

    public void click(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            var state = viewModel.open();
            if (state == WinState.WIN) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You Won");
                alert.showAndWait();
            } else if (state == WinState.LOOSE) {
                viewModel.openAll();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You Lost");
                alert.showAndWait();
            }
        } else if (event.getButton() == MouseButton.SECONDARY) {
            viewModel.flag();
        }
    }


}
