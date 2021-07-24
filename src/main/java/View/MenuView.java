package View;

import Model.MenuModel;
import ViewModel.MenuViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.w3c.dom.Text;

public class MenuView {

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

    @FXML
    Button ButtonStart;

    private MenuViewModel menuViewModel;
    private Stage stage;


    private void Generate()
    {
        // Werteübergabe aus Slidern um Spiel zu erstellen
        MainGamePane.setScaleX(SliderX.getValue());
        MainGamePane.setScaleY(SliderY.getValue());
        LabelBombs.setTextContent("" + SliderBombs.getValue());
    }

    public void initialize(MenuModel menuModel, Stage stage) {
        menuViewModel = new MenuViewModel(menuModel);
        SliderX.minProperty().bind(menuViewModel.minWidthProperty());
        SliderX.maxProperty().bind(menuViewModel.maxWidthProperty());
        SliderX.valueProperty().bind(menuViewModel.widthProperty());

        SliderY.minProperty().bind(menuViewModel.minHeightProperty());
        SliderY.maxProperty().bind(menuViewModel.minHeightProperty());
        SliderY.valueProperty().bind(menuViewModel.heightProperty());

        SliderBombs.minProperty().bind(menuViewModel.minNumBombsProperty());
        SliderBombs.maxProperty().bind(menuViewModel.maxNumBombsProperty());
        SliderBombs.valueProperty().bind(menuViewModel.numBombsProperty());

        this.stage = stage;
    }

    public void StartMainGame(javafx.event.ActionEvent actionEvent) {
       menuViewModel.start();
       stage.close();
    }




}