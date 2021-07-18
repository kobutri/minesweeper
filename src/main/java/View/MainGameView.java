package View;

import ViewModel.BoardViewModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TimerTask;

public class MainGameView implements Initializable {
    @FXML
    GridPane grid;

    @FXML
    AnchorPane anchorPaneGrid;

    @FXML
    AnchorPane anchorPaneMenu;

    @FXML
    AnchorPane anchorPaneDisplay;

    @FXML
    Button buttonRestart;

    @FXML
    MenuButton dropDownButton;

    @FXML
    MenuItem saveButton;

    @FXML
    MenuItem loadButton;

    @FXML
    MenuItem menuButton;

    @FXML
    MenuItem exitButton;

    @FXML
    Label LabelBombs;

    @FXML
    Label LabelCounter;

    IntegerProperty checkFlagChange= new SimpleIntegerProperty(10);

    final ChangeListener changeListenerFlags= new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                LabelBombs.setText("Flaggen:"+checkFlagChange.getValue());
            }
        };


    public void restartButton(javafx.event.ActionEvent actionEvent) throws IOException {
        initialize();
    }

    public void gotoMenu(javafx.event.ActionEvent actionEvent){
        //soll zum Menü springen

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
                Stage stageMenu= (Stage) anchorPaneGrid.getScene().getWindow();
                stageMenu.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public void saveState (){
        //Serialisieren

    }

    public void loadState(){
        //Deserialisieren

    }

    public void exit(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) buttonRestart.getScene().getWindow();
        stage.setOnCloseRequest(event -> stage.hide());
        // do what you have to do
        stage.close();
        buttonRestart.setOnAction(e -> Platform.exit());


    }


    public void flagCountSet(){
        checkFlagChange.addListener(changeListenerFlags);
        checkFlagChange.subtract(1);
    }
    private BoardViewModel boardViewModel;

    public void initialize() throws IOException {
        boardViewModel = new BoardViewModel();
        var fxml2 = getClass().getClassLoader().getResources("cell.fxml").nextElement();
        boardViewModel.cellViewModels.forEach((integerIntegerPair, cellViewModel) -> {
            try {
                FXMLLoader loader = new FXMLLoader(fxml2);
                Node cell = loader.load();
                CellView controller = loader.getController();
                controller.setViewModel(cellViewModel);
                grid.add(cell, integerIntegerPair.getKey(), integerIntegerPair.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    int seconds=0;
    int minutes=0;
    String secondsString= String.format("%02d",seconds);
    String minutesString= String.format("%02d",minutes);
    Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LabelCounter.setText("Zeit: "+minutesString+":"+secondsString);
        timeline= new Timeline(new KeyFrame(Duration.seconds(1),e->{
            seconds++;
            if(seconds==60){
                minutes++;
                seconds=0;
            }
            secondsString= String.format("%02d",seconds);
            minutesString= String.format("%02d",minutes);
            LabelCounter.setText("Zeit: "+minutesString+":"+secondsString);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    public void timerReset(){
        timeline.stop();
        seconds=0;
        minutes=0;
        String secondsString= String.format("%02d",seconds);
        String minutesString= String.format("%02d",minutes);
        LabelCounter.setText("Zeit: "+minutesString+":"+secondsString);
        timeline.play();
    }
    public void timerPause(javafx.event.ActionEvent actionEvent){
        timeline.stop();
    }

    public void timerResume(javafx.event.ActionEvent actionEvent){
        timeline.play();
    }
}




