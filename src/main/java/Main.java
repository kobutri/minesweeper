import Model.BoardModel;
import backend.BoardInitializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import org.hildan.fxgson.FxGson;

public class Main {

    public static void main(String[] args) {
        Application.launch(MinesweeperApp.class);
//        var board = new BoardModel();
//        board.initializeBoard(new BoardInitializer(8, 8, 5));
//        board.generateBoard(0, 0);
    }
}
