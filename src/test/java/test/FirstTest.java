package test;

import helper.Helper;
import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobject.CatalogPage;
import pageobject.MainPage;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FirstTest {
    private WebDriver webDriver;

    private String url = "https://market.yandex.ru/";
    private String product ="смартфон xiaomi mi a2";

    private String store = "//*[@class=\"_3w32plrcn2\"]";
    private String onestore = "//article[1]//div[@data-zone-name=\"shop-name\"]";
    private String oneprise = "//article[1]//div[@data-zone-name=\"price\"]";
    private String price = "//span[@data-autotest-currency=\"₽\"]";

    String originalWindow;
    Set<String> oldWindowsSet;
    MainPage mainPage;
    CatalogPage catalogPage;

    @BeforeTest
    public void setUp() {
        if (webDriver == null) {
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        }
    }

    @AfterTest
    public void cleanUp() {
        // закрыть браузер
        System.out.println("Close browser");
        //if (webDriver != null)
        //    webDriver.quit();
    }


    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }


    @Description("Description")
    @Epic("Epic")
    @Feature("Feature")
    @Story("Story")
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    public void someTest() {
        openUrl();
        mainPage = PageFactory.initElements(webDriver, MainPage.class);
        input();
        clickToFind();
        catalogPage = PageFactory.initElements(webDriver, CatalogPage.class);
        getProducts();
        checkProduct();
        sortByPrice();
        clickFirstProduct();
        getStorePrice();

    }


    //открыть страницу Яндекс.Маркет
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 0 - открыть страницу Яндекс.Маркет")
    private void openUrl() {
        webDriver.get(url);
        assertEquals("https://market.yandex.ru/", webDriver.getCurrentUrl());
        makeScreenshot();
    }

    //ввести в строку поиска какой-либо товар (например, модель вашего телефона)
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 1 - ввести в строку поиска какой-либо товар (например, модель вашего телефона)")
    private void input() {
        mainPage.input(product);
        makeScreenshot();
    }

    //нажать кнопку Найти
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 2 - нажать кнопку Найти")
    private void clickToFind() {
        mainPage.submitSearch();
        makeScreenshot();
    }

    //получить список найденных товаров (наименование товара - цена)
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 3 - получить список найденных товаров (наименование товара - цена)")
    private void getProducts() {
        catalogPage.productSearch();
        makeScreenshot();
    }

    // добавить проверку того, что в списке результатов содержится товар, который искали
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 4 - добавить проверку того, что в списке результатов содержится товар, который искали")
    private void checkProduct() {
        assertTrue(catalogPage.chekProd(product));
        makeScreenshot();
    }

    //применить сортировку по цене
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 5 - применить сортировку по цене")
    private void sortByPrice() {
        catalogPage.submitSortBt();
        makeScreenshot();
    }

    // нажать на самый первый результат
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 6 - нажать на самый первый результат")
    private void clickFirstProduct() {
        originalWindow = webDriver.getWindowHandle();
        oldWindowsSet = webDriver.getWindowHandles();
        catalogPage.clickFirstProd();
        makeScreenshot();
    }

    //вывести в консоль название магазина и цену товара
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 7 - вывести в консоль название магазина и цену товара")
    @Flaky
    private void getStorePrice() {

        String newWindow = (new WebDriverWait(webDriver, Duration.ofSeconds(10)))
                .until((ExpectedCondition<String>) driver -> {
                            assert driver != null;
                            Set<String> newWindowsSet;
                            newWindowsSet = driver.getWindowHandles();
                            newWindowsSet.removeAll(oldWindowsSet);
                            return newWindowsSet.size() > 0 ?
                                    newWindowsSet.iterator().next() : null;
                        }
                );

        webDriver.switchTo().window(newWindow);
        if (webDriver.getCurrentUrl().toLowerCase().contains(url.toLowerCase())) {
            WebElement dynamicElement4 = (new WebDriverWait(webDriver, Duration.ofSeconds(50)))
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(price)));
            if (dynamicElement4.getText().toLowerCase().contains("нет в продаже")) {
                System.out.println("нет в продаже");
            } else {
                if (Helper.existsElement(webDriver,(store+"//img"))){
                    String storeStr = webDriver.findElement(By.xpath(store)).findElement(By.xpath(".//img"))
                        .getAttribute("title");
                String priceStr = webDriver.findElement(By.xpath(store)).findElement(By.xpath(".//span")).getText();
                System.out.println("Магазин " + storeStr + " цена " + priceStr);
                }
                else {
                    if (Helper.existsElement(webDriver,(store+"//[@data-zone-name=\\\"shopName\\\"]\""))){
                        String storeStr = webDriver.findElement(By.xpath(store)).findElement(By.xpath(".//[@data-zone-name=\"shopName\"]")).getText();
                        String priceStr = webDriver.findElement(By.xpath(store)).findElement(By.xpath(".//span")).getText();
                        System.out.println("Магазин " + storeStr + " цена " + priceStr);
                    }
                    else {
                        String priceStr = webDriver.findElement(By.xpath(store)).findElement(By.xpath(".//div[@class=\"_3NaXxl-HYN _3PwoBT4kxK ffKant8Dgf\"]")).getText();
                        System.out.println("Магазин Яндекс Маркет цена " + priceStr);
                    }
                    //svg

                    //data-zone-name="shopName"
                }
            }
        } else {
            webDriver.switchTo().window(originalWindow);

           //WebElement dynamicElement4 = (new WebDriverWait(webDriver, Duration.ofSeconds(50)))
           //        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(onestore)));

           //WebElement dynamicElement5 = (new WebDriverWait(webDriver, Duration.ofSeconds(50)))
           //        .until(ExpectedConditions.presenceOfElementLocated(By.xpath(oneprise)));
           //String storeStr = dynamicElement4.getText();
           //String priceStr = dynamicElement5.getText();

            String storeStr = webDriver.findElement(By.xpath(onestore)).getText();
            String priceStr = webDriver.findElement(By.xpath(oneprise)).getText();
            System.out.println("Магазин " + storeStr + " цена " + priceStr);

        }
        makeScreenshot();
    }
}

