package gson.steps.deserialize;

import classis.*;
import com.google.gson.*;
import pageobject.CatalogPage;
import pageobject.MainPage;
import pageobject.PageParametrs;

import java.lang.reflect.Type;
import java.util.Map;

public class StepsDeserializer implements JsonDeserializer<MySteps> {
    @Override
    public MySteps deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
            JsonParseException {
        MySteps result= new MySteps();

        JsonObject jsonObject = json.getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            MyStep step;
            switch (entry.getKey()) {
                case "открыть страницу Яндекс.Маркет": {
                    step = context.deserialize(entry.getValue(), MyOpenUrl.class);
                    break;
                }
                case "ввести в строку поиска": {
                    step = context.deserialize(entry.getValue(), MyInput.class);
                    break;
                }

                case "нажать кнопку Найти": {
                    step = context.deserialize(entry.getValue(), ClickToFind.class);
                    break;
                }
                case "получить список товаров": {
                    step = context.deserialize(entry.getValue(), GetProducts.class);
                    break;
                }
                case "проверка товара в списке": {
                    step = context.deserialize(entry.getValue(), CheckProduct.class);
                    break;
                }
                case "применить сортировку по цене": {
                    step = context.deserialize(entry.getValue(), SortByPrice.class);
                    break;
                }
                /*
                case "нажать результат": {
                    step = context.deserialize(entry.getValue(), MyInput.class);
                    break;
                }
                case "вывести название магазина и цену товара": {
                    step = context.deserialize(entry.getValue(), MyInput.class);
                    break;
                }
                */

                default:{
                    step = new MyStep();
                    break;
                }

            }
            result.addMyStep(step);
        }
        return result;
    }
}