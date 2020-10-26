package classis;

import helper.Helper;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pageobject.MainPage;

public class ClickToFind extends MyStep{

    public ClickToFind() {
        System.out.println("Добавил шаг нажать кнопку Найти");
    }
    //нажать кнопку Найти
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step - нажать кнопку Найти")
    @Override
    public void execution() {
        mainPage.submitSearch();
        Helper.makeScreenshot(driver);
    }


}
