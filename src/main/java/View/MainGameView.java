package View;

import ViewModel.BoardViewModel;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MainGameView {
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

    private final Timer timer= new Timer();
    private long seconds=0;
    private long minutes=0;
    IntegerProperty checkChange= new SimpleIntegerProperty(0);
    IntegerProperty checkFlagChange= new SimpleIntegerProperty(10);

    final ChangeListener changeListenerTimer= new ChangeListener() {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            LabelCounter.setText("Counter:"+minutes+seconds);
        }
    };

        final ChangeListener changeListenerFlags= new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                LabelBombs.setText("Flaggen:"+checkFlagChange.getValue());
            }
        };


    public void startTimer(){
        //Startet Timer bzw Fortsetzen
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkChange.addListener(changeListenerTimer);
                seconds++;
                checkChange.setValue(seconds);
                if(seconds==60){
                    seconds=0;
                    minutes++;
                }
                if(minutes==99&&seconds==60){
                    endTimer();
                }
            }
        },1000,6000);
    }

    public void stopTimer(javafx.event.ActionEvent actionEvent){
        //pausiert Timer
        timer.cancel();
    }

    public void endTimer(){
        //beendet Timer+Zustand
        timer.cancel();
        seconds=0;
        minutes=0;
    }

    public void restartButton(javafx.event.ActionEvent actionEvent){
        // methode für restart Button
        endTimer();

    }

    public void gotoMenu(javafx.event.ActionEvent actionEvent){
        //soll zum Menü springen
        /*HideScene = ((Node) (actionEvent.getSource())).getScene().getWindow();
        */
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
        Window window =   ((Node)(actionEvent.getSource())).getScene().getWindow();
        if (window instanceof Stage){
            ((Stage) window).close();
        }



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
    }




