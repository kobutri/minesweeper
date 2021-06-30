package ViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

public class MainGameViewModel {
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





    public MainGameViewModel(){
    }
    private void Generate()
    {
        MainGamePane.setScaleX(SliderX.getValue());
        MainGamePane.setScaleY(SliderY.getValue());
        LabelBombs.setTextContent("" + SliderBombs.getValue());
    }




}
