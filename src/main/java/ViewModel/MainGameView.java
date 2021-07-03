package ViewModel;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

public class MainGameView {
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

    public StringProperty timer;



    public MainGameView(){
    }
    private void Generate()
    {
        // Werteübergabe aus Slidern um Spiel zu erstellen
        MainGamePane.setScaleX(SliderX.getValue());
        MainGamePane.setScaleY(SliderY.getValue());
        LabelBombs.setTextContent("" + SliderBombs.getValue());
    }




}
