package pageobject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver driver;
    private static String URL_MATCH = "https://market.yandex.ru/";
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
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException(
                    "This is not the page you are expected"
            );
        }
        //PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    /**
     * Ввод товара в поисковую строку
     *
     * @param product - {@link String}
     */
    public void input(String product) {
        headerSearch.sendKeys(product);
    }

    /**
     * Нажатие кнопки найти
     */
    public void submitSearch() {
        searchButton.submit();
    }

}
