import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main  extends Application {

    Window HideScene;

    public static void main(String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        // Menu Stage start
        var fxml = getClass().getResource("menu.fxml");
        assert fxml != null;
        Parent root = FXMLLoader.load(fxml);
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();


    }


    public void StartMainGame(javafx.event.ActionEvent actionEvent) {
        // Starten des eigentlichen Spiels aus der Menu Stage heraus
        HideScene =  ((Node)(actionEvent.getSource())).getScene().getWindow();
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }


    }



}