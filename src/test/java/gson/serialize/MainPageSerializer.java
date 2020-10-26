package gson.serialize;

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;
import pageobject.MainPage;

import java.lang.reflect.Type;

public class MainPageSerializer implements JsonSerializer<MainPage> {
    @Override
    public JsonElement serialize(@NotNull MainPage mainPage, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("URL_MATCH", mainPage.getURL_MATCH());
        result.addProperty("HeaderSearch", mainPage.getXHeaderSearch());
        result.addProperty("SearchButton", mainPage.getXSearchButton());
        return result;
    }
}