package gson.deserialize;

import com.google.gson.*;
import pageobject.MainPage;

import java.lang.reflect.Type;
import java.util.Map;

public class MainPageDeserializer implements JsonDeserializer<MainPage> {
    @Override
    public MainPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        MainPage result = new MainPage();
        JsonObject jsonObject = json.getAsJsonObject();
        result.setURL_MATCH(jsonObject.get("URL_MATCH").getAsString());
        result.setXHeaderSearch(jsonObject.get("HeaderSearch").getAsString());
        result.setXSearchButton(jsonObject.get("SearchButton").getAsString());
        return result;
    }
}

