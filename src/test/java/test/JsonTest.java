package test;

import classis.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.steps.deserialize.*;
import io.qameta.allure.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobject.CatalogPage;
import pageobject.MainPage;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static helper.Helper.readUsingBufferedReader;


public class JsonTest {

    private WebDriver webDriver;

    private final String url="https://market.yandex.ru/";
    //→
    public MainPage mainPageBack;
    public CatalogPage catalogPageBack;


    private final String product="смартфон xiaomi mi a2";

    private final String store="//*[@class=\"_3w32plrcn2\"][1]";
    private final String onestore="//article[1]//div[@data-zone-name=\"shop-name\"]";
    private final String oneprise="//article[1]//div[@data-zone-name=\"price\"]";
    private final String price="//span[@data-autotest-currency=\"₽\"]";
    //→
    String originalWindow;
    Set<String> oldWindowsSet;

    @BeforeTest
    public void setUp() {
        if (webDriver == null) {
            webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
            mainPageBack = new MainPage(webDriver, "mainPage.json");
            catalogPageBack = new CatalogPage(webDriver, "catalogPage.json");
        }
    }

    @AfterTest
    public void cleanUp() {
        // закрыть браузер
        System.out.println("Close browser");
        //if (webDriver != null)
        //    webDriver.quit();
    }


    @AfterMethod
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
        String json = "";
        try {
            json = readUsingBufferedReader("jsonTest.json");
        } catch (IOException e) {
            System.out.println("не удалось считать json jsonTest");
            e.printStackTrace();
        }
        if (json != "") {

            Gson gson;
            gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(MyInput.class, new MyInputDeserializer())
                    .registerTypeAdapter(MyOpenUrl.class, new MyOpenUrlDeserializer())
                    .registerTypeAdapter(MySteps.class, new StepsDeserializer())
                    .registerTypeAdapter(ClickToFind.class, new ClickToFindDeserializer())
                    .registerTypeAdapter(GetProducts.class, new GetProductsDeserializer())
                    .registerTypeAdapter(CheckProduct.class, new CheckProductDeserializer())
                    .registerTypeAdapter(SortByPrice.class, new SortByPriceDeserializer())
                    .disableHtmlEscaping()
                    .create();
            MySteps mySteps=gson.fromJson(json, MySteps.class);
            System.out.println("Десерилизаци MySteps прошла успешно");
            mySteps.getMySteps().stream().forEach(myStep -> myStep.setDriver(webDriver));
            System.out.println("Присоил драйвер всем шагам");
            mySteps.getMySteps().stream().forEach(myStep -> myStep.setMainPage(mainPageBack));

            mySteps.getMySteps().stream().forEach(myStep -> myStep.setCatalogPage(catalogPageBack));
            mySteps.getMySteps().stream().forEach(MyStep::execution);
            System.out.println("выполнил шаги");


        } else {
            System.out.println("json CatalogPage пуст");
        }
    }
}
