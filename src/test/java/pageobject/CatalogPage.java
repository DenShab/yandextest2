package pageobject;

import classis.Item;
import helper.Helper;
import helper.JSUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

import static helper.JSUtils.*;

public class CatalogPage {
    private WebDriver driver;

    private static String URL_MATCH = "https://market.yandex.ru/";
            //"catalog";

    private List<WebElement> elements;
    private ArrayList<Item> items;

    private String productXpath = "//article[@data-autotest-id=\"product-snippet\" or @data-autotest-id=\"offer-snippet\"] ";

    /**
     * Кнопка сортировки по цене
     */
    @FindBy(xpath = "//button[text()=\"по цене\"]")
    private WebElement sortBt;

    /**
     * Первый элемент списка
     */
    @FindBy(xpath = "//*[@data-zone-name=\"snippetList\"]/article[position()=1]")
    private WebElement firstprodXpath;


    public CatalogPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException(
                    "This is not the page you are expected"
            );
        }
        this.driver = driver;
    }

    /**
     * Проверка наличия товара на стрнице
     */
    public boolean chekProd(String product) {
        productSearch();
        boolean ret = false;
        for (Item item : this.items) {
            if (item.getName().contains(product.toLowerCase())) {
                ret = true;
                break;
            }
        }
        for (WebElement element : elements) {
            if (element.getText().toLowerCase().contains(product.toLowerCase())) {
                scrollIntoViewByJS(driver, element);
                drawBorderByJS(driver, element);
                Helper.wait(1000);
            }
        }

        return ret;
    }

    /**
     * Поиск товаров на странице
     * (метод не доделан. Нужно перебрать все страницы каталога, если хотим действительно полный список)
     */
    public void productSearch() {

        this.elements = driver.findElements(By.xpath(productXpath));

        this.items = new ArrayList<>();
        for (WebElement element : elements) {
            String name = element.findElement(By.xpath(".//h3")).getText();
            String price = element.findElement(By.xpath(".//a")).getText();

            this.items.add(new Item(name, price));
        }
    }

    /**
     * Нажатие на кнопку сортировки по цене
     */
    public void submitSortBt() {
        sortBt.click();
        Helper.wait(5000);
    }

    /**
     * Нажатие на первый элеент списка
     */
    public void clickFirstProd() {
        WebElement firstprod = firstprodXpath.
                findElement(By.xpath(".//h3[@data-zone-name=\"title\"]/a"));
        JSUtils.drawBorderByJS(driver,firstprod);
        firstprod.click();
    }
}
