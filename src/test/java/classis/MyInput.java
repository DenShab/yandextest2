package classis;

import helper.Helper;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;

import static org.testng.Assert.assertEquals;

public class MyInput extends MyStep {


    String product;


    public MyInput() {
        System.out.println("Добавил шаг ввести в строку поиска какой-либо товар (например, модель вашего телефона)");
    }

    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step  - ввести в строку поиска какой-либо товар (например, модель вашего телефона)")
    @Override
    public void execution() {
        mainPage.input(product);
        Helper.makeScreenshot(driver);
        System.out.println("ввести в строку поиска какой-либо товар (например, модель вашего телефона)");
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
}
