package View;

import Model.MainGameModel;
import ViewModel.MainGameViewModel;
import com.google.gson.JsonParser;
import com.sun.javafx.scene.paint.GradientUtils;
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

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    FileChooser fileLoader= new FileChooser();
    String pathUser= System.getProperty("user.dir");
    File saveDirec;
    FileChooser fileSaver= new FileChooser();
    String newJson;

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


    public void restartButton() {
        gameViewModel.restart();
    }

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
    public void loadState() {
        saveDirec=new File(pathUser+"/minesweeper");
        saveDirec.mkdirs();
        fileLoader.setTitle("Choose your Game");
        fileLoader.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Json Files", "*.json")
        );
        fileLoader.setInitialDirectory(saveDirec);
        String path = fileLoader.showOpenDialog(new Stage()).getAbsolutePath();
        try {
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(
                    new FileReader(path));
            newJson = data.toJSONString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (newJson !=null) {
            MainGameModel model = MainGameModel.deserialize(newJson);
            try {
                initialize(model);
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        gameViewModel.startBlank();
    }

    public void initialize(MainGameModel gameModel) throws IOException {
        gameViewModel = new MainGameViewModel(gameModel);
        boardController.initialize(gameViewModel.getGameModel().getBoardModel());
        LabelBombs.textProperty().bind(gameViewModel.remainingBombsProperty());
        LabelCounter.textProperty().bind(gameViewModel.timerProperty());
        gameViewModel.start();
    }
}




