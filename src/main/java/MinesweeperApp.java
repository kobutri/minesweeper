import View.BoardView;
import ViewModel.BoardViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MinesweeperApp extends Application {
    private Window HideScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        var fxml = getClass().getResource("board.fxml");
        assert fxml != null;
        FXMLLoader loader = new FXMLLoader(fxml);
        Parent root = loader.load();
        BoardView controller = loader.getController();
        controller.initialize();
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }

    public void StartMainGame(javafx.event.ActionEvent actionEvent) {
        // Starten des eigentlichen Spiels aus der Menu Stage heraus
        HideScene = ((Node) (actionEvent.getSource())).getScene().getWindow();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
