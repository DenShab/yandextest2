package gson.steps.deserialize;


import classis.ClickToFind;
import classis.MyInput;
import com.google.gson.*;
import pageobject.CatalogPage;

import java.lang.reflect.Type;

public class ClickToFindDeserializer implements JsonDeserializer<ClickToFind> {
    @Override
    public ClickToFind deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        ClickToFind result = new ClickToFind();
        JsonObject jsonObject = json.getAsJsonObject();
        //result.(jsonObject.get("product").getAsString());
        return result;
    }
}