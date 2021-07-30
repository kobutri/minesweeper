import View.MainGameView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MinesweeperApp extends Application {
    //Lädt Mainspiel am Anfang beim Starten



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainGameView controller = loader.getController();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
