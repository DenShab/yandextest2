package gson.steps.deserialize;

import classis.GetProducts;
import classis.SortByPrice;
import com.google.gson.*;

import java.lang.reflect.Type;

public class SortByPriceDeserializer implements JsonDeserializer<SortByPrice> {
    @Override
    public SortByPrice deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        SortByPrice result = new SortByPrice();
        JsonObject jsonObject = json.getAsJsonObject();
        result.setSortBy(jsonObject.get("sort").getAsString());
        return result;
    }
}