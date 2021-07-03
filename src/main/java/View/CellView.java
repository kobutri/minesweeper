package View;

import ViewModel.CellViewModel;
import javafx.scene.Node;

public class CellView extends Node {
    private CellViewModel viewModel;

    public CellView(CellViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
