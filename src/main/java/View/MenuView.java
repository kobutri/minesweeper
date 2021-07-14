package View;

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





    public MenuView(){
    }
    private void Generate()
    {
        // Werteübergabe aus Slidern um Spiel zu erstellen
        MainGamePane.setScaleX(SliderX.getValue());
        MainGamePane.setScaleY(SliderY.getValue());
        LabelBombs.setTextContent("" + SliderBombs.getValue());
    }

    public void StartMainGame(javafx.event.ActionEvent actionEvent) {
        // Starten des eigentlichen Spiels aus der Menu Stage heraus
        /*HideScene = ((Node) (actionEvent.getSource())).getScene().getWindow();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();*/
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            Stage stageMenu= (Stage) SliderY.getScene().getWindow();
            stageMenu.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}