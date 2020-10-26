package gson.steps.deserialize;

import classis.MyInput;
import com.google.gson.*;
import pageobject.CatalogPage;

import java.lang.reflect.Type;

public class MyInputDeserializer implements JsonDeserializer<MyInput> {
    @Override
    public MyInput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        MyInput result = new MyInput();
        JsonObject jsonObject = json.getAsJsonObject();
        result.setProduct(jsonObject.get("product").getAsString());
        return result;
    }
}