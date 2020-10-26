package classis;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

import static org.testng.Assert.assertEquals;

public class MyOpenUrl extends MyStep {
    String url;


    public MyOpenUrl() {
        System.out.println("Добавил шаг открыть страницу Яндекс.Маркет");
    }


    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step - открыть страницу Яндекс.Маркет")
    @Override
    public void execution() {
        driver.get(url);
        assertEquals("https://market.yandex.ru/", driver.getCurrentUrl());
        helper.Helper.makeScreenshot(driver);
        System.out.println("открыть страницу Яндекс.Маркет");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
