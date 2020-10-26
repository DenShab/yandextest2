package gson.serialize;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import pageobject.PageParametrs;

import java.lang.reflect.Type;

public class AllPageSerializer implements JsonSerializer<PageParametrs> {
    @Override
    public JsonElement serialize(PageParametrs src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.add("MainPage", context.serialize(src.getMainPage()));
        result.add("CatalogPage", context.serialize(src.getCatalogPage()));

        return result;
    }
}
