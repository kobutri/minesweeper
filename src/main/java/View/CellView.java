package View;

import ViewModel.CellViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML
    ImageView imgBomb;

    @FXML
    ImageView imgFlag;

    public void setViewModel(CellViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.typeProperty().addListener((observable, oldValue, newValue) -> {
            setView(newValue);
        });
        setView(viewModel.getType());
        number.textProperty().bind(viewModel.numberProperty());
    }

    private CellViewModel viewModel;

    public void click(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            viewModel.open();
        } else if (event.getButton() == MouseButton.SECONDARY) {
            viewModel.flag();
        }
    }

    private void setView(CellViewModel.CellViewType type) {
        closed.setVisible(type == CellViewModel.CellViewType.FLAGGED || type == CellViewModel.CellViewType.CLOSED);

        flag.setVisible(type == CellViewModel.CellViewType.FLAGGED);

        bomb.setVisible(type == CellViewModel.CellViewType.BOMB);

        number.setVisible(type == CellViewModel.CellViewType.NUMBER);
    }


}
