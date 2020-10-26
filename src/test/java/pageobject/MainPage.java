package pageobject;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import gson.deserialize.CatalogPageDeserializer;
import gson.deserialize.MainPageDeserializer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

import static helper.Helper.readUsingBufferedReader;
import static helper.JSUtils.*;

public class MainPage {
    private WebDriver driver;
    private String URL_MATCH;//= "https://market.yandex.ru/";
    private String xHeaderSearch;// = "//*[@id=\"header-search\"]";
    private String xSearchButton;// = "//*[@type=\"submit\"]";
    /**
     * Поле ввода для поиска
     */
    @FindBy(xpath = "//*[@id=\"header-search\"]")
    private WebElement headerSearch;

    /**
     * Конопка поиска
     */
    @FindBy(xpath = "//*[@type=\"submit\"]")
    private WebElement searchButton;


    public MainPage(WebDriver driver) {
        setWebDriver(driver);
    }

    public MainPage() {
    }

    public MainPage(WebDriver driver, String fileName) {
        setWebDriver(driver);
        //PageFactory.initElements(driver, this);
        String jsonMainPage = "";
        try {
            jsonMainPage = readUsingBufferedReader("mainPage.json");
        } catch (IOException e) {
            System.out.println("не удалось считать json");
            e.printStackTrace();
        }
        if (jsonMainPage != "") {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(MainPage.class, new MainPageDeserializer())
                    .create();
            MainPage mainPageBack = gson.fromJson(jsonMainPage, MainPage.class);
            this.setXSearchButton(mainPageBack.getXSearchButton());
            this.setXHeaderSearch(mainPageBack.getXHeaderSearch());
            this.setURL_MATCH(mainPageBack.getURL_MATCH());
            System.out.println("Десерилизаци mainPage прошла успешно");
        } else {
            System.out.println("json пуст");
        }
    }

    /**
     * Ввод товара в поисковую строку
     *
     * @param product - {@link String}
     */
    public void input(String product) {
        WebElement headerSearch1 = driver.
                findElement(By.xpath(xHeaderSearch));
        drawBorderByJS(driver, headerSearch1);
        //inputElementByJS(driver, headerSearch, product);
        headerSearch1.sendKeys(product);
    }

    /**
     * Нажатие кнопки найти
     */
    public void submitSearch() {
        //searchButton.submit();
        WebElement searchButton1 = driver.
                findElement(By.xpath(xSearchButton));
        drawBorderByJS(driver, searchButton1);
        clickElementByJS(driver, searchButton1);
    }

    public String getURL_MATCH() {
        return URL_MATCH;
    }

    public void setURL_MATCH(String URL_MATCH) {
        this.URL_MATCH = URL_MATCH;
    }

    public String getXHeaderSearch() {
        return xHeaderSearch;
    }

    public void setXHeaderSearch(String xHeaderSearch) {
        this.xHeaderSearch = xHeaderSearch;
    }

    public String getXSearchButton() {
        return xSearchButton;
    }

    public void setXSearchButton(String xSearchButton) {
        this.xSearchButton = xSearchButton;
    }

    public void setWebDriver(WebDriver driver) {
        // if (!driver.getCurrentUrl().contains(URL_MATCH)) {
        //     throw new IllegalStateException(
        //             "This is not the page you are expected"
        //     );
        // }
        //PageFactory.initElements(driver, this);
        this.driver = driver;
    }

}
