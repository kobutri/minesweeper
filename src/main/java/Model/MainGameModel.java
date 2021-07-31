package Model;

import com.google.gson.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import org.hildan.fxgson.FxGson;
import javafx.util.Duration;

public class MainGameModel {
    private MenuModel menuModel = new MenuModel();
    private BoardModel boardModel = new BoardModel();
    private Timeline timeline;


    /**Konstruktor von MainGameModel
     *
     */
    public MainGameModel() {
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

    /**Leeres Feld starten und timer starten
     *
     */
    public void startBlank(){

        timeline.play();
        boardModel.initializeBlankBoard(menuModel.getBoardInitializer());
    }

    /**timer zurücksetzen und neu starten
     *
     */
    public void restart() {

        timeline.playFromStart();
        boardModel.initializeBlankBoard(menuModel.getBoardInitializer());
    }

    /**gegebenen String json auslesen und Board+timer laden
     * @param json String
     * @return timeline Timeline oder MainGameModel model
     */
    public static MainGameModel deserialize(String json) {
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

    /**Spielstand in json datei speichern
     * @return json Datei
     */
    public String serialize() {
        JsonSerializer<Timeline> serializer = (src, typeOfSrc, context) -> {
            JsonObject jsonTime = new JsonObject();
            jsonTime.addProperty("time", src.getCurrentTime().toMillis());

            return jsonTime;
        };
        var gson = FxGson.coreBuilder().setPrettyPrinting().registerTypeAdapter(Timeline.class, serializer).create();
        return gson.toJson(this);
    }


    /**Spiel starten, auch geladenes möglich
     *
     */
    public void start() {
        timeline.play();
        boardModel.initializeBoard();
    }
}
