package classis;

import helper.Helper;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

import static org.testng.Assert.assertTrue;

public class CheckProduct extends MyStep {
String product;
    public CheckProduct() {
        System.out.println("Добавил шаг проверка того, что в списке результатов содержится товар, который искали");
    }
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step - добавить проверку того, что в списке результатов содержится товар, который искали")
    @Override
    public void execution() {
        assertTrue(catalogPage.chekProd(product));
        Helper.makeScreenshot(driver);
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
