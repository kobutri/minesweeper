package ViewModel;

import Model.MenuModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.io.IOException;

public class MenuViewModel {
    private final MenuModel menuModel;
    IntegerProperty width = new SimpleIntegerProperty(0);
    IntegerProperty height = new SimpleIntegerProperty(0);
    IntegerProperty numBombs = new SimpleIntegerProperty(0);
    IntegerProperty minNumBombs = new SimpleIntegerProperty(0);
    IntegerProperty maxNumBombs = new SimpleIntegerProperty(0);
    IntegerProperty minWidth = new SimpleIntegerProperty(0);
    IntegerProperty minHeight = new SimpleIntegerProperty(0);
    IntegerProperty maxWidth = new SimpleIntegerProperty(0);
    IntegerProperty maxHeight = new SimpleIntegerProperty(0);
    public MenuViewModel(MenuModel menuModel) {
        this.menuModel = menuModel;
        minWidth.setValue(menuModel.getMinWidth());
        minHeight.setValue(menuModel.getMinHeight());
        maxWidth.set(menuModel.getMaxWidth());
        maxHeight.set(menuModel.getMaxHeight());
        width.set(menuModel.getBoardInitializer().getWidth());
        height.set(menuModel.getBoardInitializer().getHeight());
        minNumBombs.set(menuModel.getMinBombs());
        maxNumBombs.set(menuModel.getMaxBombs());
        numBombs.set(menuModel.getBoardInitializer().getNumBombs());

        width.addListener((observable, oldValue, newValue) -> menuModel.getBoardInitializer().setWidth((Integer) newValue));
        height.addListener((observable, oldValue, newValue) -> menuModel.getBoardInitializer().setHeight((Integer) newValue));
        numBombs.addListener((observable, oldValue, newValue) -> menuModel.getBoardInitializer().setNumBombs((Integer) newValue));
    }

    public int getMinNumBombs() {
        return minNumBombs.get();
    }

    public IntegerProperty minNumBombsProperty() {
        return minNumBombs;
    }

    public int getMaxNumBombs() {
        return maxNumBombs.get();
    }

    public IntegerProperty maxNumBombsProperty() {
        return maxNumBombs;
    }

    public int getWidth() {
        return width.get();
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    public int getHeight() {
        return height.get();
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    public int getNumBombs() {
        return numBombs.get();
    }

    public IntegerProperty numBombsProperty() {
        return numBombs;
    }

    public int getMinWidth() {
        return minWidth.get();
    }

    public IntegerProperty minWidthProperty() {
        return minWidth;
    }

    public int getMinHeight() {
        return minHeight.get();
    }

    public IntegerProperty minHeightProperty() {
        return minHeight;
    }

    public int getMaxWidth() {
        return maxWidth.get();
    }

    public IntegerProperty maxWidthProperty() {
        return maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight.get();
    }

    public IntegerProperty maxHeightProperty() {
        return maxHeight;
    }

}
