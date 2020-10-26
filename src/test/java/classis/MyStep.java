package classis;

import org.openqa.selenium.WebDriver;
import pageobject.CatalogPage;
import pageobject.MainPage;

public class MyStep {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected CatalogPage catalogPage;

    public void execution() {
    }

    public MyStep() {
        System.out.println("Шаг");
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void setMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void setCatalogPage(CatalogPage catalogPage) {
        this.catalogPage = catalogPage;
    }
}
