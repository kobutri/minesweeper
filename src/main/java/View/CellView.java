package View;

import ViewModel.CellViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class CellView {
    @FXML
    Node rect;

    public void setViewModel(CellViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.typeProperty().addListener((observable, oldValue, newValue) -> {
            // change view according to newValue;
        });
    }

    private CellViewModel viewModel;

    public void click(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            viewModel.open();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            viewModel.flag();
        }
    }


}
