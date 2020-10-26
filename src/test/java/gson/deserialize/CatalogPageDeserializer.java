package gson.deserialize;

import com.google.gson.*;
import pageobject.CatalogPage;
import pageobject.MainPage;

import java.lang.reflect.Type;

public class CatalogPageDeserializer implements JsonDeserializer<CatalogPage> {
    @Override
    public CatalogPage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        CatalogPage result = new CatalogPage();
        JsonObject jsonObject = json.getAsJsonObject();
        result.setURL_MATCH(jsonObject.get("URL_MATCH").getAsString());
        result.setProductXpath(jsonObject.get("ProductXpath").getAsString());
        result.setXFirstProd(jsonObject.get("FirstProd").getAsString());
        result.setXSortBt(jsonObject.get("SortBt").getAsString());
        return result;
    }
}


