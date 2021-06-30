import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
public class Main  extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(300);
        rectangle.setHeight(400);
        rectangle.setStroke(Color.TRANSPARENT);
        rectangle.setFill(Color.valueOf("#00ffff"));

        InteractiveViewer viewer = new InteractiveViewer(rectangle);

        primaryStage.setScene(new Scene(viewer, 800, 800));

        primaryStage.show();
    }
}
