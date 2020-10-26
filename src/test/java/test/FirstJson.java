package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.deserialize.AllPageDeserializer;
import gson.deserialize.CatalogPageDeserializer;
import gson.deserialize.MainPageDeserializer;
import gson.serialize.AllPageSerializer;
import gson.serialize.CatalogPageSerializer;
import gson.serialize.MainPageSerializer;
import helper.Helper;
import io.qameta.allure.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobject.CatalogPage;
import pageobject.MainPage;
import pageobject.PageParametrs;


import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FirstJson {

    private WebDriver webDriver;

    private final String url = "https://market.yandex.ru/";
    //→
    MainPage mainPageBack;
    CatalogPage catalogPageBack;


    private final String product = "смартфон xiaomi mi a2";

    private final String store = "//*[@class=\"_3w32plrcn2\"][1]";
    private final String onestore = "//article[1]//div[@data-zone-name=\"shop-name\"]";
    private final String oneprise = "//article[1]//div[@data-zone-name=\"price\"]";
    private final String price = "//span[@data-autotest-currency=\"₽\"]";
    //→
    String originalWindow;
    Set<String> oldWindowsSet;

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
    public void someJson() {
        openUrl();
        //  mainPage = PageFactory.initElements(webDriver, MainPage.class);
        // catalogPage = PageFactory.initElements(webDriver,CatalogPage.class);
        //mainPage=new JsonMainPage();
        //  Gson gson;
        // gson = new GsonBuilder()
        //         .setPrettyPrinting()
        //         .registerTypeAdapter(MainPage.class, new MainPageSerializer())
        //         .registerTypeAdapter(CatalogPage.class, new CatalogPageSerializer())
        //          .disableHtmlEscaping()
        //         .create();
//
        // System.out.println("Серилизаци");
        // System.out.println(json);
//
//
        // try(FileWriter writer = new FileWriter("mainPage.json", false))
        // {
        //     writer.write(json);
        //     writer.flush();
        // }
        // catch(IOException ex){
        //     System.out.println(ex.getMessage());
        // }
//
        // json = gson.toJson(catalogPage);
        // System.out.println("Серилизаци");
        // System.out.println(json);
//
//
        // try(FileWriter writer = new FileWriter("catalogPage.json", false))
        // {
        //     writer.write(json);
        //     writer.flush();
        // }
        // catch(IOException ex){
        //     System.out.println(ex.getMessage());
        // }
        // gson = new GsonBuilder()
        //         .setPrettyPrinting()
        //         //.registerTypeAdapter(MainPage.class, new MainPageDeserializer())
        //         .registerTypeAdapter(CatalogPage.class, new CatalogPageDeserializer())
        //         .create();
//
        //String jsonMainPage=readUsingBufferedReader("mainPage.json");
        // String jsonCatalogPage=readUsingBufferedReader("catalogPage.json");

        // mainPageBack = gson.fromJson(jsonMainPage, MainPage.class);
        // catalogPageBack = gson.fromJson(jsonCatalogPage, CatalogPage.class);
        //mainPageBack.setWebDriver(webDriver);
        // System.out.println("Десерилизаци");


//        gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .disableHtmlEscaping()
//                .create();
//        System.out.println("Серилизаци mainPageBack");
//        //System.out.println(gson.toJson(mainPageBack));
//
//
//        System.out.println("Серилизаци catalogPageBack");
//        //System.out.println(gson.toJson(catalogPageBack));
        mainPageBack = new MainPage(webDriver, "mainPage.json");
        input();
        clickToFind();
        //catalogPageBack.setWebDriver(webDriver);
        catalogPageBack = new CatalogPage(webDriver, "catalogPage.json");
        Gson gson;
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(MainPage.class, new MainPageSerializer())
                .registerTypeAdapter(CatalogPage.class, new CatalogPageSerializer())
                .registerTypeAdapter(PageParametrs.class, new AllPageSerializer())
                .disableHtmlEscaping()
                .create();

        PageParametrs pageParametrs = new PageParametrs();
        pageParametrs.setMainPage(mainPageBack);
        pageParametrs.setCatalogPage(catalogPageBack);
        String json = gson.toJson(pageParametrs);

        System.out.println("Серилизаци");
        System.out.println(json);

        gson = new GsonBuilder()
                .registerTypeAdapter(MainPage.class, new MainPageDeserializer())
                .registerTypeAdapter(CatalogPage.class, new CatalogPageDeserializer())
                .registerTypeAdapter(PageParametrs.class, new AllPageDeserializer())
                .disableHtmlEscaping()
                .create();
        PageParametrs pageParametrsBack = gson.fromJson(json, PageParametrs.class);

        System.out.println("ДеСерилизаци");

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        System.out.println("Серилизаци повтор");
        System.out.println(gson.toJson(pageParametrsBack));

        //getProducts();
        //checkProduct();
        //sortByPrice();
        //clickFirstProduct();
        //getStorePrice();
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
        mainPageBack.input(product);
        makeScreenshot();
    }

    //нажать кнопку Найти
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 2 - нажать кнопку Найти")
    private void clickToFind() {
        mainPageBack.submitSearch();
        makeScreenshot();
    }

    //получить список найденных товаров (наименование товара - цена)
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 3 - получить список найденных товаров (наименование товара - цена)")
    private void getProducts() {
        catalogPageBack.productSearch();
        makeScreenshot();
    }

    // добавить проверку того, что в списке результатов содержится товар, который искали
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 4 - добавить проверку того, что в списке результатов содержится товар, который искали")
    private void checkProduct() {
        assertTrue(catalogPageBack.chekProd(product));
        makeScreenshot();
    }

    //применить сортировку по цене
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 5 - применить сортировку по цене")
    private void sortByPrice() {
        catalogPageBack.submitSortBt();
        makeScreenshot();
    }

    // нажать на самый первый результат
    @Owner(value = "Бойчук Денис Иванович")
    @Severity(value = SeverityLevel.NORMAL)
    @Step("Step 6 - нажать на самый первый результат")
    private void clickFirstProduct() {
        originalWindow = webDriver.getWindowHandle();
        oldWindowsSet = webDriver.getWindowHandles();
        catalogPageBack.clickFirstProd();
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
                if (Helper.existsElement(webDriver, (store + "//img"))) {
                    String storeStr = webDriver.findElement(By.xpath(store)).findElement(By.xpath(".//img"))
                            .getAttribute("title");
                    String priceStr = webDriver.findElement(By.xpath(store)).findElement(By.xpath(".//span")).getText();
                    System.out.println("Магазин " + storeStr + " цена " + priceStr);
                } else {
                    if (Helper.existsElement(webDriver, (store + "//[@data-zone-name=\"shopName\"]"))) {
                        String storeStr = webDriver.findElement(By.xpath(store)).findElement(By.xpath(".//[@data-zone-name=\"shopName\"]")).getText();
                        String priceStr = webDriver.findElement(By.xpath(store)).findElement(By.xpath(".//span")).getText();
                        System.out.println("Магазин " + storeStr + " цена " + priceStr);
                    } else {
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
