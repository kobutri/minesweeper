package View;

import Model.MainGameModel;
import ViewModel.BoardViewModel;
import ViewModel.MainGameViewModel;
import com.google.gson.*;
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
import java.lang.reflect.Type;
import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hildan.fxgson.FxGson;

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
        gameViewModel.restart();
    }

    public void saveState (){
        var json = gameViewModel.getGameModel().serialize();
    }

    public void loadState(){
        // todo pick file
        MainGameModel model = MainGameModel.deserialize("");
        try {
            initialize(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exit(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) buttonRestart.getScene().getWindow();
        stage.setOnCloseRequest(event -> stage.hide());
        stage.close();
        buttonRestart.setOnAction(e -> Platform.exit());
        
    }

    public void initialize() throws IOException {
        gameViewModel = new MainGameViewModel();
        boardController.initialize(gameViewModel.getGameModel().getBoardModel());
        LabelBombs.textProperty().bind(gameViewModel.remainingBombsProperty());
        LabelCounter.textProperty().bind(gameViewModel.timerProperty());
        gameViewModel.start();
    }

    public void initialize(MainGameModel gameModel) throws IOException {
        gameViewModel = new MainGameViewModel(gameModel);
        boardController.initialize(gameViewModel.getGameModel().getBoardModel());
        LabelBombs.textProperty().bind(gameViewModel.remainingBombsProperty());
        LabelCounter.textProperty().bind(gameViewModel.timerProperty());
        gameViewModel.start();
    }

    String json = "{\n" +
            "  \"menuModel\": {\n" +
            "    \"boardInitializer\": {\n" +
            "      \"width\": 8,\n" +
            "      \"height\": 8,\n" +
            "      \"numBombs\": 10\n" +
            "    },\n" +
            "    \"maxWidth\": 10,\n" +
            "    \"maxHeight\": 10,\n" +
            "    \"minWidth\": 5,\n" +
            "    \"minHeight\": 5,\n" +
            "    \"minBombs\": 5,\n" +
            "    \"maxBombs\": 10\n" +
            "  },\n" +
            "  \"boardModel\": {\n" +
            "    \"cells\": [\n" +
            "      {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 0,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 1,\n" +
            "        \"y\": 0,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 2,\n" +
            "        \"y\": 0,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 3,\n" +
            "        \"y\": 0,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 4,\n" +
            "        \"y\": 0,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 5,\n" +
            "        \"y\": 0,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 6,\n" +
            "        \"y\": 0,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 7,\n" +
            "        \"y\": 0,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 1,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 1,\n" +
            "        \"y\": 1,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 2,\n" +
            "        \"y\": 1,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 3,\n" +
            "        \"y\": 1,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 4,\n" +
            "        \"y\": 1,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 5,\n" +
            "        \"y\": 1,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 6,\n" +
            "        \"y\": 1,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 7,\n" +
            "        \"y\": 1,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 2,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 1,\n" +
            "        \"y\": 2,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 2,\n" +
            "        \"y\": 2,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 3,\n" +
            "        \"y\": 2,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 4,\n" +
            "        \"y\": 2,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 5,\n" +
            "        \"y\": 2,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 6,\n" +
            "        \"y\": 2,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 7,\n" +
            "        \"y\": 2,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 3,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 1,\n" +
            "        \"y\": 3,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 2,\n" +
            "        \"y\": 3,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 3,\n" +
            "        \"y\": 3,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 4,\n" +
            "        \"y\": 3,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 5,\n" +
            "        \"y\": 3,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 6,\n" +
            "        \"y\": 3,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 7,\n" +
            "        \"y\": 3,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 4,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 1,\n" +
            "        \"y\": 4,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 2,\n" +
            "        \"y\": 4,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 3,\n" +
            "        \"y\": 4,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 4,\n" +
            "        \"y\": 4,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 5,\n" +
            "        \"y\": 4,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 6,\n" +
            "        \"y\": 4,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 7,\n" +
            "        \"y\": 4,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 5,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 1,\n" +
            "        \"y\": 5,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 2,\n" +
            "        \"y\": 5,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 3,\n" +
            "        \"y\": 5,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 4,\n" +
            "        \"y\": 5,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 5,\n" +
            "        \"y\": 5,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 6,\n" +
            "        \"y\": 5,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 7,\n" +
            "        \"y\": 5,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 6,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 1,\n" +
            "        \"y\": 6,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 2,\n" +
            "        \"y\": 6,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 3,\n" +
            "        \"y\": 6,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 4,\n" +
            "        \"y\": 6,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 5,\n" +
            "        \"y\": 6,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 6,\n" +
            "        \"y\": 6,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 7,\n" +
            "        \"y\": 6,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 0,\n" +
            "        \"y\": 7,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 1,\n" +
            "        \"y\": 7,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 2,\n" +
            "        \"y\": 7,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 3,\n" +
            "        \"y\": 7,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 4,\n" +
            "        \"y\": 7,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 5,\n" +
            "        \"y\": 7,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 6,\n" +
            "        \"y\": 7,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      },\n" +
            "      {\n" +
            "        \"x\": 7,\n" +
            "        \"y\": 7,\n" +
            "        \"cellState\": \"CLOSED\",\n" +
            "        \"type\": \"EMPTY\",\n" +
            "        \"numNeighboringBombs\": 0\n" +
            "      }\n" +
            "    ],\n" +
            "    \"cellsChanged\": 1,\n" +
            "    \"flagCount\": 0,\n" +
            "    \"boardInitializer\": {\n" +
            "      \"width\": 8,\n" +
            "      \"height\": 8,\n" +
            "      \"numBombs\": 10\n" +
            "    },\n" +
            "    \"boardGenerated\": false,\n" +
            "    \"boardInitialized\": true\n" +
            "  },\n" +
            "  \"timeline\": {\n" +
            "    \"time\": 10000.0\n" +
            "  }\n" +
            "}";
}




