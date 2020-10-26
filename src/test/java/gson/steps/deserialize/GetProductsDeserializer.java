package gson.steps.deserialize;

import classis.ClickToFind;
import classis.GetProducts;
import com.google.gson.*;

import java.lang.reflect.Type;

public class GetProductsDeserializer implements JsonDeserializer<GetProducts> {
    @Override
    public GetProducts deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        GetProducts result = new GetProducts();
        JsonObject jsonObject = json.getAsJsonObject();
        //result.(jsonObject.get("product").getAsString());
        return result;
    }
}