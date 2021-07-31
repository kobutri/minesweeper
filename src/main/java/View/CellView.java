package View;

import ViewModel.CellViewModel;
import backend.WinState;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class CellView {
    //CellView mit cell.fxml verknüpfen
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

    /**
     * Binden von ViewModel und Zellenzuständen bezüglich der Sichtbarkeit
     * @param viewModel
     */
    public void setViewModel(CellViewModel viewModel) {
        this.viewModel = viewModel;
        this.bomb.visibleProperty().bind(viewModel.isBombProperty());
        this.flag.visibleProperty().bind(viewModel.isFlaggedProperty());
        this.closed.visibleProperty().bind(viewModel.isOpenProperty().not());
        this.number.visibleProperty().bind(viewModel.showNumberProperty());
        number.textProperty().bind(viewModel.numberProperty());
    }

    private CellViewModel viewModel;

    /**
     * Alle möglichen Mausklicks verarbeiten: Linksklick für Flagge oder auf Flagge, Rechtsklick auf Bombe oder keine Bombe
     * @param event
     * @throws IOException
     */
    public void click(MouseEvent event) throws IOException {
        if (event.isStillSincePress()) {
            if (event.getButton() == MouseButton.PRIMARY) {
                var state = viewModel.open();
                if (state == WinState.WIN) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "You Won");
                    alert.setTitle("Victory");
                    alert.setHeaderText("Good Job!");
                    Image image= new Image(String.valueOf(getClass().getClassLoader().getResources("trophy-icon.png").nextElement()));
                    alert.setGraphic(new ImageView(image));
                    alert.showAndWait();
                } else if (state == WinState.LOOSE) {
                    viewModel.openAll();
                    Alert alert = new Alert(Alert.AlertType.ERROR, "You Lost");
                    alert.setTitle("Defeat");
                    alert.setHeaderText("Unlucky You!");
                    Image image= new Image(String.valueOf(getClass().getClassLoader().getResources("after-boom-icon.png").nextElement()));
                    alert.setGraphic(new ImageView(image));
                    alert.showAndWait();
                }
            } else if (event.getButton() == MouseButton.SECONDARY) {
                viewModel.flag();
            }
        }
    }


}
