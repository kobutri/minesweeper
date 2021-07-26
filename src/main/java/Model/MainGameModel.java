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
        timeline = new Timeline(new KeyFrame(Duration.INDEFINITE));
        timeline.setCycleCount(1);
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public void start(){
        timeline.play();
        boardModel.initializeBoard(menuModel.getBoardInitializer());
    }

    public void restart() {
        timeline.playFromStart();
        boardModel.initializeBoard(menuModel.getBoardInitializer());
    }


    public static MainGameModel deserialize(String json) {
        JsonDeserializer<Timeline> deserializer = new JsonDeserializer<Timeline>() {
            @Override
            public Timeline deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();

                Timeline timeline = new Timeline(new KeyFrame(Duration.INDEFINITE));
                timeline.setCycleCount(1);
                timeline.jumpTo(Duration.millis(jsonObject.get("time").getAsDouble()));

                return timeline;
            }
        };
        var gson = FxGson.coreBuilder().setPrettyPrinting().registerTypeAdapter(Timeline.class, deserializer).create();
        return gson.fromJson(json, MainGameModel.class);
    }

    public String serialize() {
        JsonSerializer<Timeline> serializer = new JsonSerializer<Timeline>() {
            @Override
            public JsonElement serialize(Timeline src, Type typeOfSrc, JsonSerializationContext context) {
                JsonObject jsonTime = new JsonObject();
                jsonTime.addProperty("time", src.getCurrentTime().toMillis());

                return jsonTime;
            }
        };
        var gson = FxGson.coreBuilder().setPrettyPrinting().registerTypeAdapter(Timeline.class, serializer).create();
        return gson.toJson(this);
    }


}
