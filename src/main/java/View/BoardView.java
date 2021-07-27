package View;

import Model.BoardModel;
import ViewModel.BoardViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Transform;

import java.io.IOException;

public class BoardView {

    private final double minZoom = 1.0 / 10.0;
    private final double maxZoom = 10.0;
    @FXML
    GridPane grid;
    @FXML
    AnchorPane pane;
    private BoardViewModel boardViewModel;
    private Point2D mousePosition = null;

    public void initialize(BoardModel boardModel) throws IOException {
        boardViewModel = new BoardViewModel(boardModel);
        boardViewModel.cellsChangedProperty().addListener((observable, oldValue, newValue) -> {
            try {
                initialize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        initialize();


        grid.getTransforms().add(Transform.translate(0, 0));
        pane.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            Rectangle rectangle = new Rectangle(newValue.getWidth(), newValue.getHeight());
            pane.setClip(rectangle);
        });

        pane.setOnZoom(event -> {
            int size = grid.getTransforms().size();
            Transform oldTransform = grid.getTransforms().get(size - 1);
            var pivot = grid.parentToLocal(event.getX(), event.getY());
            var scale = event.getZoomFactor();
            Transform transform = oldTransform.createConcatenation(Transform.scale(scale, scale, pivot.getX(), pivot.getY()));
            var zoomFactor = transform.transform(Point2D.ZERO).distance(transform.transform(new Point2D(1, 0)));
            if (zoomFactor > minZoom && zoomFactor < maxZoom) {
                grid.getTransforms().set(size - 1, transform);
            }
        });
        pane.setOnScroll(event -> {
            int size = grid.getTransforms().size();
            Transform oldTransform = grid.getTransforms().get(size - 1);
            var pivot = grid.parentToLocal(event.getX(), event.getY());
            var scale = 1.0 + event.getDeltaY() / (event.getMultiplierY() * 10);
            Transform transform = oldTransform.createConcatenation(Transform.scale(scale, scale, pivot.getX(), pivot.getY()));
            var zoomFactor = transform.transform(Point2D.ZERO).distance(transform.transform(new Point2D(1, 0)));
            if (zoomFactor > minZoom && zoomFactor < maxZoom) {
                grid.getTransforms().set(size - 1, transform);
            }
        });
        pane.setOnMousePressed(event -> mousePosition = new Point2D(event.getX(), event.getY()));
        pane.setOnMouseDragged(event -> {
            Point2D newMousePosition = new Point2D(event.getX(), event.getY());
            Point2D mouseDelta = newMousePosition.subtract(mousePosition);
            mousePosition = newMousePosition;
            int size = grid.getTransforms().size();
            Transform transform = grid.getTransforms().get(size - 1);
            transform = Transform.translate(mouseDelta.getX(), mouseDelta.getY()).createConcatenation(transform);
            grid.getTransforms().set(size - 1, transform);
        });
    }

    private void initialize() throws IOException {
        grid.getTransforms().clear();
        grid.getTransforms().add(Transform.translate(0, 0));
        var fxml = getClass().getClassLoader().getResources("cell.fxml").nextElement();
        grid.getChildren().clear();
        boardViewModel.getCellViewModels().forEach((integerIntegerPair, cellViewModel) -> {
            try {
                FXMLLoader loader = new FXMLLoader(fxml);
                Node cell = loader.load();
                CellView controller = loader.getController();
                controller.setViewModel(cellViewModel);
                grid.add(cell, integerIntegerPair.getKey(), integerIntegerPair.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
