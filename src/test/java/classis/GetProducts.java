package classis;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

import static helper.Helper.makeScreenshot;

public class GetProducts extends MyStep{

    public GetProducts() {
        System.out.println("Добавил шаг получить список найденных товаров (наименование товара - цена)");
    }
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step - получить список найденных товаров (наименование товара - цена)")
    @Override
    public void execution() {
        catalogPage.productSearch();
        makeScreenshot(driver);
    }
}
