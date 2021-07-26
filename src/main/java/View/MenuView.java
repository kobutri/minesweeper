package View;

import Model.MenuModel;
import ViewModel.MenuViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.Text;

public class MenuView {

    @FXML
    Button ButtonStart;
    private Window HideScene;
    @FXML
    private Text LabelBombs;
    @FXML
    private Text LabelCounter;
    @FXML
    private Pane MainGamePane;
    @FXML
    private Slider SliderX;
    @FXML
    private Slider SliderY;
    @FXML
    private Slider SliderBombs;
    private Stage stage;

    public void initialize(MenuModel menuModel, Stage stage) {
        MenuViewModel menuViewModel = new MenuViewModel(menuModel);
        SliderX.minProperty().bind(menuViewModel.minWidthProperty());
        SliderX.maxProperty().bind(menuViewModel.maxWidthProperty());
        SliderX.valueProperty().bindBidirectional(menuViewModel.widthProperty());

        SliderY.minProperty().bind(menuViewModel.minHeightProperty());
        SliderY.maxProperty().bind(menuViewModel.maxHeightProperty());
        SliderY.valueProperty().bindBidirectional(menuViewModel.heightProperty());

        SliderBombs.minProperty().bind(menuViewModel.minNumBombsProperty());
        SliderBombs.maxProperty().bind(menuViewModel.maxNumBombsProperty());
        SliderBombs.valueProperty().bindBidirectional(menuViewModel.numBombsProperty());

        this.stage = stage;
    }

    public void StartMainGame(javafx.event.ActionEvent actionEvent) {
        stage.close();
    }
}