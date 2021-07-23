import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Transform;

public class InteractiveViewer extends Pane {
    InteractiveViewer(Node child) {
        pane.getChildren().add(child);
        getChildren().add(pane);
        pane.setMouseTransparent(true);
        pane.getTransforms().add(Transform.translate(0, 0));
        layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            Rectangle rectangle = new Rectangle(newValue.getWidth(), newValue.getHeight());
            setClip(rectangle);
        });

        setOnZoom(event -> {
            int size = pane.getTransforms().size();
            Transform oldTransform = pane.getTransforms().get(size-1);
            var pivot = pane.parentToLocal(event.getX(), event.getY());
            var scale = event.getZoomFactor();
            Transform transform = oldTransform.createConcatenation(Transform.scale(scale, scale, pivot.getX(), pivot.getY()));
            pane.getTransforms().set(size-1, transform);
        });
        setOnScroll(event -> {
            int size = pane.getTransforms().size();
            Transform oldTransform = pane.getTransforms().get(size-1);
            var pivot = pane.parentToLocal(event.getX(), event.getY());
            var scale = 1.0 + event.getDeltaY()/(event.getMultiplierY()*10);
            Transform transform = oldTransform.createConcatenation(Transform.scale(scale, scale, pivot.getX(), pivot.getY()));
            pane.getTransforms().set(size-1, transform);

        });
        setOnMousePressed(event -> {
            if(event.getButton() == MouseButton.SECONDARY) {
                mousePosition = new Point2D(event.getX(), event.getY());
            }
        });
        setOnMouseDragged(event -> {
            if(event.isSecondaryButtonDown()) {
                    Point2D newMousePosition = new Point2D(event.getX(), event.getY());
                    Point2D mouseDelta = newMousePosition.subtract(mousePosition);
                    mousePosition = newMousePosition;
                    int size = pane.getTransforms().size();
                    Transform transform = pane.getTransforms().get(size-1);
                    transform = Transform.translate(mouseDelta.getX(), mouseDelta.getY()).createConcatenation(transform);
                    pane.getTransforms().set(size-1, transform);
            }
        });
    }
    private final Pane pane = new Pane();
    private Point2D mousePosition = null;
 

}
