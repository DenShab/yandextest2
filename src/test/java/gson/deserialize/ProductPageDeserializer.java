package gson.deserialize;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pageobject.ProductPage;

import java.lang.reflect.Type;

public class ProductPageDeserializer implements JsonSerializer<ProductPage> {
    @Override
    public JsonElement serialize(ProductPage productPage, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("TEST", "test");
        return result;
    }

}
