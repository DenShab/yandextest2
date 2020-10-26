package classis;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

import static helper.Helper.makeScreenshot;

public class SortByPrice extends MyStep{
    String sortBy;
    public SortByPrice() {
        System.out.println("Добавил шаг применить сортировку по цене");
    }
    //применить сортировку по цене
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step - применить сортировку по цене")
    @Override
    public void execution() {
        catalogPage.submitSortBt();
        makeScreenshot(driver);
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
