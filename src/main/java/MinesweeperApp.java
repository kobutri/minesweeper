import Model.BoardModel;
import View.BoardView;
import ViewModel.BoardViewModel;
import backend.BoardInitializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.hildan.fxgson.FxGson;

public class MinesweeperApp extends Application {
    private Window HideScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        var board = fromJson();
        var fxml = getClass().getResource("board.fxml");
        assert fxml != null;
        FXMLLoader loader = new FXMLLoader(fxml);
        Parent root = loader.load();
        BoardView controller = loader.getController();
        controller.initialize(board);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void StartMainGame(javafx.event.ActionEvent actionEvent) {
        // Starten des eigentlichen Spiels aus der Menu Stage heraus
        HideScene = ((Node) (actionEvent.getSource())).getScene().getWindow();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    BoardModel fromJson() {
        var gson = FxGson.coreBuilder().setPrettyPrinting().create();
        var board = gson.fromJson(json, BoardModel.class);
        board.getCells().forEach(cellModel -> cellModel.setBoardModel(board));
        return board;
    }

    static String json = "{\n" +
            "  \"boardInitializer\": {\n" +
            "    \"width\": 8,\n" +
            "    \"height\": 8,\n" +
            "    \"numBombs\": 5\n" +
            "  },\n" +
            "  \"cells\": [\n" +
            "    {\n" +
            "      \"x\": 0,\n" +
            "      \"y\": 0,\n" +
            "      \"cellState\": \"BLANK\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 1,\n" +
            "      \"y\": 0,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 2,\n" +
            "      \"y\": 0,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 3,\n" +
            "      \"y\": 0,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 4,\n" +
            "      \"y\": 0,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 5,\n" +
            "      \"y\": 0,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 6,\n" +
            "      \"y\": 0,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 7,\n" +
            "      \"y\": 0,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 0,\n" +
            "      \"y\": 1,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 1,\n" +
            "      \"y\": 1,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 2,\n" +
            "      \"y\": 1,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 3,\n" +
            "      \"y\": 1,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 4,\n" +
            "      \"y\": 1,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 5,\n" +
            "      \"y\": 1,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 6,\n" +
            "      \"y\": 1,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 7,\n" +
            "      \"y\": 1,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 0,\n" +
            "      \"y\": 2,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"BOMB\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 1,\n" +
            "      \"y\": 2,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 2,\n" +
            "      \"y\": 2,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 3,\n" +
            "      \"y\": 2,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 4,\n" +
            "      \"y\": 2,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 5,\n" +
            "      \"y\": 2,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 6,\n" +
            "      \"y\": 2,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 3\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 7,\n" +
            "      \"y\": 2,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"BOMB\",\n" +
            "      \"numNeighboringBombs\": 3\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 0,\n" +
            "      \"y\": 3,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 1,\n" +
            "      \"y\": 3,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 2,\n" +
            "      \"y\": 3,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 3,\n" +
            "      \"y\": 3,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 4,\n" +
            "      \"y\": 3,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"BOMB\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 5,\n" +
            "      \"y\": 3,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 6,\n" +
            "      \"y\": 3,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"BOMB\",\n" +
            "      \"numNeighboringBombs\": 3\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 7,\n" +
            "      \"y\": 3,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"BOMB\",\n" +
            "      \"numNeighboringBombs\": 3\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 0,\n" +
            "      \"y\": 4,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 1,\n" +
            "      \"y\": 4,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 2,\n" +
            "      \"y\": 4,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 3,\n" +
            "      \"y\": 4,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 4,\n" +
            "      \"y\": 4,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 5,\n" +
            "      \"y\": 4,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 6,\n" +
            "      \"y\": 4,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 7,\n" +
            "      \"y\": 4,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 2\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 0,\n" +
            "      \"y\": 5,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 1,\n" +
            "      \"y\": 5,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 2,\n" +
            "      \"y\": 5,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 3,\n" +
            "      \"y\": 5,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 4,\n" +
            "      \"y\": 5,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 5,\n" +
            "      \"y\": 5,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 6,\n" +
            "      \"y\": 5,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 7,\n" +
            "      \"y\": 5,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 0,\n" +
            "      \"y\": 6,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 1,\n" +
            "      \"y\": 6,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 2,\n" +
            "      \"y\": 6,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 3,\n" +
            "      \"y\": 6,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 4,\n" +
            "      \"y\": 6,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 5,\n" +
            "      \"y\": 6,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 6,\n" +
            "      \"y\": 6,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 7,\n" +
            "      \"y\": 6,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 0,\n" +
            "      \"y\": 7,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 1,\n" +
            "      \"y\": 7,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 2,\n" +
            "      \"y\": 7,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 3,\n" +
            "      \"y\": 7,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 4,\n" +
            "      \"y\": 7,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 5,\n" +
            "      \"y\": 7,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 6,\n" +
            "      \"y\": 7,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    },\n" +
            "    {\n" +
            "      \"x\": 7,\n" +
            "      \"y\": 7,\n" +
            "      \"cellState\": \"CLOSED\",\n" +
            "      \"type\": \"EMPTY\",\n" +
            "      \"numNeighboringBombs\": 0\n" +
            "    }\n" +
            "  ],\n" +
            "  \"boardGenerated\": true,\n" +
            "  \"boardInitialized\": true\n" +
            "}\n";
}
