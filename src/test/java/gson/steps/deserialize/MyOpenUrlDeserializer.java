package gson.steps.deserialize;

import classis.MyOpenUrl;
import com.google.gson.*;
import pageobject.CatalogPage;

import java.lang.reflect.Type;

public class MyOpenUrlDeserializer implements JsonDeserializer<MyOpenUrl> {
    @Override
    public MyOpenUrl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        MyOpenUrl result = new MyOpenUrl();
        JsonObject jsonObject = json.getAsJsonObject();
        result.setUrl(jsonObject.get("URL_MATCH").getAsString());
        return result;
    }
}
