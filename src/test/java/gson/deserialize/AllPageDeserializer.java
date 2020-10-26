package gson.deserialize;

import com.google.gson.*;
import pageobject.CatalogPage;
import pageobject.MainPage;
import pageobject.PageParametrs;

import java.lang.reflect.Type;
import java.util.Map;

public class AllPageDeserializer implements JsonDeserializer<PageParametrs> {
    @Override
    public PageParametrs deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        PageParametrs result = new PageParametrs();
        JsonObject jsonObject = json.getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            switch (entry.getKey()) {
                case "MainPage": {
                    MainPage mainPage = context.deserialize(entry.getValue(), MainPage.class);
                    result.setMainPage(mainPage);
                    break;
                }
                case "CatalogPage": {
                    CatalogPage catalogPage = context.deserialize(entry.getValue(), CatalogPage.class);
                    result.setCatalogPage(catalogPage);
                    break;
                }
                default:
                    break;
            }
        }
        return result;
    }
}
