package View;

import Model.MainGameModel;
import ViewModel.MainGameViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MainGameView {
    // MainGameView mit main.fxml
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
    FileChooser fileLoader= new FileChooser();
    String pathUser= System.getProperty("user.dir");
    File saveDirec;
    FileChooser fileSaver= new FileChooser();

    /**
     * Alle möglichen Mausklicks verarbeiten: Linksklick für Flagge oder auf Flagge, Rechtsklick auf Bombe oder keine Bombe
     * @param event
     * @throws IOException
     */
    public void chooseDifficulty(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResources("menu.fxml").nextElement());
        Parent root = loader.load();
        MenuView menuView = loader.getController();
        Stage menuStage = new Stage();
        menuView.initialize(gameViewModel.getGameModel().getMenuModel(), menuStage);
        Scene scene = new Scene(root);
        menuStage.setScene(scene);
        menuStage.showAndWait();
        gameViewModel.startBlank();
    }


    /**
     * Setzt das Spiel und den Timer zurück
     */
    public void restartButton() {

        gameViewModel.restart();
    }

    /**
     * Öffnet den FileChooser als FileSaver und erstellt Json Dateien mit Inhalt var json
     */
    public void saveState (){
        saveDirec=new File(pathUser+"/minesweeper");
        saveDirec.mkdirs();
        fileSaver.setTitle("Save Game");
        fileSaver.setInitialDirectory(saveDirec);
        fileSaver.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json Files", "*.json")
        );
        var json = gameViewModel.getGameModel().serialize();
        File file=fileSaver.showSaveDialog(new Stage());
        if(json !=null){
            try {
                PrintWriter writer;
                writer = new PrintWriter(file);
                writer.println(json);
                writer.close();
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * Öffnet den Filechooser und liest die ausgewählte Datei mit einem JSONParser ein
     */
    public void loadState() {
        saveDirec=new File(pathUser+"/minesweeper");
        saveDirec.mkdirs();
        fileLoader.setTitle("Choose your Game");
        fileLoader.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json Files", "*.json")
        );
        fileLoader.setInitialDirectory(saveDirec);
        try {
            String json = Files.readString(Path.of(fileLoader.showOpenDialog(new Stage()).getAbsolutePath()));
            MainGameModel model = MainGameModel.deserialize(json);
            initialize(model);
        } catch (IOException ignored)  {
        }
    }

    /**
     * Schließt alle Fenster -> beendet das Spiel
     * @param actionEvent
     */
    public void exit(javafx.event.ActionEvent actionEvent){
        Stage stage = (Stage) buttonRestart.getScene().getWindow();
        stage.setOnCloseRequest(event -> stage.hide());
        stage.close();
        buttonRestart.setOnAction(e -> Platform.exit());

    }

    /**
     * Feld erstellen ohne GameModel
     * @throws IOException
     */
    public void initialize() throws IOException {
        gameViewModel = new MainGameViewModel();
        boardController.initialize(gameViewModel.getGameModel().getBoardModel());
        LabelBombs.textProperty().bind(gameViewModel.remainingBombsProperty());
        LabelCounter.textProperty().bind(gameViewModel.timerProperty());
        gameViewModel.startBlank();
    }

    /**
     * Feld erstellen mit GameModel
     * @param gameModel
     * @throws IOException
     */
    public void initialize(MainGameModel gameModel) throws IOException {
        gameViewModel = new MainGameViewModel(gameModel);
        boardController.initialize(gameViewModel.getGameModel().getBoardModel());
        LabelBombs.textProperty().bind(gameViewModel.remainingBombsProperty());
        LabelCounter.textProperty().bind(gameViewModel.timerProperty());
        gameViewModel.start();
    }
}




