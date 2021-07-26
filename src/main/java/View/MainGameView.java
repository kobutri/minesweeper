package View;

import Model.MainGameModel;
import ViewModel.BoardViewModel;
import ViewModel.MainGameViewModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainGameView {
    // FXML verknüpfungen
    @FXML
    Node board;
    @FXML
    BoardView boardController;

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

    private MainGameViewModel gameViewModel;

    public void chooseDifficulty(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResources("menu.fxml").nextElement());
        Parent root = loader.load();
        MenuView menuView = loader.getController();
        Stage menuStage = new Stage();
        menuView.initialize(gameViewModel.getGameModel().getMenuModel(), menuStage);
        Scene scene = new Scene(root);
        menuStage.setScene(scene);
        menuStage.showAndWait();
        gameViewModel.start();
    }


    public void restartButton() {
        gameViewModel.start();

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
        stage.close();
        buttonRestart.setOnAction(e -> Platform.exit());
        
    }

    public void initialize(Stage stage) throws IOException {
        gameViewModel = new MainGameViewModel();
        boardController.initialize(gameViewModel.getGameModel().getBoardModel());
        LabelBombs.textProperty().bind(gameViewModel.remainingBombsProperty());
        LabelCounter.textProperty().bind(gameViewModel.timerProperty());
        gameViewModel.start();
    }
}




