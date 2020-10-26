package gson.serialize;

import com.google.gson.*;

import java.lang.reflect.Type;

import pageobject.CatalogPage;

public class CatalogPageSerializer implements JsonSerializer<CatalogPage> {
    @Override
    public JsonElement serialize(CatalogPage catalogPage, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("URL_MATCH", catalogPage.getURL_MATCH());
        result.addProperty("ProductXpath", catalogPage.getProductXpath());
        result.addProperty("FirstProd", catalogPage.getXFirstProd());
        result.addProperty("SortBt", catalogPage.getXSortBt());
        return result;
    }
}