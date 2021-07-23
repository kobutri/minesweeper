package View;

import ViewModel.BoardViewModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
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
import java.net.URL;
import java.util.ResourceBundle;

public class MainGameView implements Initializable {
    // FXML verknüpfungen
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    IntegerProperty checkFlagChange= new SimpleIntegerProperty(10);
    //flaggenzahl

    final ChangeListener changeListenerFlags= new ChangeListener() {
        //reagiert auf änderungen an der flaggenzahl
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                LabelBombs.setText("Flags:"+checkFlagChange.getValue());
            }
        };

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResources("menu.fxml").nextElement());
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void restartButton(javafx.event.ActionEvent actionEvent) throws IOException {
        initialize();
        timerReset();

    }

    public void gotoMenu(javafx.event.ActionEvent actionEvent) throws IOException {
        //soll zum Menü springe

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();



        }

    public void saveState (){
        //Serialisieren

    }

    public void loadState(){
        //Deserialisieren

    }

    public void exit(javafx.event.ActionEvent actionEvent){
        //beendet fenster+ programm endet nach kurzer zeit selber
        Stage stage = (Stage) buttonRestart.getScene().getWindow();
        stage.setOnCloseRequest(event -> stage.hide());
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
    //teile Timeline
    int seconds=0;
    int minutes=0;
    String secondsString= String.format("%02d",seconds);
    String minutesString= String.format("%02d",minutes);
    Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //beginnt timeline direkt zu Beginn
        LabelCounter.setText("Time: "+minutesString+":"+secondsString);
        timeline= new Timeline(new KeyFrame(Duration.seconds(1),e->{
            seconds++;
            if(seconds==60){
                minutes++;
                seconds=0;
            }
            secondsString= String.format("%02d",seconds);
            minutesString= String.format("%02d",minutes);
            LabelCounter.setText("Time: "+minutesString+":"+secondsString);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }
    public void timerReset(){
        //pausiert timeline um werte zu resetten
        timeline.stop();
        seconds=0;
        minutes=0;
        String secondsString= String.format("%02d",seconds);
        String minutesString= String.format("%02d",minutes);
        LabelCounter.setText("Time: "+minutesString+":"+secondsString);
        timeline.play();
    }
    public void timerPause(javafx.event.ActionEvent actionEvent){
        //nur pausieren noch keine anwendung
        timeline.stop();
    }

    public void timerResume(javafx.event.ActionEvent actionEvent){
        //nur fortsetzen gegengtück zu timerpause
        timeline.play();
    }
}




