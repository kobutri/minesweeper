package ViewModel;

import Model.CellModel;
import backend.CellType;
import backend.WinState;
import javafx.beans.property.*;

public class CellViewModel {
    private StringProperty number;
    private SimpleObjectProperty<CellViewType> type;
    private CellModel model;

    public CellViewModel(CellModel model) {
        this.model = model;
        type = new SimpleObjectProperty<>(CellViewType.CLOSED);
        number = new SimpleStringProperty(String.valueOf(model.getNumNeighboringBombs()));
        model.getOpen().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                if (model.getType() == CellType.BOMB) {
                    // react to loose
                    type.setValue(CellViewType.BOMB);
                }
                else if (model.getNumNeighboringBombs() == 0) {
                    type.setValue(CellViewType.BLANK);
                } else {
                    type.setValue(CellViewType.NUMBER);
                }
            }
        });
    }

    public CellViewType getType() {
        return type.getValue();
    }

    public Property<CellViewType> typeProperty() {
        return type;
    }

    public String getNumber() {
        return number.get();
    }

    public StringProperty numberProperty() {
        return number;
    }

    public WinState open() {
        model.open();
        return model.getBoardModel().hasWon();
    }

    public void flag() {
        if (!model.getOpen().get()){
            if(type.get() == CellViewType.FLAGGED) {
                type.setValue(CellViewType.CLOSED);
            } else {
                type.setValue(CellViewType.FLAGGED);
            }
        }
    }

    public enum CellViewType {
        BLANK,
        NUMBER,
        CLOSED,
        FLAGGED,
        BOMB,
    }
}