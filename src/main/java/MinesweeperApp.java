import View.BoardView;
import View.MainGameView;
import ViewModel.BoardViewModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MinesweeperApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        var fxml = getClass().getResource("board.fxml");
        assert fxml != null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainGameView controller = loader.getController();
        controller.initialize();
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();
    }

}
