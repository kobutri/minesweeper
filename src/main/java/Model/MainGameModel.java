package Model;

import com.google.gson.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import org.hildan.fxgson.FxGson;
import javafx.util.Duration;
import java.lang.reflect.Type;

public class MainGameModel {
    private MenuModel menuModel = new MenuModel();
    private BoardModel boardModel = new BoardModel();
    private Timeline timeline;



    public MainGameModel() {
        //Konstruktor von MainGameModel
        timeline = new Timeline(new KeyFrame(Duration.INDEFINITE));
        timeline.setCycleCount(1);
    }

    //Getter
    public Timeline getTimeline() {
        return timeline;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void startBlank(){
        //Leeres Feld starten und timer starten
        timeline.play();
        boardModel.initializeBlankBoard(menuModel.getBoardInitializer());
    }

    public void restart() {
        //timer zurücksetzen und neu starten
        timeline.playFromStart();
        boardModel.initializeBlankBoard(menuModel.getBoardInitializer());
    }


    public static MainGameModel deserialize(String json) {
        //gegebenen String json auslesen und Board+timer laden
        JsonDeserializer<Timeline> deserializer = (json1, typeOfT, context) -> {
            JsonObject jsonObject = json1.getAsJsonObject();

            Timeline timeline = new Timeline(new KeyFrame(Duration.INDEFINITE));
            timeline.setCycleCount(1);
            timeline.jumpTo(Duration.millis(jsonObject.get("time").getAsDouble()));

            return timeline;
        };
        var gson = FxGson.coreBuilder().setPrettyPrinting().registerTypeAdapter(Timeline.class, deserializer).create();
        MainGameModel model =  gson.fromJson(json, MainGameModel.class);
        model.getBoardModel().getCells().forEach(cellModel -> cellModel.setBoardModel(model.getBoardModel()));
        return model;
    }

    public String serialize() {
        //Spielstand in json datei speichern
        JsonSerializer<Timeline> serializer = (src, typeOfSrc, context) -> {
            JsonObject jsonTime = new JsonObject();
            jsonTime.addProperty("time", src.getCurrentTime().toMillis());

            return jsonTime;
        };
        var gson = FxGson.coreBuilder().setPrettyPrinting().registerTypeAdapter(Timeline.class, serializer).create();
        return gson.toJson(this);
    }


    public void start() {
        //Spiel starten, auch geladenes möglich
        timeline.play();
        boardModel.initializeBoard();
    }
}
