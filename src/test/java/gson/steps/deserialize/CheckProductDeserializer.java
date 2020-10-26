package gson.steps.deserialize;

import classis.CheckProduct;
import classis.MyInput;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CheckProductDeserializer implements JsonDeserializer<CheckProduct> {
    @Override
    public CheckProduct deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        CheckProduct result = new CheckProduct();
        JsonObject jsonObject = json.getAsJsonObject();
        result.setProduct(jsonObject.get("product").getAsString());
        return result;
    }
}